import { useTranslation } from "react-i18next"
import { useNavigate, useParams } from "react-router"
import { useDeleteTestPortfolioMutation, useGetPortfolioDataQuery } from "../api/investment-portfolio-api"

export const PortfolioPage = () => {
  const { portfolioId } = useParams()
  const { data } = useGetPortfolioDataQuery(portfolioId)
  const { t } = useTranslation("MainPage")
  const navigate = useNavigate()
  const [deletePortfolio] = useDeleteTestPortfolioMutation()

  if (!data) {
    return
  }


  const portfolio = data.portfolio
  const accounts = portfolio.accounts
  const groups = portfolio.groups
  const otherPortfolios = data.otherPortfolios

  const isTestPortfolio = portfolio.testPortfolio
  const portfolioInfo = isTestPortfolio ? t('otherPortfolio', { name: portfolio.name }) : t('mainPortfolio')

  const moveToAccountCreation = () => {
    navigate(`accountCreate`)
  }

  const moveToGroupCreation = () => {
    navigate(`groupCreate`)
  }

  const moveToGroup = (id: number) => {
    navigate(`group/${id}`)
  }

  const moveToAccount = (id: number) => {
    navigate(`account/${id}`)
  }

  const moveToPaymentCalculationPage = () => {
    navigate(`paymentCalculation`)
  }

  const moveToPortfolio = (id: number) => {
    navigate(`/portfolio/${id}`)
  }

  const moveToTestPortfolioCreation = () => {
    navigate("/testPortfolioCreate")
  }

  const deletePortfolioHandler = async () => {
    const result = await deletePortfolio(portfolio.id)
    if (result.data !== undefined) {
      navigate("/")
    }
  }

  return (
    <div>
      <div>{portfolioInfo}</div>
      {accounts.map(account => (
        <div onClick={() => moveToAccount(account.id)} key={`account-${account.name}`}>
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
      <button onClick={moveToPaymentCalculationPage}>{t("calculatePayment")}</button>
      <div>{t('otherPortfolios')}</div>
      {otherPortfolios.map(other => (
        <div onClick={() => moveToPortfolio(other.id)} key={`portfolio-${other.id}`}>
          {other.testPortfolio ? other.name : t('mainUserPortfolio')}
        </div>
      ))}
      <button onClick={moveToTestPortfolioCreation}>{t("createTestPortfolio")}</button>
      {portfolio.testPortfolio && <button onClick={deletePortfolioHandler}>{t("deletePortfolio")}</button>}
    </div>
  )
}
