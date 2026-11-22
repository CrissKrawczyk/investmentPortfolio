import { TextField as FieldBase } from "@mui/material"
import { FieldProps } from "../../store/form-context"

export interface NumericFieldParams {
  decimalDigits?: number
}

const getNumericValue = (value: string, decimalDigits: number) => {
  value = value.replace(/[^1-9,.]*/g, "")
    .replace(/,/g, ".")
  if (decimalDigits <= 0) {
    return value.replace(/\./g, "")
  }
  const parts = value.split("\.")
  const partsSize = parts.length
  value = ""
  if (partsSize === 0) {
    return ""
  } else if (partsSize === 1) {
    return parts[0]
  }
  for (let i = 0; i < partsSize - 1; i++) {
    const part = parts[i]
    value += part
  }
  value += "." + parts[partsSize - 1].substring(0, decimalDigits)
  return value
}

export interface NumericFieldProps extends FieldProps<NumericFieldParams | undefined> { }

export const NumericField = (props: NumericFieldProps) => {
  const { setValue, value, params } = props
  const decimalDigits = params?.decimalDigits ?? 0

  const valueChangeHandler = (value: string) => {
    value = getNumericValue(value, decimalDigits)
    setValue(value)
  }

  return (
    <FieldBase
      value={value.replace("\.", ",")}
      onChange={v => valueChangeHandler(v.currentTarget.value)}
    />
  )
}
