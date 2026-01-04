import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react"
import { Portfolio } from "../model/portfolio"
import { User, UserLoginData } from "../model/user"
import { ElementType, PaymentTreeData } from "../components/fields/PaymentTreeField/PaymentTreeField"
import { PaymentCalculationData } from "../forms/PaymentCalculationPage"

export interface PaymentDistribution {
  type: ElementType,
  name: string
  items: PaymentDistribution[]
  value: number
}

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
    getDataToComputeDistribution: build.query<PaymentTreeData, void>({
      query: () => ({
        url: `api/portfolio/dataToComputeDistribution`,
        method: "GET",
      }),
      providesTags: ["portfolio"],
    }),
    computePaymentDistribution: build.query<PaymentDistribution, PaymentCalculationData>({
      query: data => ({
        url: `api/portfolio/computePaymentDistribution`,
        method: "POST",
        body: data
      }),
    }),
  }),
})

export const {
  useLoginMutation,
  useRegisterMutation,
  useGetPortfolioDataQuery,
  useGetDataToComputeDistributionQuery,
  useLazyComputePaymentDistributionQuery
} = investmentPortfolioApi
