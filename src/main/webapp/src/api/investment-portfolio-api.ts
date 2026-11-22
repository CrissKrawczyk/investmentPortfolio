import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react"
import { OtherPortfolio, Portfolio, PortfolioFormData } from "../model/portfolio"
import { User, UserLoginData, UserLoginResponse } from "../model/user"
import { ElementType, PaymentTreeData } from "../components/fields/PaymentTreeField/PaymentTreeField"
import { PaymentCalculationData } from "../forms/PaymentCalculationPage"

export interface PaymentDistribution {
  type: ElementType,
  name: string
  items: PaymentDistribution[]
  value: number
}

interface Errors {
  [field: string]: string
}

export interface ValidatedObject<T> {
  object: T
  valid: boolean
  errors: Errors
}

interface GetPortfolioResponse {
  portfolio: Portfolio
  otherPortfolios: OtherPortfolio[]
}

export const investmentPortfolioApi = createApi({
  reducerPath: "pokemonApi",
  baseQuery: fetchBaseQuery({ baseUrl: "http://localhost:8080/" }),
  tagTypes: ["portfolio", "group", "account"],
  endpoints: build => ({
    login: build.mutation<UserLoginResponse, UserLoginData>({
      query: user => ({
        url: `login?username=${user.name}&password=${user.password}`,
        method: "POST",
      }),
    }),
    register: build.mutation<ValidatedObject<User>, User>({
      query: user => ({
        url: `api/user`,
        method: "POST",
        body: user,
      }),
    }),
    createTestPortfolio: build.mutation<ValidatedObject<Portfolio>, PortfolioFormData>({
      query: user => ({
        url: `api/portfolio`,
        method: "POST",
        body: user,
      }),
      invalidatesTags: ["portfolio"],
    }),
    deleteTestPortfolio: build.mutation<void, number>({
      query: portfolioId => ({
        url: `api/portfolio/${portfolioId}`,
        method: "DELETE",
      }),
      invalidatesTags: ["portfolio"],
    }),
    getPortfolioData: build.query<GetPortfolioResponse, string | undefined>({
      query: (portfolioId) => ({
        url: `api/portfolio`,
        method: "GET",
        params: {
          "portfolioId": portfolioId
        }
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
  useCreateTestPortfolioMutation,
  useDeleteTestPortfolioMutation,
  useGetPortfolioDataQuery,
  useGetDataToComputeDistributionQuery,
  useLazyComputePaymentDistributionQuery
} = investmentPortfolioApi
