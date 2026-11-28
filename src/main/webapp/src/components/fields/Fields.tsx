import { useTranslation } from "react-i18next"
import { FormFieldProps, useFormContext } from "../../store/form-context"
import { PasswordField } from "./PasswordField"
import { TextField } from "./TextField"
import { FieldError } from "./FieldError"
import { NumericField } from "./NumericField"
import { SingleSelectField } from "./SingleSelectField"
import { DateField } from "./DateField"

export const Field = (props: FormFieldProps<any>) => {
  const { formIdn, getValue, setValue, getError } = useFormContext()
  const { t } = useTranslation(formIdn)
  const idn = props.idn
  const value = getValue(idn)
  const setValueHandler = (value: string) => setValue(idn, value)
  const error = getError(idn)

  let field
  switch (props.type) {
    case "text":
      field = <TextField value={value} setValue={setValueHandler} {...props} />
      break
    case "password":
      field = <PasswordField {...props} />
      break
    case "numeric":
      field = <NumericField value={value} setValue={setValueHandler} {...props} />
      break
    case "singleSelect":
      field = <SingleSelectField value={value} setValue={setValueHandler} {...props} />
      break
    case "date":
      field = <DateField value={value} setValue={setValueHandler} {...props} />
      break
  }

  return (
    <div>
      <h3>{t(`fields.${props.idn}.label`)}</h3>
      {field}
      <FieldError fieldIdn={idn} formIdn={formIdn} error={error} />
    </div>
  )
}
