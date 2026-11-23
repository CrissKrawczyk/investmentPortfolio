import { useNavigate } from "react-router"
import { AccountFormData } from "../model/account"
import { FromContextProvider } from "../store/form-context"
import { useCreateTestPortfolioMutation } from "../api/investment-portfolio-api"

export const CreateTestPortfolio = () => {
  const [createPortfolio] = useCreateTestPortfolioMutation()
  const navigate = useNavigate()

  const createAccountHandler = async (data: AccountFormData) => {
    let result
    result = await createPortfolio(data)
    if (result.data && result.data.valid) {
      navigate(`/portfolio/${result.data.object.id}`)
    } else if (result.data?.errors) {
      return result.data.errors
    }
  }

  return (
    <div>
      <FromContextProvider<AccountFormData>
        formIdn="TestPortfolioForm"
        fields={[
          {
            idn: "name",
            type: "text",
            required: true,
            params: undefined,
          },
        ]}
        confirmHandler={createAccountHandler}
      ></FromContextProvider>
    </div>
  )
}
