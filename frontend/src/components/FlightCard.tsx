import { useNavigate } from "react-router-dom"
import { FlightRespose } from "../interfaces/types"

const FlightCard = ({
    departureDate,
    arrivalDateTime,
    pricePerTraveler,
    priceTotal,
    duration,
    arrivalAirport,
    departureAirport,
    arrivalAirportName,
    departureAirportName,
    airlineCode,
    airlineName,
    currency,
    id
}: FlightRespose) => {
    const navigate = useNavigate()
    const departureDateFormat = departureDate.split("T")
    const arrivalDateFormat = arrivalDateTime.split("T")

    const onClick = () => {
        navigate(`/flights/${id}`)
    }

    return (
        <div className="w-11/12 h-fit flex flex-col hover:cursor-pointer" onClick={onClick}>
            <div className="grid grid-cols-3 w-full border shadow rounded p-4 items-cente justify-center">
                <p>{`${departureDateFormat[0]} (${departureDateFormat[1]})`} - {`${arrivalDateFormat[0]} (${arrivalDateFormat[1]})`}</p>
                <p className="text-center text-sm">{duration.replace("PT", "").replace("H", " hrs ").replace("M", " min")}</p>
                <p>${priceTotal} {currency}</p>
                <p className="font-bold">{`${departureAirportName} (${departureAirport}) - ${arrivalAirportName} (${arrivalAirport})`}</p>
                <p></p>
                <p>${pricePerTraveler} {currency} per traveler</p>
                <p className="text-sm font-semibold mt-4">{`${airlineName} (${airlineCode})`}</p>
            </div>
        </div>
    )
}

export default FlightCard