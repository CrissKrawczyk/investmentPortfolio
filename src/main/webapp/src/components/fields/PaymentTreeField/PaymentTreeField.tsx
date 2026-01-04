import { useTranslation } from "react-i18next"
import { ParentTreeNode } from "./ParentTreeNode"
import { TextField } from "../TextField"
import { PaymentCalculationData } from "../../../forms/PaymentCalculationPage"

export interface PaymentTreeData {
  type: ElementType
  name: string
  percentageValue: number
  id: number
  value: number
  items: PaymentTreeData[]
}

export type ElementType = 'group' | 'account' | 'position' | 'portfolio'

export interface PaymentTreeFieldProps {
  value: PaymentCalculationData
  setValue: (data: PaymentCalculationData) => void
}

export const PaymentTreeField = ({ value, setValue }: PaymentTreeFieldProps) => {
  const { t } = useTranslation("PaymentTreeField")

  return (
    <div>
      <TextField setValue={(v) => setValue({ ...value, paymentValue: v })} value={value.paymentValue} idn="paymentValue" type="text" params={undefined} />
      {t('portfolio')}
      <ParentTreeNode item={value.data} setValue={(v) => setValue({ ...value, data: v })} />
    </div>
  )
}
