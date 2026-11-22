import { useTranslation } from "react-i18next"

interface FieldErrorProps {
  formIdn: string
  fieldIdn: string
  error: string | undefined
}

export const FieldError = (props: FieldErrorProps) => {
  const { error, fieldIdn, formIdn } = props
  const { t: formT } = useTranslation(formIdn)
  const { t: commonT } = useTranslation("Common")

  if (!error) {
    return null;
  }

  if (error === 'required') {
    return <h5>{commonT(`fields.required`)}</h5>
  }

  return (
    <div>
      {error?.split("|").map(e =>
        <h5>{formT(`fields.${fieldIdn}.error.${e}`)}</h5>
      )}
    </div>
  )

}
