import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react"
import { Account } from "../model/account"
import { Portfolio } from "../model/portfolio"
import { User, UserLoginData } from "../model/user"

export const investmentPortfolioApi = createApi({
  reducerPath: "pokemonApi",
  baseQuery: fetchBaseQuery({ baseUrl: "http://localhost:8080/" }),
  tagTypes: ["portfolio", "group"],
  endpoints: build => ({
    login: build.mutation<User, UserLoginData>({
      query: user => ({
        url: `login?username=${user.name}&password=${user.password}`,
        method: "POST",
      }),
    }),
    register: build.mutation<User, User>({
      query: user => ({
        url: `api/user`,
        method: "POST",
        body: user,
      }),
    }),
    getPortfolioData: build.query<Portfolio, void>({
      query: () => ({
        url: `api/portfolio`,
        method: "GET",
      }),
      providesTags: ["portfolio"],
    }),
    createAccount: build.mutation<Account, Account>({
      query: account => ({
        url: `api/account`,
        method: "POST",
        body: {
          ...account,
          groupId: account.groupId ? Number(account.groupId) : undefined,
        },
      }),
      invalidatesTags: ["portfolio"],
    }),
  }),
})

export const {
  useLoginMutation,
  useRegisterMutation,
  useGetPortfolioDataQuery,
  useCreateAccountMutation,
} = investmentPortfolioApi
