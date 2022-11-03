import React, { Component, Fragment } from "react";
import './styles.css'
import { TextInput } from '@carbon/react';

class Autocomplete extends Component {
  constructor(props) {
    super(props);
    this.state = {
      activeSuggestion: 0,
      filteredSuggestions: [],
      showSuggestions: false,
      userInput: ""
    };
  }

  onChange = e => {
    const { suggestions, index, item_index, handleChange, field, name } = this.props;
    const userInput = e.currentTarget.value;

    const filteredSuggestions = suggestions.filter(
      suggestion =>
        suggestion.label.toLowerCase().indexOf(userInput.toLowerCase()) > -1
    );

    this.setState({
      activeSuggestion: 0,
      filteredSuggestions,
      showSuggestions: true,
      userInput: e.currentTarget.value
    });

    const nameValue = {
      target: { name: name, value: e.currentTarget.value }
    }

    handleChange(nameValue, index, item_index, field);
  };

  onClick = (e, id) => {
    const { index, item_index, handleChange, field, name, idField } = this.props;
    this.setState({
      activeSuggestion: 0,
      filteredSuggestions: [],
      showSuggestions: false,
      userInput: e.currentTarget.innerText
    });

    const nameValue = {
      target: { name: name, value: e.currentTarget.innerText }
    }

    const nameId = {
      target: { name: idField, value: id }
    }
    
    handleChange(nameValue, index, item_index, field);
    handleChange(nameId, index, item_index, field);
  };

  onKeyDown = e => {
    const { activeSuggestion, filteredSuggestions } = this.state;

    if (e.keyCode === 13) {
      this.setState({
        activeSuggestion: 0,
        showSuggestions: false,
        userInput: filteredSuggestions[activeSuggestion]
      });
    } else if (e.keyCode === 38) {
      if (activeSuggestion === 0) {
        return;
      }
      this.setState({ activeSuggestion: activeSuggestion - 1 });
    }
    // User pressed the down arrow, increment the index
    else if (e.keyCode === 40) {
      if (activeSuggestion - 1 === filteredSuggestions.length) {
        return;
      }
      this.setState({ activeSuggestion: activeSuggestion + 1 });
    }
  };

  render() {
    const {
      onChange,
      onClick,
      onKeyDown,
      state: {
        activeSuggestion,
        filteredSuggestions,
        showSuggestions,
        userInput
      }
    } = this;

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
                <li className={className} key={index} onClick={(e) => onClick(e, suggestion.value)}>
                  {suggestion.label}
                </li>
              );
            })}
          </ul>
        );
      } else {
        suggestionsListComponent = (
          <div className="no-suggestions">
            <em>No suggestions available.</em>
          </div>
        );
      }
    }

    return (
      <Fragment>
        <TextInput
          type="text"
          id={this.props.index + "_" + this.props.item_index + "_test" + this.props.field}
          name="test"
          labelText=""
          className={this.props.class}
          onChange={onChange}
          onKeyDown={onKeyDown}
          value={this.props.stateValue}
        />
        {suggestionsListComponent}
      </Fragment>
    );
  }
}

export default Autocomplete;