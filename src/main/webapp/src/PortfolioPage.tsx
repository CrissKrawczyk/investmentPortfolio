import { useTranslation } from "react-i18next"
import { useNavigate } from "react-router"
import { useGetPortfolioDataQuery } from "./api/investment-portfolio-api"

export const PortfolioPage = () => {
  const { data: portfolio } = useGetPortfolioDataQuery()
  const { t } = useTranslation("MainPage")
  const navigate = useNavigate()

  const moveToAccountCreation = () => {
    navigate("/accountCreate")
  }

  const moveToGroupCreation = () => {
    navigate("/groupCreate")
  }

  const moveToGroup = (id: number) => {
    navigate(`/group/${id}`)
  }

  if (!portfolio) {
    return
  }

  const accounts = portfolio.accounts
  const groups = portfolio.groups

  return (
    <div>
      {accounts.map(account => (
        <div key={`account-${account.name}`}>
          {t("account")}
          {account.name}
        </div>
      ))}
      {groups.map(group => (
        <div onClick={() => moveToGroup(group.id)} key={`group-${group.name}`}>
          {t("group")}
          {group.name}
        </div>
      ))}
      <button onClick={moveToAccountCreation}>{t("addAccountButton")}</button>
      <button onClick={moveToGroupCreation}>{t("addGroupButton")}</button>
    </div>
  )
}
