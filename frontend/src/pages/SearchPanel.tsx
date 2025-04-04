import { useContext, useState } from "react"
import InputSelect from "../components/inputs/InputSelect"
import InputDate from "../components/inputs/InputDate"
import { CheckboxProps, DatePickerProps } from "antd"
import dayjs from 'dayjs'
import BaseButton from "../components/Button"
import InputCheckbox from "../components/inputs/InputCheckbox"
import InputDebounceSelect from "../components/inputs/InputDebounceSelect"
import { BasicSelect, FlightRespose, SearchForm } from "../interfaces/types"
import { GlobalContext } from "../context/GlobalContext"
import InputText from "../components/inputs/InputText"
import axios, { AxiosError } from "axios"
import { useNavigate } from "react-router-dom"
import { toast } from "sonner"

const SearchPanel = () => {
    const url = import.meta.env.VITE_API_URL
    const context = useContext(GlobalContext)
    const navigate = useNavigate()
    if (!context) {
        throw new Error('Must be inside a context')
    }
    const { searchForm, setSearchForm, setFlights } = context
    const [departure, setDeparture] = useState<BasicSelect[]>([])
    const [arrival, setArrival] = useState<BasicSelect[]>([])
    const [loading, setLoading] = useState<boolean>(false)

    async function fetchAirportList(city: string): Promise<BasicSelect[]> {
        return fetch(`${url}locations?name=${city}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`${response.status}`)
                }
                return response.json()
            })
            .then((data: { city: string; code: string; country: string }[]) =>
                data.map((airport) => ({
                    label: `${airport.city} (${airport.code})`,
                    value: airport.code,
                }))).catch((error: Error) => {
                    toast.error('Sorry, the server is not available')
                    return []
                })
    }

    const onSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        if (!searchForm.adults || !searchForm.originLocationCode || !searchForm.destinationCode || !searchForm.departureDate || !searchForm.currency) {
            toast.error("Fill in all required fields")
            return
        }
        try {
            setLoading(true)
            const data = await axios.get(`${url}flights?originLocationCode=${searchForm.originLocationCode}&destinationLocationCode=${searchForm.destinationCode}&departureDate=${searchForm.departureDate}${searchForm.arrivalDate === "" ? "" : `&arrivalDate=${searchForm.arrivalDate}`}&adults=${searchForm.adults}&currencyCode=${searchForm.currency}&nonStop=${searchForm.nonStop}`)
            const flightResponses: FlightRespose[] = data.data
            setFlights(flightResponses)
            navigate('/flights')
        } catch (error) {
            const axiosError = error as AxiosError
            if (axiosError.response) {
                const status = axiosError.response.status
                switch (status) {
                    case 502:
                        toast.error('Amadeus Internal Error')
                        break
                    case 500:
                        toast.error('Internal Server Error')
                        break
                    case 400:
                        toast.error('Bad request')
                        break
                    case 404:
                        toast.error('Seach not found')
                        break
                    case 501:
                        toast.error('Not implemented')
                        break
                    default:
                        toast.error('Something went wrong')
                }
            }
            toast.warning('The server is busy, please wait')
        } finally {
            setLoading(false)
        }

    }

    const onChangeDeparture: DatePickerProps['onChange'] = (date) => {
        setSearchForm((prev: SearchForm) => ({ ...prev, ["departureDate"]: date ? date.format("YYYY-MM-DD") : "" }))
    }

    const onChangeArrival: DatePickerProps['onChange'] = (date) => {
        setSearchForm((prev: SearchForm) => ({ ...prev, ["arrivalDate"]: date ? date.format("YYYY-MM-DD") : "" }))
    }

    const onChangeBox: CheckboxProps['onChange'] = (e) => {
        setSearchForm((prev: SearchForm) => ({ ...prev, ["nonStop"]: e.target.checked }))
    }

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        onChangeForm(e.target.value, e.target.name)
    }

    const onChangeForm = (value: string, name: string) => {
        setSearchForm((prev: SearchForm) => ({ ...prev, [name]: value }))
    }

    return (
        <div className="w-2/4 h-3/4 shadow flex flex-col items-center justify-center">
            <h1 className="text-5xl">Flight Search</h1>
            <form className="w-10/12 flex justify-center flex-col" onSubmit={onSubmit}>
                <InputDebounceSelect
                    label="Departure airport"
                    id="originLocationCode"
                    fetchData={fetchAirportList}
                    value={departure}
                    setValue={setDeparture}
                    setValueForm={onChangeForm}
                    required
                />
                <InputDebounceSelect
                    label="Arrival airport"
                    id="destinationCode"
                    fetchData={fetchAirportList}
                    value={arrival}
                    setValue={setArrival}
                    setValueForm={onChangeForm}
                    required
                />
                <InputDate
                    name="dep-date"
                    label="Departure Date"
                    id="dep-date"
                    onChange={onChangeDeparture}
                    value={searchForm.departureDate ? dayjs(searchForm.departureDate) : null}
                    maxDate={searchForm.arrivalDate === "" ? undefined : searchForm.arrivalDate}
                    size="large"
                    className="w-1/2"
                    required
                />
                <InputDate
                    name="ret-date"
                    label="Return Date"
                    id="ret-date"
                    onChange={onChangeArrival}
                    value={searchForm.arrivalDate ? dayjs(searchForm.arrivalDate) : null}
                    size="large"
                    className="w-1/2"
                    minDate={searchForm.departureDate === "" ? undefined : searchForm.departureDate}
                />
                <InputSelect
                    name="currency"
                    label="Currency"
                    id="currency"
                    options={[
                        { label: "USD", value: 'USD' },
                        { label: "MXN", value: 'MXN' },
                        { label: "EUR", value: 'EUR' },
                    ]}
                    onChange={(value) => onChangeForm(value, "currency")}
                    value={searchForm.currency}
                    className="w-1/2"
                    size="large"
                    required
                />
                <InputText
                    name="adults"
                    id="adults"
                    type="number"
                    required
                    onChange={handleInputChange}
                    label="Number of adults"
                    size="large"
                />
                <InputCheckbox
                    originChecked={searchForm.nonStop}
                    onChange={onChangeBox}
                    label="Non-stop"
                />
                <div className="w-full mt-4 flex justify-end">
                    <BaseButton
                        htmlType="submit"
                        text="Search"
                        size="large"
                        className="w-1/2 !bg-zinc-700 !text-white shadow hover:!bg-zinc-500 
                        hover:!border-zinc-500 hover:!text-white"
                        loading={loading}
                    />
                </div>
            </form>
        </div>
    )
}

export default SearchPanel