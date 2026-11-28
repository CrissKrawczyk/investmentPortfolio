import { Account } from "../model/account"
import { AddAccountRequest, AddGroupRequest, Group } from "../model/group"
import { investmentPortfolioApi, ValidatedObject } from "./investment-portfolio-api"

export const {
  useCreateGroupMutation,
  useGetGroupQuery,
  useAddAccountMutation,
  useDeleteGroupMutation,
} = investmentPortfolioApi.injectEndpoints({
  endpoints: build => ({
    createGroup: build.mutation<ValidatedObject<Group>, AddGroupRequest>({
      query: request => ({
        url: `api/accountGroup`,
        method: "POST",
        body: request.group,
        params: {
          groupId: request.groupId,
          portfolioId: request.portfolioId
        }
      }),
      invalidatesTags: ["portfolio", "group"],
    }),
    getGroup: build.query<Group, number | string>({
      query: id => ({
        url: `api/accountGroup/${id}`,
        method: "GET",
      }),
      providesTags: (_r, _f, arg) => [{ type: "group", id: arg }],
    }),
    addAccount: build.mutation<ValidatedObject<Account>, AddAccountRequest>({
      query: account => ({
        url: `api/accountGroup/${account.groupId}/addAccount`,
        method: "POST",
        body: account.account,
      }),
      invalidatesTags: (_r, _f, arg) => [{ type: "group", id: arg.groupId }],
    }),
    deleteGroup: build.mutation<void, number>({
      query: groupId => ({
        url: `api/accountGroup/${groupId}`,
        method: "DELETE",
      }),
      invalidatesTags: ["portfolio", "group"],
    }),
  }),
})
