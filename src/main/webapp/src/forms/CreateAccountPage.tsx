import { useNavigate, useParams } from "react-router"
import { useAddAccountMutation } from "../api/group-api"
import { AccountFormData } from "../model/account"
import { FromContextProvider } from "../store/form-context"
import { useCreateAccountMutation } from "../api/account-api"

export const CreateAccountPage = () => {
  const { groupId } = useParams()
  const [createAccount] = useCreateAccountMutation()
  const [createAccountInGroup] = useAddAccountMutation()
  const navigate = useNavigate()

  const createAccountHandler = async (data: AccountFormData) => {
    let result
    if (groupId) {
      result = await createAccountInGroup({ account: data, groupId })
    } else {
      result = await createAccount(data)
    }
    if (result.data) {
      navigate(groupId ? `/group/${groupId}` : "/")
      return true
    }
    return false
  }

  return (
    <div>
      <FromContextProvider<AccountFormData>
        formIdn="CreateAccountForm"
        fields={[
          {
            idn: "name",
            type: "text",
            params: undefined,
          },
          {
            idn: "valuePercentage",
            type: "text",
            params: undefined,
          },
        ]}
        confirmHandler={createAccountHandler}
      ></FromContextProvider>
    </div>
  )
}
