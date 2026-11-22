import { useTranslation } from "react-i18next"
import { useNavigate } from "react-router"
import { useLoginMutation } from "./api/investment-portfolio-api"
import { UserLoginData } from "./model/user"
import { FromContextProvider } from "./store/form-context"
import { useUserContext } from "./store/user-context"

export const LoginPage = () => {
  const [login] = useLoginMutation()
  const { setUser } = useUserContext()
  const navigate = useNavigate()
  const { t } = useTranslation("LoginPage")

  const loginHandler = async (data: UserLoginData) => {
    const result = await login(data)
    if (result.data) {
      setUser(result.data.user)
      navigate(`/portfolio/${result.data.portfolioId}`)
    }
    return undefined
  }

  return (
    <div>
      <FromContextProvider<UserLoginData>
        formIdn="LoginPage"
        fields={[
          {
            idn: "name",
            type: "text",
            params: undefined,
          },
          {
            idn: "password",
            type: "password",
            params: {},
          },
        ]}
        confirmHandler={loginHandler}
      ></FromContextProvider>
      <button onClick={() => navigate("/register")}>
        {t("registerButton")}
      </button>
    </div>
  )
}
