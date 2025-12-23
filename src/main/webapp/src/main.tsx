import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import { Provider } from "react-redux"
import { BrowserRouter, Route, Routes } from "react-router"
import { App } from "./App"
import { LoginPage } from "./LoginPage"
import { RegisterPage } from "./RegisterPage"
import "./i18n/i18n"
import { store } from "./store/redux-store"
import { UserContextProvider } from "./store/user-context"

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
