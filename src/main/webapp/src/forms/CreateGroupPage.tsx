import { useNavigate, useParams } from "react-router"
import { useCreateGroupMutation } from "../api/group-api"
import { GroupFormData } from "../model/group"
import { FromContextProvider } from "../store/form-context"

export const CreateGroupPage = () => {
  const { groupId, portfolioId } = useParams()
  const [createGroup] = useCreateGroupMutation()
  const navigate = useNavigate()

  const createGroupHandler = async (data: GroupFormData) => {
    const result = await createGroup({ group: data, groupId, portfolioId })
    if (result.data && result.data.valid) {
      navigate(`/portfolio/${portfolioId}/group/${result.data.object.id}`)
    } else if (result.data?.errors) {
      return result.data.errors
    }
  }

  return (
    <div>
      <FromContextProvider<GroupFormData>
        formIdn="CreateGroupForm"
        fields={[
          {
            idn: "name",
            type: "text",
            params: undefined,
          },
          {
            idn: "valuePercentage",
            type: "numeric",
            params: undefined,
          },
        ]}
        confirmHandler={createGroupHandler}
      ></FromContextProvider>
    </div>
  )
}
