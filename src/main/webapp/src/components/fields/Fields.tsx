import { useTranslation } from "react-i18next"
import { FieldProps, useFormContext } from "../../store/form-context"
import { PasswordField } from "./PasswordField"
import { TextField } from "./TextField"

export const Field = (props: FieldProps<any>) => {
  const { formIdn } = useFormContext()
  const { t } = useTranslation(formIdn)

  let field
  switch (props.type) {
    case "text":
      field = <TextField {...props} />
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
