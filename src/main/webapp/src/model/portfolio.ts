import { DataType } from "../store/form-context";
import { Account } from "./account"
import { GroupInPortfolio } from "./group"

export interface Portfolio {
  id: number
  name: string
  testPortfolio: boolean
  accounts: Account[]
  groups: GroupInPortfolio[]
}

export interface OtherPortfolio {
  id: number
  name: string
  testPortfolio: boolean
}


export interface PortfolioFormData extends DataType {
  name: string
}