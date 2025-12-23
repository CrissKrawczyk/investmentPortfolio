import { TextField as FieldBase } from "@mui/material"
import { FieldProps, useFormContext } from "../../store/form-context"

export interface TextProps extends FieldProps<undefined> {}

export const TextField = ({ idn }: TextProps) => {
  const { setValue, getValue } = useFormContext()
  const value = getValue(idn)

  return (
    <FieldBase
      value={value}
      onChange={v => setValue(idn, v.currentTarget.value)}
    />
  )
}
