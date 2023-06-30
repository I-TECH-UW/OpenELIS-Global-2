import React, {Component, Fragment} from "react";
import '../admin/reflexTests/ReflexStyles.css';
import {TextInput} from '@carbon/react';

class AutoComplete extends Component {
    constructor(props) {
        super(props);
        this.state = {
            textValue: "",
            activeSuggestion: 0,
            filteredSuggestions: [],
            showSuggestions: false,
            userInput: "",
            invalid: false
        };
    }

    onChange = (e) => {
        const {suggestions} = this.props;
        const userInput = e.currentTarget.value;
        this.setState({textValue: userInput});
        const filteredSuggestions = suggestions.filter(
            suggestion =>
                suggestion.value.toLowerCase().indexOf(userInput.toLowerCase()) > -1
        );

        this.setState({
            activeSuggestion: 0,
            filteredSuggestions,
            showSuggestions: true,
            userInput: e.currentTarget.value,
        });


        if (filteredSuggestions.length) {
            this.setState({invalid: true})
        }
    };

    onClick = (e, id, suggestion) => {
        const { onSelect} = this.props;
        this.setState({
            textValue: suggestion.value,
            activeSuggestion: 0,
            filteredSuggestions: [],
            showSuggestions: false,
            userInput: e.currentTarget.innerText,
            invalid: false
        });

        if (typeof onSelect === "function") {
            onSelect(id);
        }
    };

    onKeyDown = e => {
        const {activeSuggestion, filteredSuggestions} = this.state;
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
            this.setState({activeSuggestion: activeSuggestion - 1});
        } else if (e.keyCode === 40) {
            if (activeSuggestion - 1 === filteredSuggestions.length) {
                return;
            }
            this.setState({activeSuggestion: activeSuggestion + 1});
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
                                <li className={className} key={index}
                                    onClick={(e) => onClick(e, suggestion.id, suggestion)}>
                                    {suggestion.value}
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
                    id={this.props.idField}
                    name={this.props.name}
                    labelText={this.props.label ? this.props.label : ""}
                    className={this.props.class}
                    onChange={onChange}
                    onKeyDown={onKeyDown}
                    value={this.state.textValue}
                    invalid={this.state.invalid}
                    required={this.props.required ? this.props.required : false}
                    invalidText={this.props.invalidText}
                />
                {suggestionsListComponent}
            </Fragment>
        );
    }
}

export default AutoComplete;
