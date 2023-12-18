import React, { useEffect, useRef, useState } from "react";
import {
  FilterableMultiSelect,
  Select,
  SelectItem,
  TextInput,
  Stack,
  InlineLoading,
} from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import "../../index.css";
import "../../App.css";
import "../Style.css";
import { getFromOpenElisServer } from "../utils/Utils";
import {
  Questionnaire,
  ProgramSelect,
} from "../addOrder/OrderEntryAdditionalQuestions";

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
    if (!event.target.value) {
      setAdditionalQuestions(null);
      setOrderFormValues({
        ...orderFormValues,
        sampleOrderItems: {
          ...orderFormValues.sampleOrderItems,
          programId: "",
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
            editable={true}
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
