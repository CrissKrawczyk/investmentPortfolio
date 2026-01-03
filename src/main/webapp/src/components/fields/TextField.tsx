import { TextField as FieldBase } from "@mui/material"
import { FieldProps } from "../../store/form-context"

export interface TextProps extends FieldProps<undefined> { }

export const TextField = (props: TextProps) => {
  const { setValue, value } = props

  return (
    <FieldBase
      value={value}
      onChange={v => setValue(v.currentTarget.value)}
    />
  )
}
