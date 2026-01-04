import { useEffect, useState } from "react"
import { useGetDataToComputeDistributionQuery, useLazyComputePaymentDistributionQuery } from "../api/investment-portfolio-api"
import { PaymentTreeData, PaymentTreeField } from "../components/fields/PaymentTreeField/PaymentTreeField"
import { useTranslation } from "react-i18next"
import { PaymentElement } from "../molecules/PaymentElement/PaymentElement"

export interface PaymentCalculationData {
    data: PaymentTreeData,
    paymentValue: string
}

export const PaymentCalculationPage = () => {
    const { data } = useGetDataToComputeDistributionQuery()
    const { t } = useTranslation("PaymentTreeField")
    const [value, setValue] = useState<PaymentCalculationData>()
    const [triggerResult, result] = useLazyComputePaymentDistributionQuery()

    useEffect(() => {
        if (data) {
            setValue({ data: data, paymentValue: value?.paymentValue || "" })
        }
    }, [data])

    if (!value) {
        return
    }

    if (result.data) {
        return <PaymentElement item={result.data} />
    }

    return (
        <div>
            <PaymentTreeField value={value} setValue={setValue} />
            <button onClick={() => value && triggerResult(value)}>{t("confirm")}</button>
        </div>
    )
}
