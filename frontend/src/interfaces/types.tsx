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

export interface TextInputProps {
    name: string
    placeholder?: string
    id: string
    value?: string
    className?: string
    onChange?: (e: React.ChangeEvent<HTMLInputElement> ) => void 
    size?: SizeType
    prefix?: React.ReactNode
    type: string
    label: string
    required?: boolean
    maxLength?: number
    showCount?: boolean
    variant?: "outlined" | "borderless" | "filled" | undefined
}