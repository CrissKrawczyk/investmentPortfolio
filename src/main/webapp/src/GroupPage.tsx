import { useTranslation } from "react-i18next"
import { useNavigate, useParams } from "react-router"
import { useGetGroupQuery } from "./api/group-api"

export const GroupPage = () => {
  const { groupId } = useParams()
  const { data: group } = useGetGroupQuery(groupId ?? 0)
  const { t } = useTranslation("GroupPage")
  const navigate = useNavigate()

  const moveToAccountCreation = () => {
    navigate(`/accountCreate/${groupId}`)
  }

  if (!group) {
    return
  }

  const accounts = group.accounts

  return (
    <div>
      <h1>{t("title", { groupName: group.name })}</h1>
      {accounts.map(account => (
        <div key={`group-account-${account.name}`}>
          {t("account")}
          {account.name}
        </div>
      ))}
      <button onClick={moveToAccountCreation}>{t("addAccountButton")}</button>
    </div>
  )
}
