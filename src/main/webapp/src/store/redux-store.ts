import { configureStore } from "@reduxjs/toolkit"
import { setupListeners } from "@reduxjs/toolkit/query"
import { investmentPortfolioApi } from "../api/investment-portfolio-api"

export const store = configureStore({
  reducer: {
    [investmentPortfolioApi.reducerPath]: investmentPortfolioApi.reducer,
  },
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware().concat(investmentPortfolioApi.middleware),
})

setupListeners(store.dispatch)
