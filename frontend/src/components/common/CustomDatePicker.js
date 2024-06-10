import React, { useEffect, useState, useContext } from "react";
import { DatePicker, DatePickerInput } from "@carbon/react";
import { format } from "date-fns";
import { ConfigurationContext } from "../layout/Layout";

const CustomDatePicker = (props) => {
  const [currentDate, setCurrentDate] = useState(
    props.value ? props.value : "",
  );
  const { configurationProperties } = useContext(ConfigurationContext);
  function handleDatePickerChange(e) {
    let date = new Date(e[0]);
    const formatDate = format(
      new Date(date),
      configurationProperties.DEFAULT_DATE_LOCALE == "fr-FR"
        ? "dd/MM/yyyy"
        : "MM/dd/yyyy",
    );
    setCurrentDate(formatDate);
    props.onChange(currentDate);
  }

  useEffect(() => {
    props.onChange(currentDate);
  }, [currentDate]);

  useEffect(() => {
    if (props.updateStateValue) {
      setCurrentDate(props.value);
    }
  }, [props.value]);

  return (
    <>
      <DatePicker
        id={props.id}
        dateFormat={
          configurationProperties.DEFAULT_DATE_LOCALE == "fr-FR"
            ? "d/m/Y"
            : "m/d/Y"
        }
        className={props.className}
        datePickerType="single"
        value={currentDate}
        onChange={(e) => handleDatePickerChange(e)}
        maxDate={
          props.disallowFutureDate
            ? format(
                new Date(),
                configurationProperties.DEFAULT_DATE_LOCALE == "fr-FR"
                  ? "dd/MM/yyyy"
                  : "MM/dd/yyyy",
              )
            : ""
        }
        minDate={
          props.disallowPastDate
            ? format(
                new Date(),
                configurationProperties.DEFAULT_DATE_LOCALE == "fr-FR"
                  ? "dd/MM/yyyy"
                  : "MM/dd/yyyy",
              )
            : ""
        }
      >
        <DatePickerInput
          id={props.id}
          placeholder={
            configurationProperties.DEFAULT_DATE_LOCALE == "fr-FR"
              ? "dd/mm/yyyy"
              : "mm/dd/yyyy"
          }
          type="text"
          labelText={props.labelText}
          invalid={props.invalid}
          invalidText={props.invalidText}
        />
      </DatePicker>
    </>
  );
};

export default CustomDatePicker;
