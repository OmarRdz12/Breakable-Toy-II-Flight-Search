import { useContext, useState } from "react"
import InputSelect from "./inputs/InputSelect"
import InputDate from "./inputs/InputDate"
import { CheckboxProps, DatePickerProps } from "antd"
import dayjs, { Dayjs } from 'dayjs'
import BaseButton from "./Button"
import InputCheckbox from "./inputs/InputCheckbox"
import InputDebounceSelect from "./inputs/InputDebounceSelect"
import { BasicSelect, SearchForm } from "../interfaces/types"
import { GlobalContext } from "../context/GlobalContext"
import InputText from "./inputs/InputText"

const SearchPanel = () => {
    const url = import.meta.env.VITE_API_URL
    const { searchForm, setSearchForm } = useContext(GlobalContext)
    const [departure, setDeparture] = useState<BasicSelect[]>([])
    const [arrival, setArrival] = useState<BasicSelect[]>([])

    async function fetchAirportList(city: string): Promise<BasicSelect[]> {
        return fetch(`${url}locations?name=${city}`)
            .then((response) => response.json())
            .then((data) =>
                data.map(
                    (airport: { city: string; code: string; country: string }) => ({
                        label: `${airport.city} (${airport.code})`,
                        value: airport.code,
                    }),
                ),
            );
    }

    const onChangeDeparture: DatePickerProps['onChange'] = (date) => {
        setSearchForm((prev: SearchForm) => ({ ...prev, ["departureDate"]: date ? date.format("YYYY-MM-DD") : "" }))
    }

    const onChangeArrival: DatePickerProps['onChange'] = (date) => {
        setSearchForm((prev: SearchForm) => ({ ...prev, ["arrivalDate"]: date ?  date.format("YYYY-MM-DD") : "" }))
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
            <h1 className="text-5xl"> File Search</h1>
            <form className="w-10/12 flex justify-center flex-col">
                <InputDebounceSelect
                    label="Departure airport"
                    id="originLocationCode"
                    fetchData={fetchAirportList}
                    value={departure}
                    setValue={setDeparture}
                    setValueForm={onChangeForm}
                />
                <InputDebounceSelect
                    label="Arrival airport"
                    id="destinationCode"
                    fetchData={fetchAirportList}
                    value={arrival}
                    setValue={setArrival}
                    setValueForm={onChangeForm}
                />
                <InputDate
                    name="dep-date"
                    label="Departure Date"
                    id="dep-date"
                    onChange={onChangeDeparture}
                    value={searchForm.departureDate ? dayjs(searchForm.departureDate) : null}
                    maxDate={searchForm.arrivalDate === "" ? null : searchForm.arrivalDate}
                    size="large"
                    className="w-1/2"
                />
                <InputDate
                    name="ret-date"
                    label="Return Date"
                    id="ret-date"
                    onChange={onChangeArrival}
                    value={searchForm.arrivalDate ? dayjs(searchForm.arrivalDate) : null}
                    size="large"
                    className="w-1/2"
                    minDate={searchForm.departureDate === "" ? null : searchForm.departureDate}
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
                    />
                </div>
            </form>
        </div>
    )
}

export default SearchPanel