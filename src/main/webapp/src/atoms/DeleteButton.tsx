interface DeleteButtonProps {
  deleteHandler: () => void
  label: string
}

export const DeleteButton = (props: DeleteButtonProps) => {
  const clickHandler = () => {
    props.deleteHandler()
  }

  return (
    <button onClick={clickHandler}>{props.label}</button>
  )
}
