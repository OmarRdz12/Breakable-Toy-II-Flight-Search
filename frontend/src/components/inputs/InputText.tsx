import { Input } from "antd"
import { TextInputProps } from "../../interfaces/types"

const InputText = ({
    name,
    placeholder,
    id,
    value,
    className,
    onChange,
    size,
    prefix,
    type = "text",
    label,
    required = false,
    maxLength,
    showCount = false,
    variant
}: TextInputProps) => {
    return (
        <div className="flex gap-4 mt-4 items-start justify-start w-full">
            <label htmlFor={id} className="w-1/4"> {label}</label>
            <Input
                name={name}
                placeholder={placeholder}
                id={id}
                className={`!w-1/2 ${className}`}
                value={value}
                onChange={onChange}
                size={size}
                prefix={prefix}
                type={type}
                maxLength={maxLength}
                showCount={showCount}
                variant={variant}
                min={1}
            />
        </div>
    )
}

export default InputText