import { AddTransactionRequest } from "../model/position"
import { investmentPortfolioApi, ValidatedObject } from "./investment-portfolio-api"

export const {
    useBuyMutation,
    useSellMutation
} = investmentPortfolioApi.injectEndpoints({
    endpoints: build => ({
        buy: build.mutation<ValidatedObject<void>, AddTransactionRequest>({
            query: transaction => ({
                url: `api/position/${transaction.positionId}/buy`,
                method: "POST",
                body: transaction.transaction
            }),
        }),
        sell: build.mutation<ValidatedObject<void>, AddTransactionRequest>({
            query: transaction => ({
                url: `api/position/${transaction.positionId}/sell`,
                method: "POST",
                body: transaction.transaction,
            }),
        }),
    }),
})
