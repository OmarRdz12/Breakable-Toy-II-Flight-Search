import { useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { GlobalContext } from '../context/GlobalContext';
import { FlightRespose, IndividualFlight, Stops } from '../interfaces/types';
import DetailsCard from '../components/Cards/DetailsCard';
import BaseButton from '../components/Button';

const Flight = () => {
    const [flight, setFlight] = useState<FlightRespose>()
    const context = useContext(GlobalContext)
    const navigate = useNavigate()
    if (!context) {
        throw new Error('Must be inside a context')
    }
    const { flights } = context
    const { id } = useParams();

    useEffect(() => {
        const filteredFlight = flights.find(flightTemp => flightTemp.id === id)
        setFlight(filteredFlight)
    }, [id, flights])

    const onClick = () => {
        navigate("/flights")
    }

    return (
        <div className='w-full min-h-full flex-col'>
            <BaseButton htmlType="button" text="Return to Results" className="w-1/2 !bg-zinc-700 !text-white shadow hover:!bg-zinc-500 
                        hover:!border-zinc-500 hover:!text-white" onClick={onClick} size="large"
            />
            <div className='w-full h-fit flex items-center'>
                <div className='w-3/4 flex flex-col min-h-full items-center p-4'>
                    {
                        flight?.individualFlights.map((individualFlight: IndividualFlight, index: number) => (
                            <div className='flex flex-col w-full items-center gap-4' key={index}>
                                {
                                    index === 1 ? <p key={100} className='text-xl font-bold'>Return </p> : <p key={100} className='text-xl font-bold'>Going</p>
                                }
                                {
                                    individualFlight.stops.map((stop: Stops, indexInside: number) => (
                                        <DetailsCard {...stop} key={indexInside} segment={index === 2 ? indexInside + 1 - flight.individualFlights[0].stops.length : indexInside + 1} />
                                    ))
                                }
                            </div>
                        ))
                    }
                    
                </div>
                <div className='w-1/4 h-full items-center border border-black flex flex-col p-4 gap-4'>
                    <h3 className="font-bold text-lg">Price Breakdown</h3>
                    <div>
                        <p className='text-sm'><span className='font-bold'>Base: </span> ${flight?.pricePerTraveler}</p>
                    </div>
                    <div>
                        <p className='text-sm'><span className='font-bold'>Total: </span> ${flight?.priceTotal}</p>
                    </div>
                </div>
            </div>
        </div>

    )
}

export default Flight