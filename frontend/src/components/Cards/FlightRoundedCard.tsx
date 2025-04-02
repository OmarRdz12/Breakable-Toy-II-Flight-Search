import { useNavigate } from "react-router-dom"
import { FlightRespose, IndividualFlight } from "../../interfaces/types"

const FlightRoundedCard = ({
    id,
    individualFlights,
    priceTotal,
    pricePerTraveler
}: FlightRespose) => {
    const navigate = useNavigate()
    const currency = individualFlights[0].currency
    const onClick = () => {
        navigate(`/flights/${id}`)
    }

    return (
        <div className="w-11/12 flex border border-black rounded ">
            <div className="w-full h-fit flex-col hover:cursor-pointer">
                {
                    individualFlights.map((individualFlight: IndividualFlight, index: number) => (
                        <div key={index} className="grid grid-cols-2 w-full border shadow rounded p-4 items-center justify-center" onClick={onClick}>
                            <p>{`${individualFlight.departureDate.split("T")[0]} (${individualFlight.departureDate.split("T")[1]})`} - {`${individualFlight.arrivalDateTime.split("T")[0]} (${individualFlight.arrivalDateTime.split("T")[1]})`}</p>
                            <p className="text-center text-sm">{individualFlight.duration.replace("PT", "").replace("H", " hrs ").replace("M", " min")}</p>
                            <p className="font-bold">{`${individualFlight.departureAirportName} (${individualFlight.departureAirport}) - ${individualFlight.arrivalAirportName} (${individualFlight.arrivalAirport})`}</p>
                            <p></p>
                            <p className="text-sm font-semibold mt-4">{`${individualFlight.airlineName} (${individualFlight.airlineCode})`}</p>
                        </div>
                    ))
                }
            </div>
            <div className="w-1/4 h-full border border-black p-4 flex flex-col gap-4 rounded-r">
                <p>${priceTotal} {currency} total</p>
                <p>${pricePerTraveler} {currency} per traveler</p>
            </div>
        </div>
    )
}

export default FlightRoundedCard