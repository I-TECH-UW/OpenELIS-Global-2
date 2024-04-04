import  { useEffect, useState } from "react";
import { DatePicker, DatePickerInput } from "@carbon/react";
import { format } from "date-fns";

const CustomDatePicker = (props) => {
  const [currentDate, setCurrentDate] = useState(
    props.value ? props.value : "",
  );

  function handleDatePickerChange(e) {
    let date = new Date(e[0]);
    const formatDate = format(new Date(date), "dd/MM/yyyy");
    setCurrentDate(formatDate);
    props.onChange(currentDate);
  }

  useEffect(() => {
    props.onChange(currentDate);
  }, [currentDate]);

  return (
    <>
      <DatePicker
        id={props.id}
        dateFormat="d/m/Y"
        className={props.className}
        datePickerType="single"
        value={currentDate}
        onChange={(e) => handleDatePickerChange(e)}
        maxDate={props.disallowFutureDate ? new Date() : null}
        minDate={props.disallowPastDate ? new Date() : null}
      >
        <DatePickerInput
          id={props.id}
          placeholder="dd/mm/yyyy"
          type="text"
          labelText={props.labelText}
        />
      </DatePicker>
    </>
  );
};

export default CustomDatePicker;
