import { useNavigate } from "react-router"
import { useRegisterMutation } from "../api/investment-portfolio-api"
import { User } from "../model/user"
import { FromContextProvider } from "../store/form-context"

export const RegisterPage = () => {
  const [register] = useRegisterMutation()
  const navigate = useNavigate()

  const registerHandler = async (data: User) => {
    const result = await register(data)
    if (result.data && result.data.valid) {
      navigate("/login")
    } else if (result.data?.errors) {
      return result.data.errors
    }
  }

  return (
    <div>
      <FromContextProvider<User>
        formIdn="RegisterPage"
        fields={[
          {
            idn: "login",
            type: "text",
            required: true,
            params: undefined,
          },
          {
            idn: "forename",
            type: "text",
            required: true,
            params: undefined,
          },
          {
            idn: "surname",
            type: "text",
            required: true,
            params: undefined,
          },
          {
            idn: "password",
            type: "password",
            required: true,
            params: undefined,
          },
        ]}
        confirmHandler={registerHandler}
      ></FromContextProvider>
    </div>
  )
}
