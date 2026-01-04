import { useTranslation } from "react-i18next";
import styles from './PaymentElement.module.css'
import { PaymentDistribution } from "../../api/investment-portfolio-api";

interface PaymentElementProps {
    item: PaymentDistribution;
}

export const PaymentElement = (props: PaymentElementProps) => {
    const { t } = useTranslation("PaymentTreeField")
    const { type, name, items, value } = props.item


    return <div className={styles.item}>
        <div>{t(type)} {name}({value})</div>
        {items.map(item => <PaymentElement item={item} />)}
    </div>
}