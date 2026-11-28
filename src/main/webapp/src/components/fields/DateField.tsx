import { DateField as FieldBase } from '@mui/x-date-pickers/DateField';
import { FieldProps } from "../../store/form-context";
import dayjs from 'dayjs';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';

export interface DateFieldProps extends FieldProps<undefined> { }

export const DateField = (props: DateFieldProps) => {
  const { setValue, value } = props

  return (

    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <FieldBase
        value={dayjs(value)}
        onChange={v => setValue(v?.toString() ?? "")}
        format="DD-MM-YYYY"
        error={false}
      />
    </LocalizationProvider>
  )
}
