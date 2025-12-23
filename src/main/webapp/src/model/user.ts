import { DataType } from "../store/form-context"

export interface UserLoginData extends DataType {
  name: string
  password: string
}

export interface User extends DataType {
  login: string
  forename: string
  surname: string
}
