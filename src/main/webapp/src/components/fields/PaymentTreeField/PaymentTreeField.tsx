import { useTranslation } from "react-i18next"
import { useState } from "react"
import { ParentTreeNode } from "./ParentTreeNode"
import { TextField } from "../TextField"

export interface PaymentTreeData {
  type: ElementType
  name: string
  percentageValue: number
  id: number
  value?: string
  items: PaymentTreeData[]
}

export type ElementType = 'group' | 'account' | 'position' | 'portfolio'

export interface PaymentTreeFieldProps {
  data: PaymentTreeData
}

export const PaymentTreeField = ({ data }: PaymentTreeFieldProps) => {
  const { t } = useTranslation("PaymentTreeField")
  const [value, setValue] = useState<PaymentTreeData>(data)
  const [toPay, setToPay] = useState<string>("")

  return (
    <div>
      <TextField setValue={setToPay} value={toPay} idn="toPay" type="text" params={undefined} />
      {t('portfolio')}
      <ParentTreeNode item={value} setValue={setValue} />
      <button onClick={() => console.log(value)}>{t("confirm")}</button>
    </div>
  )
}
