import { Account } from "./account"
import { GroupInPortfolio } from "./group"

export interface Portfolio {
  accounts: Account[]
  groups: GroupInPortfolio[]
}
