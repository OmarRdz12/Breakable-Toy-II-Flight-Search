import { useContext, useEffect, useState } from "react"
import InputSelect from "./inputs/InputSelect"
import InputDate from "./inputs/InputDate"
import { CheckboxProps, DatePickerProps } from "antd"
import dayjs, { Dayjs } from 'dayjs'
import BaseButton from "./Button"
import InputCheckbox from "./inputs/InputCheckbox"
import InputDebounceSelect from "./inputs/InputDebounceSelect"
import { BasicSelect, SearchForm } from "../interfaces/types"
import { GlobalContext } from "../context/GlobalContext"

const SearchPanel = () => {
    const url = import.meta.env.VITE_API_URL
    const { searchForm, setSearchForm } = useContext(GlobalContext)

    const [value, setValue] = useState('')
    const [date, setDate] = useState<string | number | Dayjs | Date | null | undefined>(null)
    const [checked, setChecked] = useState(false)
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

    const handleChange = (name: string, value: string) => {
        //setSearchForm()
        setValue(value)
    }

    const onChange: DatePickerProps['onChange'] = (date) => {
        setDate(date.format("YYYY-MM-DD"))
    }

    const onChangeBox: CheckboxProps['onChange'] = (e) => {
        console.log('checked = ', e.target.checked);
        setChecked(e.target.checked);
    }

    const onChangeForm = (value: string, name: string) => {
        setSearchForm((prev: SearchForm) => ({...prev, [name]: value}))
        console.log(name, value)
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
                    onChange={onChange}
                    value={date ? dayjs(date) : null}
                    size="large"
                    className="w-1/2"
                />
                <InputDate
                    name="ret-date"
                    label="Return Date"
                    id="ret-date"
                    onChange={onChange}
                    value={date ? dayjs(date) : null}
                    size="large"
                    className="w-1/2"
                />
                <InputSelect
                    name="currency"
                    label="Currency"
                    id="currency"
                    options={[{ label: "USD", value: 'USD' }, { label: "MX", value: 'MX' }]}
                    onChange={(value) => handleChange("currency", value)}
                    value={value}
                    className="w-1/2"
                    size="large"
                />
                <InputCheckbox
                    originChecked={checked}
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