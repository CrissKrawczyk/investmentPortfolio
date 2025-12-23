import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react"
import { User, UserLoginData } from "../model/user"

export const investmentPortfolioApi = createApi({
  reducerPath: "pokemonApi",
  baseQuery: fetchBaseQuery({ baseUrl: "http://localhost:8080/" }),
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
  }),
})

export const { useLoginMutation, useRegisterMutation } = investmentPortfolioApi
