import { AddAccountRequest, Group, GroupFormData } from "../model/group"
import { investmentPortfolioApi } from "./investment-portfolio-api"

export const {
  useCreateGroupMutation,
  useGetGroupQuery,
  useAddAccountMutation,
} = investmentPortfolioApi.injectEndpoints({
  endpoints: build => ({
    createGroup: build.mutation<void, GroupFormData>({
      query: group => ({
        url: `api/accountGroup`,
        method: "POST",
        body: group,
      }),
      invalidatesTags: ["portfolio"],
    }),
    getGroup: build.query<Group, number | string>({
      query: id => ({
        url: `api/accountGroup/${id}`,
        method: "GET",
      }),
      providesTags: (_r, _f, arg) => [{ type: "group", id: arg }],
    }),
    addAccount: build.mutation<void, AddAccountRequest>({
      query: account => ({
        url: `api/accountGroup/${account.groupId}/addAccount`,
        method: "POST",
        body: account.account,
      }),
      invalidatesTags: (_r, _f, arg) => [{ type: "group", id: arg.groupId }],
    }),
  }),
})
