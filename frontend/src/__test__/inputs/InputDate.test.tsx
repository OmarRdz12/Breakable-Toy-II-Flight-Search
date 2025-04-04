import { render, screen } from '@testing-library/react'
import { describe, it, expect, vi } from 'vitest'
import InputDate, { InputDateProps } from '../../components/inputs/InputDate'



describe('InputDate', () => {
    it('renders with label and input', () => {
        const props: InputDateProps = {
            name: 'test',
            id: 'test-id',
            label: 'Test Label',
            onChange: vi.fn()
        }
        render(<InputDate {...props} />)
        const label = screen.getByText('Test Label')
        expect(label).toBeInTheDocument()
        expect(label).toHaveAttribute('for', 'test-id')
        
        const input = screen.getByRole('textbox')
        expect(input).toBeInTheDocument()
    })

    it('shows required asterisk when required is true', () => {
        const props: InputDateProps = {
            name: 'test',
            id: 'test-id',
            label: 'Test Label',
            onChange: vi.fn(),
            required: true
        }
        render(<InputDate {...props} />)
        expect(screen.getByText('*')).toHaveClass('text-red-500')
    })


})