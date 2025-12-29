import { useTranslation } from "react-i18next"
import { useGetPortfolioDataQuery } from "./api/investment-portfolio-api"
import { useNavigate } from "react-router"

export const PortfolioPage = () => {
    const { data: portfolio } = useGetPortfolioDataQuery()
    const { t } = useTranslation("MainPage")
    const navigate = useNavigate()

    const moveToAccountCreation = () => {
        navigate("/accountCreate")
    }


    if (!portfolio) {
        return
    }

    console.log(portfolio)

    const accounts = portfolio.accounts

    return (
        <div>
            {accounts.map(
                account =>
                    <div key={`account-${account.name}`}>{account.name}</div>
            )}
            <button onClick={moveToAccountCreation}>{t("addAccountButton")}</button>
        </div>
    )
}
