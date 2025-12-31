import { useNavigate, useParams } from "react-router"
import { useAddAccountMutation } from "./api/group-api"
import { useCreateAccountMutation } from "./api/investment-portfolio-api"
import { Account } from "./model/account"
import { FromContextProvider } from "./store/form-context"

export const CreateAccountPage = () => {
  const { groupId } = useParams()
  const [createAccount] = useCreateAccountMutation()
  const [createAccountInGroup] = useAddAccountMutation()
  const navigate = useNavigate()

  const createAccountHandler = async (data: Account) => {
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
      <FromContextProvider<Account>
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
