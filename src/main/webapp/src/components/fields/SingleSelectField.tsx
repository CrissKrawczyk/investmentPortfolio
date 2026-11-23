import { MenuItem, Select } from "@mui/material"
import { FieldProps } from "../../store/form-context"
import { useGetDictionaryListQuery } from "../../api/investment-portfolio-api"

export interface SingleSelectFieldParams {
  dictIdn: string
}

export interface SingleSelectFieldProps extends FieldProps<SingleSelectFieldParams> { }

export const SingleSelectField = (props: SingleSelectFieldProps) => {
  const { setValue, value, params } = props
  const { data } = useGetDictionaryListQuery(params.dictIdn)

  const valueChangeHandler = (value: string) => {
    setValue(value)
  }

  return (
    <Select
      value={value}
      onChange={v => valueChangeHandler(v.target.value)}
    >
      {data?.list.map(object => <MenuItem value={object.id}>{object.caption}</MenuItem>)}
    </Select>
  )
}
