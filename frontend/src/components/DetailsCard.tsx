import { Amenities, Stops } from "../interfaces/types"

const DetailsCard = ({
    departureAirportName,
    departureAirportCode,
    arrivalAirportName,
    arrivalAirportCode,
    departureTime,
    arrivalTime,
    durationTravel,
    airlineCode,
    airlineName,
    flightNumber,
    carrierAirlineCode,
    carrierAirlineName,
    aircraft,
    fareDetailsBySegment,
    segment
}: Stops) => {
    const departureDateFormat = departureTime.split("T")
    const arrivalDateFormat = arrivalTime.split("T")

    return (
        <div className='w-11/12 min-h-60 border-2 shadow border-black my-4 p-4 flex gap-2'>
            <div className='w-3/4 h-full p-4'>
                <p className='font-bold text-lg'>Segment {segment}</p>
                <p>{`${departureDateFormat[0]} (${departureDateFormat[1]})`} - {`${arrivalDateFormat[0]} (${arrivalDateFormat[1]})`}</p>
                <p className="font-bold">{`${departureAirportName} (${departureAirportCode}) - ${arrivalAirportName} (${arrivalAirportCode})`}</p>
                <p className="text-sm font-semibold mt-4">{`${airlineName} (${airlineCode})`}</p>
                {
                    carrierAirlineCode !== airlineCode &&
                    <p className="text-sm font-semibold">{`Operating: ${carrierAirlineName} (${carrierAirlineCode})`}</p>
                }
                <p className=" text-sm">{durationTravel.replace("PT", "").replace("H", " hrs ").replace("M", " min")}</p>
                <p className="text-sm">Aircraft {aircraft}</p>
                <p className="text-sm">Flight number: {flightNumber}</p>
            </div>
            <div className='w-1/4 h-full border border-black p-2'>
                <p className="font-bold"> Fare Details</p>
                <p>Cabin: {fareDetailsBySegment.cabin}</p>
                <p>Class: {fareDetailsBySegment.class}</p>
                {
                    fareDetailsBySegment.amenities &&
                    <div>
                        <h3 className="font-bold text-sm">AMENITIES</h3>
                        {fareDetailsBySegment.amenities.map((amenity: Amenities, index: number) => (
                        <div key={index}>
                            <p className={`text-sm ${amenity.isChargeable && 'text-green-500'}`}>
                                {amenity.description}
                            </p>
                            <p className="text-sm"> {amenity.isChargeable}</p>
                        </div>
                    ))}
                    </div>
                }
            </div>
        </div>
    )
}

export default DetailsCard