export interface BasicSelect {
    label: string
    value: string
}

export interface SearchForm {
    originLocationCode: string
    destinationCode: string
    departureDate: string
    adults: number
    nonStop: boolean
}