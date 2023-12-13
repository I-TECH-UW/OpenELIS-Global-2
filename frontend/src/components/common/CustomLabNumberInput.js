import React, { useState, useContext, useRef, useEffect } from "react";
import { TextInput } from "@carbon/react";
import { convertAlphaNumLabNumForDisplay } from "../utils/Utils";
import { ConfigurationContext } from "../layout/Layout";

const CustomLabNumberInput = (props) => {
  const componentMounted = useRef(true);

  const { configurationProperties } = useContext(ConfigurationContext);

  const [rawInput, setRawInput] = useState([]);
  const [formattedInput, setFormattedInput] = useState("");

  useEffect(() => {
    setRawInput(props.value);
    if (
      configurationProperties.AccessionFormat === "ALPHANUM" &&
      props?.value?.length < 13
    ) {
      const formatted = convertAlphaNumLabNumForDisplay(props.value); // use your own format function here
      setFormattedInput(formatted);
    } else {
      setFormattedInput(props.value);
    }
  }, [props.value]);

  if (configurationProperties.AccessionFormat !== "ALPHANUM") {
    return (
      <>
        <TextInput {...props} />
      </>
    );
  } else {
    return (
      <>
        <input type="hidden" value={rawInput} name={props.name} id={props.id} />
        <TextInput
          {...props}
          onChange={(e) => {
            const val = e.target.value.replace(/-/g, "");
            setRawInput(val);
            if (typeof props.onChange === "function") {
              props.onChange(e, val);
            }
          }}
          labelText={props.labelText}
          id={"display_" + props.id}
          value={formattedInput}
          name={"display_" + props.name}
        />
      </>
    );
  }
};
export default CustomLabNumberInput;
