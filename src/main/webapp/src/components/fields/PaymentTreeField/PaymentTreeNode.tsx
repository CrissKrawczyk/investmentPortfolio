import { useTranslation } from "react-i18next";
import { PaymentTreeData } from "./PaymentTreeField";
import styles from './PaymentTreeField.module.css'
import { LastTreeNode } from "./LastTreeNode";
import { ParentTreeNode } from "./ParentTreeNode";

interface PaymentTreeNodeProps {
    item: PaymentTreeData;
    setValue: (value: PaymentTreeData) => void
}

export const PaymentTreeNode = (props: PaymentTreeNodeProps) => {
    const { t } = useTranslation("PaymentTreeField")
    const { type, name, items, percentageValue } = props.item

    const lastNode = items.length == 0
    const NodeComponent = lastNode ? LastTreeNode : ParentTreeNode

    return <div className={styles.item}>
        <h3>{t(type)} {name}({percentageValue}%):</h3>
        <NodeComponent {...props} />
    </div>
}