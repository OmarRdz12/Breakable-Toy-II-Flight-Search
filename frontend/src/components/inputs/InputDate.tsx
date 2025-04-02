import { DatePicker } from 'antd'
import { SizeType } from 'antd/es/config-provider/SizeContext'
import dayjs, { Dayjs } from 'dayjs'

interface InputDateProps {
    id: string
    label: string
    required?: boolean
    onChange: (date: any, dateString: string | string[]) => void
    size?: SizeType
    name: string
    value?: string | number | Dayjs | Date | null | undefined
    className?: string
    minDate?: string
    maxDate?: string
}

const InputDate = ({
    id,
    label,
    required = false,
    onChange,
    name,
    size,
    value,
    className,
    minDate,
    maxDate
}: InputDateProps) => {
    return (
        <div className="flex gap-4 mt-4 items-start justify-start w-full">
            <label htmlFor={id} className='w-1/4'>{label} {required && <span className='text-red-500'>*</span>}</label>
            <DatePicker
                title={name}
                id={id}
                onChange={onChange}
                required={required}
                maxDate={maxDate ?  dayjs(maxDate) : undefined}
                minDate={minDate ?  dayjs(minDate) : dayjs(Date.now())}
                size={size}
                value={value}
                className={className}
            />
        </div>
    )
}

export default InputDate