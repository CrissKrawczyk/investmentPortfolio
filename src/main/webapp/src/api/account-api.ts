import { Account, AddAccountData } from "../model/account"
import { AddPositionRequest } from "../model/position"
import { investmentPortfolioApi, ValidatedObject } from "./investment-portfolio-api"

export const {
  useCreateAccountMutation,
  useGetAccountQuery,
  useAddPositionMutation,
  useDeleteAccountMutation
} = investmentPortfolioApi.injectEndpoints({
  endpoints: build => ({
    createAccount: build.mutation<ValidatedObject<Account>, AddAccountData>({
      query: request => ({
        url: `api/account`,
        method: "POST",
        body: request.account,
        params: {
          portfolioId: request.portfolioId
        }
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
    addPosition: build.mutation<ValidatedObject<void>, AddPositionRequest>({
      query: position => ({
        url: `api/position/${position.accountId}/addPosition`,
        method: "POST",
        body: position.position,
      }),
      invalidatesTags: (_r, _f, arg) => [{ type: "account", id: arg.accountId }],
    }),
    deleteAccount: build.mutation<void, number>({
      query: accountId => ({
        url: `api/account/${accountId}`,
        method: "DELETE",
      }),
      invalidatesTags: ["portfolio", "group"],
    }),
  }),
})
