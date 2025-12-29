import { useEffect } from "react"
import { useTranslation } from "react-i18next"
import { useNavigate } from "react-router"
import { useUserContext } from "./store/user-context"
import { PortfolioPage } from "./PortfolioPage"

export const App = () => {
  const { user } = useUserContext()
  const navigate = useNavigate()
  const { t } = useTranslation("MainPage")

  useEffect(() => {
    if (!user) {
      navigate("/login")
    }
  }, [user])

  if (!user) {
    return
  }

  const caption = user.forename + " " + user.surname

  return <div>{t("welcome", { caption })}<PortfolioPage /></div>
}
