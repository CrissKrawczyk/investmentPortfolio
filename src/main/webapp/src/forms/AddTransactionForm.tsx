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
    if (result.data) {
      onClose()
      return true
    }
    return false
  }

  return (
    <div>
      <FromContextProvider<TransactionFormData>
        formIdn="AddTransactionForm"
        fields={[
          {
            idn: "price",
            type: "text",
            params: undefined,
          },
          {
            idn: "amount",
            type: "text",
            params: undefined,
          },
        ]}
        confirmHandler={addTransaction}
        cancelHandler={onClose}
      ></FromContextProvider>
    </div>
  )
}
