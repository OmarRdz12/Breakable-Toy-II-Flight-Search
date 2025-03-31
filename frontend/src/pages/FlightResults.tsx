import { useContext } from "react"
import { GlobalContext } from "../context/GlobalContext"
import { FlightRespose } from "../interfaces/types"
import FlightCard from "../components/FlightCard"

const FlightResults = () => {
    const context = useContext(GlobalContext)
    if (!context) {
        throw new Error('Must be inside a context')
    }
    const { flights } = context
    return (
        <div className="w-screen h-screen flex flex-col items-center">
            {
                flights.map((flight: FlightRespose, index: number) => (
                    <FlightCard {... flight} key={index}/> 
                ))
            }

        </div>
    )
}

export default FlightResults