import { useGetDataToComputeDistributionQuery } from "../api/investment-portfolio-api"
import { PaymentTreeField } from "../components/fields/PaymentTreeField/PaymentTreeField"

export const PaymentCalculationPage = () => {
    const { data } = useGetDataToComputeDistributionQuery()

    if (!data) {
        return
    }

    return (
        <div>
            <PaymentTreeField data={data} />
        </div>
    )
}
