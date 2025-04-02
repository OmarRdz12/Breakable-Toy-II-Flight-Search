import { SizeType } from "antd/es/config-provider/SizeContext"
export interface BasicSelect {
    label: string
    value: string
}

export interface SearchForm {
    originLocationCode: string
    destinationCode: string
    departureDate: string
    arrivalDate: string
    currency: string
    adults: number
    nonStop: boolean
}

export interface SortingForm {
    priceSort: string,
    durationSort: string
}

export interface TextInputProps {
    name: string
    placeholder?: string
    id: string
    value?: string
    className?: string
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void
    size?: SizeType
    prefix?: React.ReactNode
    type: string
    label: string
    required?: boolean
    maxLength?: number
    showCount?: boolean
    variant?: "outlined" | "borderless" | "filled" | undefined
}

export interface Amenities {
    description: string
    isChargeable: boolean
}

export interface FareDetailsBySegment {
    segmentId: number
    cabin: string
    class: string
    amenities: Amenities[]
}

export interface Stops {
    departureAirportName: string
    departureAirportCode: string
    arrivalAirportName: string
    arrivalAirportCode: string
    departureTime: string
    arrivalTime: string
    durationTravel: string
    airlineCode: string
    airlineName: string
    flightNumber: string
    carrierAirlineCode: string
    carrierAirlineName: string
    aircraft: string
    id: string
    segment?: number
    fareDetailsBySegment: FareDetailsBySegment
}

export interface IndividualFlight {
    airlineCode: string
    airlineName: string
    arrivalAirport: string
    arrivalAirportName: string
    arrivalDateTime: string
    carrierCode: string
    carrierName: string 
    departureAirport: string
    departureAirportName: string
    departureDate: string
    duration: string
    currency: string
    stops: Stops[]
}

export interface FlightRespose {
    id: string
    pricePerTraveler: string
    priceTotal: string
    totalDuration: string
    individualFlights: IndividualFlight[]
}