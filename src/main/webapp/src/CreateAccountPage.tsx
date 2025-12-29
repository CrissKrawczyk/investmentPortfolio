import { useNavigate } from "react-router"
import { useCreateAccountMutation } from "./api/investment-portfolio-api"
import { FromContextProvider } from "./store/form-context"
import { Account } from "./model/account"

export const CreateAccountPage = () => {
  const [createAccount] = useCreateAccountMutation()
  const navigate = useNavigate()

  const createAccountHandler = async (data: Account) => {
    const result = await createAccount(data)
    if (result.data) {
      navigate("/")
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
