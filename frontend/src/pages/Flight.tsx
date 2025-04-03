import { useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { GlobalContext } from '../context/GlobalContext';
import { FlightRespose, Stops } from '../interfaces/types';
import DetailsCard from '../components/DetailsCard';
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
            <div className='w-full min-h-full flex'>
                <div className='w-3/4 flex flex-col min-h-full items-center p-4'>
                    {
                        flight?.stops.map((stop: Stops, index: number) => (
                            <DetailsCard {...stop} key={index} segment={index + 1} />
                        ))
                    }

                </div>
                <div className='w-1/4 min-h-full border border-black flex flex-col p-4 gap-4'>
                    <h3 className="font-bold text-lg">Price Breakdown</h3>
                    <div>
                        <p className='text-sm'><span className='font-bold'>Base: </span> {flight?.pricePerTraveler}</p>
                    </div>
                    <div>
                        <p className='text-sm'><span className='font-bold'>Total: </span> {flight?.priceTotal}</p>
                    </div>
                </div>
            </div>
        </div>

    )
}

export default Flight