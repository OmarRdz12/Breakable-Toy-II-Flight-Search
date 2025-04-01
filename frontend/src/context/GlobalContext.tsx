import React, { createContext, Dispatch, SetStateAction, useState } from "react"
import { FlightRespose, SearchForm } from "../interfaces/types"

interface GlobalContextType {
    flights: FlightRespose[]
    searchForm: SearchForm
    setFlights: Dispatch<SetStateAction<FlightRespose[]>>
    setSearchForm: Dispatch<SetStateAction<SearchForm>>
}

export const GlobalContext = createContext<GlobalContextType | undefined>(undefined)

const GlobalContextProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [searchForm, setSearchForm] = useState<SearchForm>({ originLocationCode: "", destinationCode: "", departureDate: "", arrivalDate: "", currency: "", adults: 1, nonStop: false })
    const [flights, setFlights] = useState<FlightRespose[]>([])

    return (
        <GlobalContext.Provider value={{
            searchForm,
            setSearchForm,
            flights,
            setFlights
        }}>
            {children}
        </GlobalContext.Provider>
    )
}

export default GlobalContextProvider