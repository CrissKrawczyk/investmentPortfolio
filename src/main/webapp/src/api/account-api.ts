import { Account, AccountFormData } from "../model/account"
import { AddPositionRequest } from "../model/position"
import { investmentPortfolioApi } from "./investment-portfolio-api"

export const {
  useCreateAccountMutation,
  useGetAccountQuery,
  useAddPositionMutation
} = investmentPortfolioApi.injectEndpoints({
  endpoints: build => ({
    createAccount: build.mutation<AccountFormData, AccountFormData>({
      query: account => ({
        url: `api/account`,
        method: "POST",
        body: account
      }),
      invalidatesTags: ["portfolio"],
    }),
    getAccount: build.query<Account, number | string>({
      query: id => ({
        url: `api/account/${id}`,
        method: "GET",
      }),
      providesTags: (_r, _f, arg) => [{ type: "account", id: arg }],
    }),
    addPosition: build.mutation<void, AddPositionRequest>({
      query: position => ({
        url: `api/position/${position.accountId}/addPosition`,
        method: "POST",
        body: position.position,
      }),
      invalidatesTags: (_r, _f, arg) => [{ type: "account", id: arg.accountId }],
    }),
  }),
})
