import { BrowserRouter, Route, Routes } from "react-router-dom"
import SearchPanel from "./pages/SearchPanel"
import FlightResults from "./pages/FlightResults"
import Flight from "./pages/Flight"

const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/">
                    <Route index element={<SearchPanel />} />
                    <Route path="/flights" element={<FlightResults />} />
                    <Route path="/flights/:id" element={<Flight />} />
                </Route>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes