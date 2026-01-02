import { DataType } from "../store/form-context"
import { TransactionFormData } from "./transaction"

export interface PositionFormData extends DataType {
  name: string
}

export interface AddPositionRequest {
  position: PositionFormData
  accountId: string
}

export interface Position {
  name: string
  id: number;
}

export interface AddTransactionRequest {
  transaction: TransactionFormData
  positionId: number | string
}