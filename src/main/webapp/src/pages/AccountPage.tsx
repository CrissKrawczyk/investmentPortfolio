import { useTranslation } from "react-i18next"
import { useNavigate, useParams } from "react-router"
import { useGetAccountQuery } from "../api/account-api"
import { PositionElement } from "../molecules/PositionElement/PositionElement"

export const AccountPage = () => {
  const { accountId } = useParams()
  const { data: account } = useGetAccountQuery(accountId ?? 0)
  const { t } = useTranslation("AccountPage")
  const navigate = useNavigate()

  const moveToPositionCreation = () => {
    navigate(`positionCreate`)
  }

  if (!account) {
    return
  }

  const positions = account.positions

  return (
    <div>
      <h1>{t("title", { accountName: account.name })}</h1>
      {positions.map(position => (
        <PositionElement key={`position-${position.name}`} position={position} />
      ))}
      <button onClick={moveToPositionCreation}>{t("addPositionButton")}</button>
    </div>
  )
}
