import { TextField } from "../TextField";
import { PaymentTreeData } from "./PaymentTreeField";

interface LastTreeNodeProps {
    item: PaymentTreeData;
    setValue: (value: PaymentTreeData) => void
}

export const LastTreeNode = (props: LastTreeNodeProps) => {
    const { type, id, value } = props.item
    const setValue = props.setValue

    return <div>
        <TextField idn={`${type}-${id}`} value={value.toString()} type="text" params={undefined}
            setValue={(v) => setValue({ ...props.item, value: Number.parseInt(v) })} />
    </div>
}