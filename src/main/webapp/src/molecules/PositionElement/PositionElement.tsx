import { useTranslation } from "react-i18next";
import { Position } from "../../model/position"
import { AddTransactionForm } from "../../forms/AddTransactionForm";
import { useState } from "react";
import { TransactionType } from "../../model/transaction";

interface PositionElementProps {
    position: Position;
}

export const PositionElement = (props: PositionElementProps) => {
    const { id, name } = props.position
    const { t } = useTranslation("AccountPage")
    const [formState, setFormState] = useState<TransactionType>()


    return <div>
        {t("position")}
        {name}
        <button disabled={formState !== undefined} onClick={() => setFormState('Purchase')}>{t('buy')}</button>
        <button disabled={formState !== undefined} onClick={() => setFormState('Sale')}>{t('sell')}</button>
        {formState && <AddTransactionForm positionId={id} onClose={() => setFormState(undefined)} type={formState} />}
    </div>
}