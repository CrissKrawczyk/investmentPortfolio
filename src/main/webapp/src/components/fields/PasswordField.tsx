import { TextField } from "@mui/material"
import { FieldProps, useFormContext } from "../../store/form-context"

export interface PasswordProps extends FieldProps<PasswordParams> {}
export interface PasswordParams {
  minLength: number
}
export const PasswordField = ({ idn }: PasswordProps) => {
  const { setValue, getValue } = useFormContext()
  const value = getValue(idn)

  return (
    <TextField
      type="password"
      value={value}
      onChange={v => setValue(idn, v.currentTarget.value)}
    />
  )
}
