import { Select } from "antd"
import { SizeType } from "antd/es/config-provider/SizeContext"

interface Choice {
    value: string
    label: string
}
interface SelectInputProps {
    options: Choice[]
    defaultValue?: string
    name: string
    id: string
    label: string
    required?: boolean
    onChange: (value: string) => void
    size?: SizeType
    value?: string
    className?: string
}

const InputSelect = ({
    id,
    label,
    onChange,
    name,
    options,
    defaultValue,
    size,
    value,
    className,
    required = false
}: SelectInputProps) => {
    return (
        <div className="flex gap-4 mt-4 items-start justify-start w-full">
            <label htmlFor={id} className="w-1/4">{label} {required && <span className='text-red-500'>*</span>}</label>
            <Select
                title={name}
                id={id}
                onChange={onChange}
                options={options}
                defaultValue={defaultValue}
                size={size}
                value={value}
                className={className}
            />
        </div>
    )
}

export default InputSelect