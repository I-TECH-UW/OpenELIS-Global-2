import React, { useEffect, useRef, useState } from "react";
import {
  FilterableMultiSelect,
  Select,
  SelectItem,
  TextInput,
  Stack,
  InlineLoading,
} from "@carbon/react";
import { FormattedMessage } from "react-intl";
import "../../index.css";
import "../../App.css";
import "../Style.css";
import { getFromOpenElisServer } from "../utils/Utils";

export const Questionnaire = ({
  questionnaire,
  onAnswerChange = () => {
    console.log("default onAnswerChange function does nothing");
  },
  setAnswer,
}) => {
  const getSelectOption = (answerOption, index) => {
    if ("valueString" in answerOption) {
      return (
        <SelectItem
          key={index}
          value={answerOption.valueString}
          text={answerOption.valueString}
        />
      );
    } else if ("valueCoding" in answerOption) {
      return (
        <SelectItem
          key={index}
          value={answerOption.valueCoding.code}
          text={answerOption.valueCoding.display}
        />
      );
    } else {
      return <></>;
    }
  };

  const renderQuestion = (item) => {
    var options = [];
    if (
      item.type == "choice" &&
      item.repeats === true &&
      "answerOption" in item
    ) {
      item.answerOption.map((answerOption) => {
        if ("valueString" in answerOption) {
          options.push({
            value: answerOption.valueString,
            text: answerOption.valueString,
          });
        }
        if ("valueCoding" in answerOption) {
          options.push({
            value: answerOption.valueCoding.code,
            text: answerOption.valueCoding.display,
          });
        }
      });
    }

    return (
      <>
        <div className="inputText">
          {item.type == "boolean" && (
            <Select
              id={item.linkId}
              className="inputText"
              labelText={item.text}
              onChange={onAnswerChange}
              value={setAnswer(item.linkId)}
            >
              <FormattedMessage id="select.default.option.label">
                {(msg) => <SelectItem disabled value="" text={msg} />}
              </FormattedMessage>
              <SelectItem value="" text="" />
              <FormattedMessage id="yes.option">
                {(msg) => <SelectItem disabled value="true" text={msg} />}
              </FormattedMessage>
              <FormattedMessage id="no.option">
                {(msg) => <SelectItem disabled value="false" text={msg} />}
              </FormattedMessage>
            </Select>
          )}
          {item.type == "choice" && item.repeats !== true && (
            <Select
              id={item.linkId}
              labelText={item.text}
              defaultValue={
                "inital" in item
                  ? "valueString" in item.initial[0]
                    ? item.initial[0].valueString
                    : "valueCoding" in item.initial[0]
                    ? item.initial[0].valueCoding
                    : ""
                  : ""
              }
              onChange={onAnswerChange}
              value={setAnswer(item.linkId)}
            >
              <FormattedMessage id="select.default.option.label">
                {(msg) => <SelectItem disabled value="" text={msg} />}
              </FormattedMessage>
              <SelectItem value="" text="" />
              {"answerOption" in item &&
                item.answerOption.map((answerOption, index) =>
                  getSelectOption(answerOption, index),
                )}
            </Select>
          )}
          {item.type == "choice" && item.repeats === true && (
            <FilterableMultiSelect
              id={item.linkId}
              titleText={item.text}
              label=""
              items={options}
              itemToString={(item) => (item ? item.text : "")}
              onChange={(changes) => {
                var e = { target: {} };
                e.target.id = item.linkId;
                e.target.value = changes.selectedItems;
                onAnswerChange(e);
              }}
              value={setAnswer(item.linkId)}
              selectionFeedback="top-after-reopen"
            />
          )}
          {item.type == "integer" && (
            <TextInput
              id={item.linkId}
              labelText={item.text}
              onChange={onAnswerChange}
              value={setAnswer(item.linkId)}
              type="number"
              step="1"
              pattern="\d+"
            />
          )}
          {item.type == "decimal" && (
            <TextInput
              id={item.linkId}
              labelText={item.text}
              onChange={onAnswerChange}
              value={setAnswer(item.linkId)}
              type="number"
              step="0.01"
            />
          )}
          {item.type == "date" && (
            <TextInput
              id={item.linkId}
              labelText={item.text}
              onChange={onAnswerChange}
              value={setAnswer(item.linkId)}
              type="date"
            />
          )}
          {item.type == "time" && (
            <TextInput
              id={item.linkId}
              labelText={item.text}
              onChange={onAnswerChange}
              value={setAnswer(item.linkId)}
              type="time"
            />
          )}
          {item.type == "string" && (
            <TextInput
              id={item.linkId}
              labelText={item.text}
              onChange={onAnswerChange}
              value={setAnswer(item.linkId)}
              type="text"
            />
          )}
          {item.type == "text" && (
            <TextInput
              id={item.linkId}
              labelText={item.text}
              onChange={onAnswerChange}
              value={setAnswer(item.linkId)}
              type="text"
            />
          )}
          {item.type == "quantity" && (
            <TextInput
              id={item.linkId}
              labelText={item.text}
              onChange={onAnswerChange}
              value={setAnswer(item.linkId)}
              type="number"
            />
          )}
        </div>
      </>
    );
  };
  if (questionnaire) {
    var inputs =
      "item" in questionnaire &&
      questionnaire.item.map((item, index) => {
        return <span key={index}>{renderQuestion(item)}</span>;
      });

    var groups = [];
    var children = [];
    var i = 0;
    for (; i < inputs.length; i++) {
      children.push(inputs[i]);
      if (children.length === 2) {
        groups.push(
          <div className="formInlineDiv" key={"group_" + i}>
            {children}
          </div>,
        );
        children = [];
      }
    }
    if (children.length > 0) {
      groups.push(
        <div className="formInlineDiv" key={"group_" + i}>
          {children}
        </div>,
      );
    }

    return <div className="extraQuestions">{groups}</div>;
  } else {
    return <></>;
  }
};

export const ProgramSelect = ({
  programChange = () => {
    console.log("default programChange function does nothing");
  },
  orderFormValues,
}) => {
  const componentMounted = useRef(true);

  const [programs, setPrograms] = useState([]);

  const fetchPrograms = (programsList) => {
    if (componentMounted.current) {
      setPrograms(programsList);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/user-programs", fetchPrograms);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  return (
    <>
      <div className="formInlineDiv">
        {programs.length > 0 && (
          <div className="inputText">
            <Select
              id="additionalQuestionsSelect"
              labelText={<FormattedMessage id="label.program" />}
              onChange={programChange}
              defaultValue={
                programs.find((program) => {
                  return program.value === "Routine Testing";
                })?.id
              }
              value={orderFormValues?.sampleOrderItems?.programId}
              disabled={true}
            >
              <SelectItem value="" text="" />
              {programs.map((program) => {
                return (
                  <SelectItem
                    key={program.id}
                    value={program.id}
                    text={program.value}
                  />
                );
              })}
            </Select>
          </div>
        )}
      </div>
    </>
  );
};

const EditOrderEntryAdditionalQuestions = ({
  orderFormValues,
  setOrderFormValues = () => {
    console.log("default setOrderFormValues change function does nothing");
  },
}) => {
  const [questionnaire, setQuestionnaire] = useState({});
  const [questionnaireResponse, setQuestionnaireResponse] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (orderFormValues?.sampleOrderItems?.programId) {
      getFromOpenElisServer(
        "/program/" +
          orderFormValues.sampleOrderItems.programId +
          "/questionnaire",
        setDefaultAdditionalQuestions,
      );
    }
    if (orderFormValues?.sampleOrderItems?.labNo) {
      if (!orderFormValues.sampleOrderItems.programId) {
        setLoading(false);
      }
    }
  }, [orderFormValues]);

  const handleProgramSelection = (event) => {
    if (event.target.value === "") {
      setAdditionalQuestions(null);
      setOrderFormValues({
        ...orderFormValues,
        sampleOrderItems: {
          ...orderFormValues.sampleOrderItems,
          programId: null,
        },
      });
    } else {
      getFromOpenElisServer(
        "/program/" + event.target.value + "/questionnaire",
        setAdditionalQuestions,
      );
      setOrderFormValues({
        ...orderFormValues,
        sampleOrderItems: {
          ...orderFormValues.sampleOrderItems,
          programId: event.target.value,
        },
      });
    }
  };

  function convertQuestionnaireToResponse(questionnaire) {
    var items = [];
    if (questionnaire && "item" in questionnaire) {
      for (let i = 0; i < questionnaire.item.length; i++) {
        let currentItem = questionnaire.item[i];
        items.push({
          linkId: currentItem.linkId,
          definition: currentItem.definition,
          text: currentItem.text,
          answer: [],
        });
      }

      var convertedQuestionnaireResponse = {
        resourceType: "QuestionnaireResponse",
        id: "",
        questionnaire: "Questionnaire/" + questionnaire.id,
        status: "in-progress",
        item: items,
      };
      return convertedQuestionnaireResponse;
    }
    return null;
  }

  function setAdditionalQuestions(res) {
    console.log(res);
    setQuestionnaire(res);
    var convertedQuestionnaireResponse = convertQuestionnaireToResponse(res);
    setQuestionnaireResponse(convertedQuestionnaireResponse);
  }

  function setDefaultAdditionalQuestions(res) {
    if (loading) {
      console.log(res);
      setQuestionnaire(res);
      setQuestionnaireResponse(
        orderFormValues.sampleOrderItems.additionalQuestions,
      );
      setLoading(false);
    }
  }

  const setAnswer = (linkId) => {
    var responseItem = questionnaireResponse?.item?.find(
      (item) => item.linkId === linkId,
    );
    var questionnaireItem = questionnaire?.item?.find(
      (item) => item.linkId === linkId,
    );
    switch (questionnaireItem.type) {
      case "boolean":
        return responseItem?.answer ? responseItem?.answer[0].valueBoolean : "";
      case "decimal":
        return responseItem?.answer ? responseItem?.answer[0].valueDecimal : "";
      case "integer":
        return responseItem?.answer ? responseItem?.answer[0].valueInteger : "";
      case "date":
        return responseItem?.answer ? responseItem?.answer[0].valueDate : "";
      case "time":
        return responseItem?.answer ? responseItem?.answer[0].valueTime : "";
      case "string":
      case "text":
        return responseItem?.answer ? responseItem?.answer[0].valueString : "";
      case "quantity":
        return responseItem?.answer
          ? responseItem?.answer[0].valueQuantity
          : "";
      case "choice":
        if (responseItem?.answer) {
          return responseItem.answer[0].valueCoding
            ? responseItem?.answer[0].valueCoding.code
            : responseItem?.answer[0].valueString;
        }
    }
  };

  const answerChange = (e) => {
    const { id, value } = e.target;

    var updatedQuestionnaireResponse = { ...questionnaireResponse };
    var responseItem = updatedQuestionnaireResponse.item.find(
      (item) => item.linkId === id,
    );
    var questionnaireItem = questionnaire.item.find(
      (item) => item.linkId === id,
    );
    responseItem.answer = [];
    if (value !== "") {
      switch (questionnaireItem.type) {
        case "boolean":
          responseItem.answer.push({ valueBoolean: value });
          break;
        case "decimal":
          responseItem.answer.push({ valueDecimal: value });
          break;
        case "integer":
          responseItem.answer.push({ valueInteger: value });
          break;
        case "date":
          responseItem.answer.push({ valueDate: value });
          break;
        case "time":
          responseItem.answer.push({ valueTime: value });
          break;
        case "string":
        case "text":
          responseItem.answer.push({ valueString: value });
          break;
        case "quantity":
          responseItem.answer.push({ valueQuantity: value });
          break;
        case "choice":
          //make single select and multiselect have the same shape to reuse code
          var items = value;
          if (!Array.isArray(items)) {
            items = [{ value: value }];
          }
          for (var i = 0; i < items.length; i++) {
            var curValue = items[i].value;
            var option = questionnaireItem?.answerOption?.find(
              (option) => option?.valueCoding?.code === curValue,
            );
            if (option) {
              responseItem.answer.push({ valueCoding: option.valueCoding });
            } else {
              option = questionnaireItem?.answerOption?.find(
                (option) => option.valueString === curValue,
              );
              if (option) {
                responseItem.answer.push({ valueString: option.valueString });
              } else {
                console.error(
                  "couldn't find a matching questionnaire answer for '" +
                    curValue +
                    "'",
                );
              }
            }
          }
          break;
      }
    }
    setQuestionnaireResponse(updatedQuestionnaireResponse);
    setOrderFormValues({
      ...orderFormValues,
      sampleOrderItems: {
        ...orderFormValues.sampleOrderItems,
        additionalQuestions: updatedQuestionnaireResponse,
      },
    });
  };

  return (
    <>
      <Stack gap={10}>
        <div className="orderLegendBody">
          <h3>
            <FormattedMessage id="label.program" />
          </h3>
          <ProgramSelect
            orderFormValues={orderFormValues}
            programChange={handleProgramSelection}
          />
          <Questionnaire
            questionnaire={questionnaire}
            onAnswerChange={answerChange}
            setAnswer={setAnswer}
          />
          {questionnaireResponse && (
            <input
              type="hidden"
              name="additionalQuestions"
              value={questionnaireResponse}
            />
          )}
          {loading && <InlineLoading />}
        </div>
      </Stack>
    </>
  );
};

export default EditOrderEntryAdditionalQuestions;
