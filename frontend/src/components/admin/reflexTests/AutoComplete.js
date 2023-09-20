import React, { useState } from "react";

import { FormattedMessage } from "react-intl";
import "./ReflexStyles.css";
import { TextInput } from "@carbon/react";

function Autocomplete(props) {
  const [activeSuggestion, setActiveSuggestion] = useState(0);
  const [filteredSuggestions, setFilteredSuggestions] = useState([]);
  const [showSuggestions, setShowSuggestions] = useState(false);
  const [userInput, setUserInput] = useState("");
  const [invalid, setInvalid] = useState(false);

  const onChange = (e) => {
    const {
      suggestions,
      index,
      item_index,
      handleChange,
      field,
      name,
      addError,
    } = props;
    const userInput = e.currentTarget.value;

    const filteredSuggestions = suggestions.filter(
      (suggestion) =>
        suggestion.label.toLowerCase().indexOf(userInput.toLowerCase()) > -1,
    );

    setActiveSuggestion(0);
    setFilteredSuggestions(filteredSuggestions);
    setShowSuggestions(true);
    setUserInput(e.currentTarget.value);

    addError({
      name: name + "-" + index + "-" + item_index,
      error: "Invaid Test",
    });
    const nameValue = {
      target: { name: name, value: e.currentTarget.value },
    };

    handleChange(nameValue, index, item_index, field);
    if (filteredSuggestions.length) {
      setInvalid(true);
    }
  };

  const onClick = (e, id, testDetails) => {
    const {
      index,
      item_index,
      handleChange,
      field,
      name,
      idField,
      onSelect,
      clearError,
    } = props;
    setActiveSuggestion(0);
    setShowSuggestions(false);
    setFilteredSuggestions([]);
    setUserInput(e.currentTarget.innerText);
    setInvalid(false);

    clearError(name + "-" + index + "-" + item_index);
    const nameValue = {
      target: { name: name, value: e.currentTarget.innerText },
    };

    const nameId = {
      target: { name: idField, value: id },
    };

    handleChange(nameValue, index, item_index, field);
    handleChange(nameId, index, item_index, field);
    if (typeof onSelect === "function") {
      onSelect(index, item_index, testDetails);
    }
  };

  const onKeyDown = (e) => {
    if (e.keyCode === 13) {
      setActiveSuggestion(0);
      setShowSuggestions(false);
      setUserInput(filteredSuggestions[activeSuggestion]);
    } else if (e.keyCode === 38) {
      if (activeSuggestion === 0) {
        return;
      }
      setActiveSuggestion(activeSuggestion - 1);
    }
    // User pressed the down arrow, increment the index
    else if (e.keyCode === 40) {
      if (activeSuggestion - 1 === filteredSuggestions.length) {
        return;
      }
      setActiveSuggestion(activeSuggestion + 1);
    }
  };

  let suggestionsListComponent;

  if (showSuggestions && userInput) {
    if (filteredSuggestions.length) {
      suggestionsListComponent = (
        <ul className="suggestions">
          {filteredSuggestions.map((suggestion, index) => {
            let className;

            // Flag the active suggestion with a class
            if (index === activeSuggestion) {
              className = "suggestion-active";
            }
            return (
              <li
                className={className}
                key={index}
                onClick={(e) => onClick(e, suggestion.value, suggestion)}
              >
                {suggestion.label}
              </li>
            );
          })}
        </ul>
      );
    } else {
      suggestionsListComponent = (
        <div className="no-suggestions">
          <em>
            <FormattedMessage id="rulebuilder.label.noSuggestions" />
          </em>
        </div>
      );
    }
  }

  return (
    <>
      <TextInput
        type="text"
        id={props.index + "_" + props.item_index + "_test" + props.field}
        name={props.name}
        labelText={props.label ? props.label : ""}
        className={props.class}
        onChange={onChange}
        onKeyDown={onKeyDown}
        value={props.stateValue}
        invalid={invalid}
        required
        invalidText={<FormattedMessage id="rulebuilder.error.invalidTest" />}
      />
      {suggestionsListComponent}
    </>
  );
}

export default Autocomplete;
