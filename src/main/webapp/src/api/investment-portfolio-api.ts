import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react"
import { Portfolio } from "../model/portfolio"
import { User, UserLoginData } from "../model/user"

export const investmentPortfolioApi = createApi({
  reducerPath: "pokemonApi",
  baseQuery: fetchBaseQuery({ baseUrl: "http://localhost:8080/" }),
  tagTypes: ["portfolio", "group", "account"],
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
  }),
})

export const {
  useLoginMutation,
  useRegisterMutation,
  useGetPortfolioDataQuery,
} = investmentPortfolioApi
