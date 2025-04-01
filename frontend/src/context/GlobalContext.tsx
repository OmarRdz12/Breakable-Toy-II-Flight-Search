import React, { createContext, Dispatch, SetStateAction, useState } from "react"
import { FlightRespose, SearchForm, SortingForm } from "../interfaces/types"

interface GlobalContextType {
    flights: FlightRespose[]
    searchForm: SearchForm
    sortingForm: SortingForm
    setFlights: Dispatch<SetStateAction<FlightRespose[]>>
    setSearchForm: Dispatch<SetStateAction<SearchForm>>
    setSortingForm: Dispatch<SetStateAction<SortingForm>>
}

export const GlobalContext = createContext<GlobalContextType | undefined>(undefined)

const GlobalContextProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [searchForm, setSearchForm] = useState<SearchForm>({ originLocationCode: "", destinationCode: "", departureDate: "", arrivalDate: "", currency: "", adults: 1, nonStop: false })
    const [flights, setFlights] = useState<FlightRespose[]>([])
    const [sortingForm, setSortingForm] = useState<SortingForm>({priceSort: "", durationSort: "" })

    return (
        <GlobalContext.Provider value={{
            searchForm,
            setSearchForm,
            flights,
            setFlights,
            setSortingForm,
            sortingForm
        }}>
            {children}
        </GlobalContext.Provider>
    )
}

export default GlobalContextProvider