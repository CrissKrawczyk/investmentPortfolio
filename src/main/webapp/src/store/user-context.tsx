import { PropsWithChildren, createContext, useContext, useState } from "react"
import { User } from "../model/user"

interface UserContextData {
  user?: User
  setUser: (user: User) => void
}

const UserContext = createContext<UserContextData>({ setUser: () => {} })

export const UserContextProvider = ({ children }: PropsWithChildren) => {
  const [user, setUser] = useState<User>()

  return (
    <UserContext.Provider value={{ user, setUser }}>
      {children}
    </UserContext.Provider>
  )
}
export const useUserContext = () => useContext(UserContext)
