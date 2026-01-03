import { PaymentTreeData } from "./PaymentTreeField";
import { useEffect, useState } from "react";
import { PaymentTreeNode } from "./PaymentTreeNode";

interface ParentTreeNodeProps {
    item: PaymentTreeData;
    setValue: (value: PaymentTreeData) => void
}

export const ParentTreeNode = (props: ParentTreeNodeProps) => {
    const { items } = props.item
    const [value, setValue] = useState<PaymentTreeData[]>(items)

    useEffect(() => {
        props.setValue({ ...props.item, items: value })
    }, [value])

    const setItemValue = (itemValue: PaymentTreeData) => {
        const itemIndex = value.findIndex(item => item.id === itemValue.id && item.type === itemValue.type)
        const newValue = [...value]
        newValue[itemIndex] = itemValue
        setValue(newValue)
    }

    return <div>
        {value.map(item => <PaymentTreeNode setValue={setItemValue} item={item} />)}
    </div>
}