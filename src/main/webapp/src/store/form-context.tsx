import { PropsWithChildren, createContext, useContext, useState } from "react"
import { useTranslation } from "react-i18next"
import { Field } from "../components/fields/Fields"

export type FieldType = "text" | "password"

export interface FieldProps<P> {
  idn: string
  type: FieldType
  params: P
}

export type DataType = Record<string, string | undefined>

interface FormContextData<T extends DataType> {
  data: T
  formIdn: string
  setValue: (key: string, value: string) => void
  getValue: (key: string) => string
  setError: (key: string, error: string) => void
  getError: (key: string, error: string) => void
}

const FromContext = createContext<FormContextData<any>>(
  {} as FormContextData<any>,
)

interface FromContextProviderProps<
  T extends DataType,
> extends PropsWithChildren {
  fields: FieldProps<any>[]
  confirmHandler: (data: T) => Promise<boolean>
  cancelHandler?: () => void
  formIdn: string
}

export const FromContextProvider = <T extends DataType>({
  fields,
  confirmHandler,
  cancelHandler,
  formIdn,
}: FromContextProviderProps<T>) => {
  const [data, setData] = useState<T>({} as T)
  const [errors, setErrors] = useState<Map<string, string>>(new Map())
  const { t } = useTranslation(formIdn)

  const setValue = (key: keyof T, value: string) => {
    const old = data[key]
    data[key] = value as typeof old
    setData({ ...data })
  }

  const getValue = (key: keyof T) => {
    return data[key] || ""
  }

  const setError = (key: string, error: string) => {
    errors.set(key, error)
    setErrors(new Map(errors))
  }

  const getError = (key: string) => {
    return errors.get(key)
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
        <button onClick={() => confirmHandler(data)}>
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
