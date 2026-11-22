import { useTranslation } from "react-i18next"
import { useNavigate, useParams } from "react-router"
import { useGetGroupQuery } from "../api/group-api"

export const GroupPage = () => {
  const { groupId, portfolioId } = useParams()
  const { data: group } = useGetGroupQuery(groupId ?? 0)
  const { t } = useTranslation("GroupPage")
  const navigate = useNavigate()

  const moveToAccountCreation = () => {
    navigate(`accountCreate`)
  }

  const moveToAccount = (id: number) => {
    navigate(`account/${id}`)
  }

  const moveToGroupCreation = () => {
    navigate(`groupCreate`)
  }

  const moveToGroup = (id: number) => {
    navigate(`/portfolio/${portfolioId}/group/${id}`)
  }

  if (!group) {
    return
  }

  const accounts = group.accounts
  const groups = group.groups

  return (
    <div>
      <h1>{t("title", { groupName: group.name })}</h1>
      <div>{t("accounts")}</div>
      {accounts.map(account => (
        <div onClick={() => moveToAccount(account.id)} key={`group-account-${account.id}`}>
          {t("account")}
          {account.name}
        </div>
      ))}
      <div>{t("groups")}</div>
      {groups.map(group => (
        <div onClick={() => moveToGroup(group.id)} key={`group-account-${group.id}`}>
          {t("group")}
          {group.name}
        </div>
      ))}
      <button onClick={moveToAccountCreation}>{t("addAccountButton")}</button>
      <button onClick={moveToGroupCreation}>{t("addGroupButton")}</button>
    </div>
  )
}
