import { useTranslation } from "react-i18next"
import { FormFieldProps, useFormContext } from "../../store/form-context"
import { PasswordField } from "./PasswordField"
import { TextField } from "./TextField"

export const Field = (props: FormFieldProps<any>) => {
  const { formIdn, getValue, setValue } = useFormContext()
  const { t } = useTranslation(formIdn)
  const idn = props.idn
  const value = getValue(idn)
  const setValueHandler = (value: string) => setValue(idn, value)

  let field
  switch (props.type) {
    case "text":
      field = <TextField value={value} setValue={setValueHandler} {...props} />
      break
    case "password":
      field = <PasswordField {...props} />
      break
  }

  return (
    <div>
      <h3>{t(`fields.${props.idn}`)}</h3>
      {field}
    </div>
  )
}
