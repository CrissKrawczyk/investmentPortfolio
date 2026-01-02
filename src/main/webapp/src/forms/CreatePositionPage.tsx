import { useNavigate, useParams } from "react-router"
import { FromContextProvider } from "../store/form-context"
import { useAddPositionMutation } from "../api/account-api"
import { PositionFormData } from "../model/position"

export const CreatePositionPage = () => {
  const { groupId, accountId } = useParams()
  const [createPosition] = useAddPositionMutation()
  const navigate = useNavigate()

  const createPositionHandler = async (data: PositionFormData) => {
    let result = await createPosition({ position: data, accountId: accountId ?? "0" })
    if (result.data) {
      navigate(groupId ? `/group/${groupId}/account/${accountId}` : `/account/${accountId}`)
      return true
    }
    return false
  }

  return (
    <div>
      <FromContextProvider<PositionFormData>
        formIdn="CreatePositionForm"
        fields={[
          {
            idn: "name",
            type: "text",
            params: undefined,
          },
        ]}
        confirmHandler={createPositionHandler}
      ></FromContextProvider>
    </div>
  )
}
