import { PropsWithChildren, createContext, useContext, useState } from "react"
import { useTranslation } from "react-i18next"
import { Field } from "../components/fields/Fields"

export type FieldType = "text" | "password" | "numeric"

export interface FieldProps<P> extends FormFieldProps<P> {
  setValue: (value: string) => void
  value: string
}

export interface FormFieldProps<P> {
  idn: string
  type: FieldType
  required?: boolean
  params: P
}

export type DataType = Record<string, string | undefined>

interface FormContextData<T extends DataType> {
  data: T
  formIdn: string
  setValue: (key: string, value: string) => void
  getValue: (key: string) => string
  setError: (key: string, error: string) => void
  getError: (key: string) => string | undefined
}

const FromContext = createContext<FormContextData<any>>(
  {} as FormContextData<any>,
)

interface FromContextProviderProps<
  T extends DataType,
> extends PropsWithChildren {
  fields: FormFieldProps<any>[]
  confirmHandler: (data: T) => Promise<Errors | undefined>
  cancelHandler?: () => void
  formIdn: string
}

interface Errors {
  [field: string]: string
}

export const FromContextProvider = <T extends DataType>({
  fields,
  confirmHandler,
  cancelHandler,
  formIdn,
}: FromContextProviderProps<T>) => {
  const [data, setData] = useState<T>({} as T)
  const [errors, setErrors] = useState<Errors>({})
  const { t } = useTranslation(formIdn)

  const setValue = (key: keyof T, value: string) => {
    const old = data[key]
    data[key] = value as typeof old
    setData({ ...data })
    delete errors[key as string]
  }

  const getValue = (key: keyof T) => {
    return data[key] || ""
  }

  const setError = (key: string, error: string) => {
    errors[key] = error
    setErrors({ ...errors })
  }

  const getError = (key: string) => {
    return errors[key]
  }

  const onConfirm = async () => {
    let errors: Errors | undefined = {}
    let hasError = false
    for (const field of fields) {
      if (field.required && !data[field.idn]) {
        errors[field.idn] = "required"
        hasError = true
      }
    }
    if (hasError) {
      setErrors(errors)
      return
    }
    errors = await confirmHandler(data)
    if (errors) {
      setErrors(errors)
    } else {
      setErrors({})
    }
  }

  return (
    <FromContext.Provider
      value={{ data, setValue, getValue, setError, getError, formIdn }}
    >
      <h2>{t("formTitle")}</h2>
      {fields.map(f => (
        <Field {...f} />
      ))}
      {
        <button onClick={onConfirm}>
          {t("confirmFormButton")}
        </button>
      }
      {cancelHandler &&
        <button onClick={cancelHandler}>
          {t("cancelFormButton")}
        </button>
      }
    </FromContext.Provider>
  )
}
export const useFormContext = () => useContext(FromContext)
