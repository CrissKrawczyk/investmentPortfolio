import { DataType } from "../store/form-context"
import { Position } from "./position"

export interface AccountFormData extends DataType {
  name: string
  valuePercentage: string
}

export interface AddAccountData {
  account: AccountFormData
  portfolioId?: string
}

export interface Account {
  id: number
  valuePercentage: string
  name: string
  groupId?: string
  positions: Position[]
}
