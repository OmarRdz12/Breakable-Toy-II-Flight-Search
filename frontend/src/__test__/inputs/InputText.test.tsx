import { render, screen } from '@testing-library/react'
import { describe, it, expect } from 'vitest'
import InputText from '../../components/inputs/InputText'
import { TextInputProps } from '../../interfaces/types'

describe('InputText', () => {
    it('renders with label and input', () => {
        const props: TextInputProps = {
            name: 'test',
            id: 'test-id',
            label: 'Test Label',
            type: "text"
        }
        render(<InputText {...props} />)
        expect(screen.getByLabelText('Test Label')).toBeInTheDocument()
        expect(screen.getByRole('textbox')).toHaveAttribute('id', 'test-id')
    })

    it('shows required asterisk when required is true', () => {
        const props: TextInputProps = {
            name: 'test',
            id: 'test-id',
            label: 'Test Label',
            type: "text",
            required: true
        }
        render(<InputText {...props} />)
        expect(screen.getByText('*')).toHaveClass('text-red-500')
    })

    it('sets input value', () => {
        const props: TextInputProps = {
            name: 'test',
            id: 'test-id',
            label: 'Test Label',
            type: "text",
            value: "test value"
        }
        render(<InputText {...props} />)
        expect(screen.getByDisplayValue('test value')).toBeInTheDocument()
    })

    
})