import { DataType } from "../store/form-context";

export interface TransactionFormData extends DataType {
    amount: string
    price: string
    type: string
    date: string
}

export interface Transaction {
    amount: string
    price: string
    type: string
    date: string
}

export type TransactionType = 'Purchase' | 'Sale'