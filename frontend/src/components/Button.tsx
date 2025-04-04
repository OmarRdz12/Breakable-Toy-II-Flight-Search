import { Tooltip } from "antd"
import Button, { ButtonShape } from "antd/es/button"
import { SizeType } from "antd/es/config-provider/SizeContext"
import { ReactNode } from "react"

interface ButtonProps {
    htmlType: "button" | "submit" | "reset" | undefined
    text: string
    size?: SizeType
    className?: string
    onClick?: () => void
    toolTip?: boolean
    shape?: ButtonShape
    icon?: ReactNode
    disabled?: boolean
    id?: string
    loading?: boolean
}

const BaseButton = ({
    htmlType,
    text,
    size,
    className,
    onClick,
    toolTip,
    shape,
    icon,
    disabled,
    id,
    loading
}: ButtonProps) => {
    return (
        <>
            {
                toolTip ?
                    <Tooltip title={text}>
                        <Button
                            shape={shape}
                            icon={icon}
                            onClick={onClick}
                            className={className}
                            disabled={disabled}
                            id={id}
                            loading={loading}
                        />
                    </Tooltip>
                    :
                    <Button
                        htmlType={htmlType}
                        size={size}
                        className={className}
                        onClick={onClick}
                        disabled={disabled}
                        id={id}
                        icon={icon}
                        loading={loading}
                    >
                        {text}
                    </Button >
            }
        </>
    )
}

export default BaseButton