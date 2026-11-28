import { useTranslation } from "react-i18next"
import { useNavigate, useParams } from "react-router"
import { useDeleteAccountMutation, useGetAccountQuery } from "../api/account-api"
import { PositionElement } from "../molecules/PositionElement/PositionElement"
import { DeleteButton } from "../atoms/DeleteButton"

export const AccountPage = () => {
  const { accountId } = useParams()
  const { data: account } = useGetAccountQuery(accountId ?? 0)
  const { t } = useTranslation("AccountPage")
  const navigate = useNavigate()
  const [deleteAccount] = useDeleteAccountMutation()

  const moveToPositionCreation = () => {
    navigate(`positionCreate`)
  }

  if (!account) {
    return
  }

  const deleteAccountHandler = async () => {
    const result = await deleteAccount(account.id)
    if (result.data !== undefined) {
      navigate("..")
    }
  }

  const positions = account.positions

  return (
    <div>
      <h1>{t("title", { accountName: account.name })}</h1>
      {positions.map(position => (
        <PositionElement key={`position-${position.name}`} position={position} />
      ))}
      <button onClick={moveToPositionCreation}>{t("addPositionButton")}</button>
      <DeleteButton deleteHandler={deleteAccountHandler} label={t("deleteAccount")} />
    </div>
  )
}
