import { Checkbox, CheckboxChangeEvent } from "antd"

interface BaseCheckboxProps {
    originChecked?: boolean
    id?: string
    label: string
    onChange: (e: CheckboxChangeEvent) => void
}


const InputCheckbox = ({
    id,
    label,
    originChecked,
    onChange
}: BaseCheckboxProps) => {
    return (
        <div className="flex gap-4 mt-4 items-start justify-start w-full">
            <label htmlFor={id} className="w-1/4"> {label}</label>
            <Checkbox
                checked={originChecked}
                onChange={onChange}
            />
        </div>
    )
}

export default InputCheckbox