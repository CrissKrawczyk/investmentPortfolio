import { DataType } from "../store/form-context"
import { Account } from "./account"

export interface GroupFormData extends DataType {
  name: string
  valuePercentage: string
}

export interface GroupInPortfolio {
  name: string
  valuePercentage: string
  id: number
}

export interface Group {
  name: string
  valuePercentage: string
  accounts: Account[]
  id: number
}

export interface AddAccountRequest {
  account: Account
  groupId: string
}
