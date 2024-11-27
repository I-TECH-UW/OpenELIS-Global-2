import React, { useContext, useEffect, useState } from "react";
import { ConfigurationContext } from "../layout/Layout";
import { TimePicker } from "@carbon/react";

const CustomTimePicker = (props) => {
  const { configurationProperties } = useContext(ConfigurationContext);
  const [currentTime, setCurrentTime] = useState(
    props.value ? props.value : "",
  );

  function handleTimePicker(e) {
    let time = e.target.value;
    setCurrentTime(time);
    props.onChange(time);
  }
  useEffect(() => {
    if (props.autofillTime) {
      setCurrentTime(configurationProperties.currentTimeAsText);
    }
  }, [configurationProperties]);

  useEffect(() => {
    props.onChange(currentTime);
  }, [currentTime]);

  return (
    <>
      <TimePicker
        id={props.id}
        value={currentTime == null ? "" : currentTime}
        onChange={(e) => handleTimePicker(e)}
        labelText={props.labelText == null ? "" : props.labelText}
      />
    </>
  );
};

export default CustomTimePicker;
