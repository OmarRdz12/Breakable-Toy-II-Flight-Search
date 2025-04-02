import { useContext } from "react"
import { GlobalContext } from "../context/GlobalContext"
import { FlightRespose, IndividualFlight, SortingForm } from "../interfaces/types"
import FlightCard from "../components/Cards/FlightCard"
import BaseButton from "../components/Button"
import { useNavigate } from "react-router-dom"
import InputSelect from "../components/inputs/InputSelect"
import axios from "axios"
import FlightRoundedCard from "../components/Cards/FlightRoundedCard"

const FlightResults = () => {
    const context = useContext(GlobalContext)
    const navigate = useNavigate()
    if (!context) {
        throw new Error('Must be inside a context')
    }
    const { flights, sortingForm, setSortingForm, setFlights } = context

    const onChangeForm = (value: string, name: string) => {
        setSortingForm((prev: SortingForm) => ({ ...prev, [name]: value }))
    }

    const onClick = () => {
        navigate("/")
    }

    const onSort = async (e: React.FormEvent) => {
        e.preventDefault()
        const url = import.meta.env.VITE_API_URL
        const data = await axios.get(`${url}flights/sort?priceSort=${sortingForm.priceSort}&durationSort=${sortingForm.durationSort}`)
        const flightResponses: FlightRespose[] = data.data
        setFlights(flightResponses)
    }

    return (
        <div className="w-full h-fit flex flex-col gap-4 items-center">
            <BaseButton htmlType="button" text="Return to Search" className="w-1/2 !bg-zinc-700 !text-white shadow hover:!bg-zinc-500 
                        hover:!border-zinc-500 hover:!text-white" onClick={onClick} size="large"
            />
            <form className="h-fit w-full bg-stone-800 text-white flex flex-col gap-4 p-4 items-center justify-center" onSubmit={onSort}>
                <h2 className="text-2xl font-extrabold">Sort your results</h2>
                <InputSelect
                    name="priceSort"
                    label="Price"
                    id="priceSort"
                    options={[
                        { label: "Origin", value: '' },
                        { label: "Ascending", value: 'asc' },
                        { label: "Descending", value: 'desc' },
                    ]}
                    onChange={(value) => onChangeForm(value, "priceSort")}
                    value={sortingForm.priceSort}
                    className="w-1/2"
                    size="large"
                    defaultValue=""
                />
                <InputSelect
                    name="durationSort"
                    label="Duration"
                    id="durationSort"
                    options={[
                        { label: "Origin", value: '' },
                        { label: "Ascending", value: 'asc' },
                        { label: "Descending", value: 'desc' },
                    ]}
                    onChange={(value) => onChangeForm(value, "durationSort")}
                    value={sortingForm.durationSort}
                    defaultValue=""
                    className="w-1/2"
                    size="large"
                />
                <BaseButton htmlType="submit" text="Sort" className="   w-1/4 !bg-white !text-black shadow hover:!bg-zinc-300 
                        hover:!border-zinc-500" size="large"
                />
            </form>

            {
                flights.length > 0 && flights[0].individualFlights.length === 1 ?
                <>
                    {
                        flights.map((flight: FlightRespose) => (
                            <>
                                {
                                    flight.individualFlights.map((individualFlight: IndividualFlight, index: number) => (
                                        <FlightCard
                                            {...individualFlight}
                                            id={flight.id}
                                            priceTotal={flight.priceTotal}
                                            pricePerTraveler={flight.pricePerTraveler}
                                            totalDuration={flight.totalDuration}
                                            key={index}
                                        />
                                    ))
                                }
                            </>
                        ))
                    }
                </>
                : flights.length > 0 && flights[0].individualFlights.length > 1 &&
                <>
                    {
                        flights.map((flight: FlightRespose, index: number) => (
                            <FlightRoundedCard key={index} {...flight} />
                        ))
                    }
                </>

            }
        </div>
    )
}

export default FlightResults