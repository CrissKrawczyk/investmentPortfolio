import { useNavigate } from "react-router"
import { useCreateGroupMutation } from "../api/group-api"
import { GroupFormData } from "../model/group"
import { FromContextProvider } from "../store/form-context"

export const CreateGroupPage = () => {
  const [createGroup] = useCreateGroupMutation()
  const navigate = useNavigate()

  const createGroupHandler = async (data: GroupFormData) => {
    const result = await createGroup(data)
    if (result.data) {
      navigate("/")
      return true
    }
    return false
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
            type: "text",
            params: undefined,
          },
        ]}
        confirmHandler={createGroupHandler}
      ></FromContextProvider>
    </div>
  )
}
