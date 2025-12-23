import { useNavigate } from "react-router"
import { useRegisterMutation } from "./api/investment-portfolio-api"
import { User } from "./model/user"
import { FromContextProvider } from "./store/form-context"

export const RegisterPage = () => {
  const [register] = useRegisterMutation()
  const navigate = useNavigate()

  const registerHandler = async (data: User) => {
    const result = await register(data)
    if (result.data) {
      navigate("/login")
      return true
    }
    return false
  }

  return (
    <div>
      <FromContextProvider<User>
        formIdn="RegisterPage"
        fields={[
          {
            idn: "login",
            type: "text",
            params: undefined,
          },
          {
            idn: "forename",
            type: "text",
            params: undefined,
          },
          {
            idn: "surname",
            type: "text",
            params: undefined,
          },
          {
            idn: "password",
            type: "password",
            params: { minLength: 8 },
          },
        ]}
        confirmHandler={registerHandler}
      ></FromContextProvider>
    </div>
  )
}
