import React, { useContext, useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { GlobalContext } from '../context/GlobalContext';
import { FlightRespose } from '../interfaces/types';

const Flight = () => {
    const [flight, setFlight] = useState<FlightRespose>()
    const context = useContext(GlobalContext)
    if (!context) {
        throw new Error('Must be inside a context')
    }
    const { flights } = context
    const { id } = useParams();

    useEffect(() => {
        const filteredFlight = flights.find(flightTemp => flightTemp.id === id)
        setFlight(filteredFlight)
    }, [id, flights])

    return (
        <>
            <div>Flight {id}</div>
            <div>{flight?.duration}</div>
        </>

    )
}

export default Flight