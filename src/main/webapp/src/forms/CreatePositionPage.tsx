import { useNavigate, useParams } from "react-router"
import { FromContextProvider } from "../store/form-context"
import { useAddPositionMutation } from "../api/account-api"
import { PositionFormData } from "../model/position"

export const CreatePositionPage = () => {
  const { accountId } = useParams()
  const [createPosition] = useAddPositionMutation()
  const navigate = useNavigate()

  const createPositionHandler = async (data: PositionFormData) => {
    let result = await createPosition({ position: data, accountId: accountId ?? "0" })
    if (result.data && result.data.valid) {
      navigate('../')
    } else if (result.data?.errors) {
      return result.data.errors
    }
  }

  return (
    <div>
      <FromContextProvider<PositionFormData>
        formIdn="CreatePositionForm"
        fields={[
          {
            idn: "name",
            type: "text",
            required: true,
            params: undefined,
          },
          {
            idn: "kind",
            type: "singleSelect",
            required: true,
            params: {
              dictIdn: "positionKind"
            },
          },
          {
            idn: "currency",
            type: "singleSelect",
            required: true,
            params: {
              dictIdn: "currency"
            },
          },
          {
            idn: "valuePercentage",
            type: "numeric",
            params: undefined,
          },
        ]}
        confirmHandler={createPositionHandler}
      ></FromContextProvider>
    </div>
  )
}
