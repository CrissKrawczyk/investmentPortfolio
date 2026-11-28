import { FromContextProvider } from "../store/form-context"
import { TransactionFormData, TransactionType } from "../model/transaction"
import { useBuyMutation, useSellMutation } from "../api/position-api"

interface AddTransactionFormProps {
  positionId: number
  type: TransactionType
  onClose: () => void
}

export const AddTransactionForm = (props: AddTransactionFormProps) => {
  const { onClose, positionId, type } = props
  const [buy] = useBuyMutation()
  const [sell] = useSellMutation()

  const addTransaction = async (data: TransactionFormData) => {
    const addingFunction = type === 'Sale' ? sell : buy
    const result = await addingFunction({ transaction: data, positionId })
    if (result.data && result.data.valid) {
      onClose()
    } else if (result.data?.errors) {
      return result.data.errors
    }
  }

  return (
    <div>
      <FromContextProvider<TransactionFormData>
        formIdn="AddTransactionForm"
        fields={[
          {
            idn: "price",
            type: "numeric",
            params: {
              decimalDigits: 2
            },
          },
          {
            idn: "amount",
            type: "numeric",
            params: {
              decimalDigits: 2
            },
          },
          {
            idn: "date",
            type: "date",
            params: {}
          },
        ]}
        confirmHandler={addTransaction}
        cancelHandler={onClose}
      ></FromContextProvider>
    </div>
  )
}
