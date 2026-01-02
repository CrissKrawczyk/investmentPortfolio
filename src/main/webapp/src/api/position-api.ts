import { AddTransactionRequest } from "../model/position"
import { Transaction } from "../model/transaction"
import { investmentPortfolioApi } from "./investment-portfolio-api"

export const {
    useBuyMutation,
    useSellMutation
} = investmentPortfolioApi.injectEndpoints({
    endpoints: build => ({
        buy: build.mutation<Transaction, AddTransactionRequest>({
            query: transaction => ({
                url: `api/position/${transaction.positionId}/buy`,
                method: "POST",
                body: transaction.transaction
            }),
        }),
        sell: build.mutation<Transaction, AddTransactionRequest>({
            query: transaction => ({
                url: `api/position/${transaction.positionId}/sell`,
                method: "POST",
                body: transaction.transaction,
            }),
        }),
    }),
})
