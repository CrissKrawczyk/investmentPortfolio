import { DataType } from "../store/form-context"
import { Account, AccountFormData } from "./account"

export interface AddGroupRequest {
  group: GroupFormData
  portfolioId?: string
  groupId?: string
}

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
  groups: Group[]
  id: number
}

export interface AddAccountRequest {
  account: AccountFormData
  groupId: string
}
