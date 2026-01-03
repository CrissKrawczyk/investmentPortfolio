import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import { Provider } from "react-redux"
import { BrowserRouter, Route, Routes } from "react-router"
import { App } from "./App"
import { CreateAccountPage } from "./forms/CreateAccountPage"
import { CreateGroupPage } from "./forms/CreateGroupPage"
import { GroupPage } from "./pages/GroupPage"
import { LoginPage } from "./LoginPage"
import { RegisterPage } from "./forms/RegisterPage"
import "./i18n/i18n"
import { store } from "./store/redux-store"
import { UserContextProvider } from "./store/user-context"
import { AccountPage } from "./pages/AccountPage"
import { CreatePositionPage } from "./forms/CreatePositionPage"
import { PaymentCalculationPage } from "./forms/PaymentCalculationPage"

const container = document.getElementById("root")

if (container) {
  const root = createRoot(container)

  root.render(
    <Provider store={store}>
      <StrictMode>
        <BrowserRouter>
          <UserContextProvider>
            <Routes>
              <Route path="/" element={<App />} />
              <Route path="/login" element={<LoginPage />} />
              <Route path="/register" element={<RegisterPage />} />
              <Route path="/accountCreate">
                <Route path="" element={<CreateAccountPage />} />
                <Route path=":groupId" element={<CreateAccountPage />} />
              </Route>
              <Route path="/groupCreate" element={<CreateGroupPage />} />
              <Route path="/group/:groupId" element={<GroupPage />} />
              <Route path="/account/:accountId" element={<AccountPage />} />
              <Route path="/group/:groupId/account/:accountId" element={<AccountPage />} />
              <Route path="/account/:accountId/positionCreate" element={<CreatePositionPage />} />
              <Route path="/group/:groupId/account/:accountId/positionCreate" element={<CreatePositionPage />} />
              <Route path="/paymentCalculation" element={<PaymentCalculationPage />} />
            </Routes>
          </UserContextProvider>
        </BrowserRouter>
      </StrictMode>
    </Provider>,
  )
} else {
  throw new Error(
    "Root element with ID 'root' was not found in the document. Ensure there is a corresponding HTML element with the ID 'root' in your HTML file.",
  )
}
