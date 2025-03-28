import React, { createContext, useState } from "react"
import { SearchForm } from "../interfaces/types"

export const GlobalContext = createContext<any>(null)

const GlobalContextProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [searchForm, setSearchForm] = useState<SearchForm>({ originLocationCode: "", destinationCode: "", departureDate: "", arrivalDate: "", currency: "", adults: 1, nonStop: false })

    return (
        <GlobalContext.Provider value={{
            searchForm,
            setSearchForm
        }}>
            {children}
        </GlobalContext.Provider>
    )
}

export default GlobalContextProvider