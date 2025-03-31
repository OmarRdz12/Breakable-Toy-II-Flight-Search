import { useContext } from "react"
import { GlobalContext } from "../context/GlobalContext"
import { FlightRespose } from "../interfaces/types"
import FlightCard from "../components/FlightCard"
import BaseButton from "../components/Button"
import { useNavigate } from "react-router-dom"

const FlightResults = () => {
    const context = useContext(GlobalContext)
    const navigate = useNavigate()
    if (!context) {
        throw new Error('Must be inside a context')
    }
    const { flights } = context

    const onClick = () => {
        navigate("/")
    }
    return (
        <div className="w-full h-screen flex flex-col items-center">
            <BaseButton htmlType="button" text="Return to Search" className="w-1/2 !bg-zinc-700 !text-white shadow hover:!bg-zinc-500 
                        hover:!border-zinc-500 hover:!text-white" onClick={onClick} size="large"
            />

            {
                flights.map((flight: FlightRespose, index: number) => (
                    <FlightCard {...flight} key={index} />
                ))
            }

        </div>
    )
}

export default FlightResults