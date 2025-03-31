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
    departureAirportName
}:FlightRespose) => {
  return (
    <div className="w-11/12 h-52 flex flex-col">
        <div className="grid grid-cols-3 w-full border shadow rounded p-4 items-center">
            <p>{departureDate}-{arrivalDateTime}</p>
            <p>{duration}</p>
            <p>${priceTotal}MXN</p>
            <p>{`${departureAirportName} (${departureAirport}) - ${arrivalAirportName} (${arrivalAirport})`}</p>
            <p></p>
            <p>${pricePerTraveler}MXN per traveler</p>
        </div>
    </div>
  )
}

export default FlightCard