import React, {useState} from 'react'
import {TextInput} from "@carbon/react";

const CustomTextInput = (props) => {
    const {id, defaultValue, onChange} = props;
    const [inputText, setInputText] = useState("");

    const handleInputChange = (e) => {
        if (onChange != null) {
            let value = e.target.value;
            setInputText(value);
            onChange(value);
        }
    }
    return (
        <>
            <TextInput id={id} onChange={handleInputChange} labelText=""
                       value={inputText === "" ? defaultValue : inputText}/>
        </>
    )
}
export default CustomTextInput;
