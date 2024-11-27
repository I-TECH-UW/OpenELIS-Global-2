import React, { useContext, useEffect, useState } from "react";
import { DatePicker, DatePickerInput } from "@carbon/react";
import { ConfigurationContext } from "../layout/Layout";
import { format } from "date-fns";

const CustomDatePicker = (props) => {
  const { configurationProperties } = useContext(ConfigurationContext);
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
    if (props.autofillDate) {
      setCurrentDate(configurationProperties.currentDateAsText);
    }
  }, [configurationProperties]);

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
