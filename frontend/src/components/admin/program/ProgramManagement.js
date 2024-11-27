import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Form,
  FormLabel,
  Heading,
  TextArea,
  TextInput,
  Select,
  SelectItem,
  Button,
  Loading,
  Grid,
  Column,
  Section,
} from "@carbon/react";
import ProgramFormValues from "./ProgramFormValues";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../utils/Utils";
import { Questionnaire } from "../../addOrder/OrderEntryAdditionalQuestions";
import { NotificationContext } from "../../layout/Layout";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification";
import { FormattedMessage ,useIntl} from "react-intl";

function ProgramManagement() {
  const componentMounted = useRef(true);
  const [programs, setPrograms] = useState([]);
  const [testSections, setTestSections] = useState([]);
  const [loading, setLoading] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [programValues, setProgramValues] = useState(ProgramFormValues);
  const { notificationVisible, setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);
  const intl = useIntl();

  const fetchPrograms = (programsList) => {
    if (componentMounted.current) {
      setPrograms(programsList);
    }
  };

  const fetchTestSections = (testSectionListList) => {
    if (componentMounted.current) {
      setTestSections(testSectionListList);
    }
  };

  const handleProgramSelection = (event) => {
    if (event.target.value === "") {
      setProgramValues(ProgramFormValues);
    } else {
      setLoading(true);
      getFromOpenElisServer(
        "/program/" + event.target.value,
        setAdditionalQuestions,
      );
    }
  };

  function setAdditionalQuestions(res) {
    console.log(res);
    if (res.additionalOrderEntryQuestions) {
      res.additionalOrderEntryQuestions = JSON.stringify(
        res.additionalOrderEntryQuestions,
        null,
        4,
      );
    }
    setProgramValues(res);
    setLoading(false);
  }

  const handleFieldChange = (e) => {
    const { name, value } = e.target;
    //TODO use better strategy developed with greg
    var names = name.split(".");
    const updatedValues = { ...programValues };
    if (names.length === 1) {
      updatedValues[name] = value;
    } else if (names.length === 2) {
      updatedValues[names[0]][names[1]] = value;
    }
    setProgramValues(updatedValues);
  };

  async function displayStatus(res) {
    setNotificationVisible(true);
    setIsSubmitting(false);
    if (res.status == "200") {
      setNotificationBody({
        kind: NotificationKinds.success,
        title: <FormattedMessage id="notification.title" />,
        message: <FormattedMessage id="success.add.edited.msg" />,
      });
      getFromOpenElisServer("/rest/displayList/PROGRAM", fetchPrograms);
      var body = await res.json();
      if (body.additionalOrderEntryQuestions) {
        body.additionalOrderEntryQuestions = JSON.stringify(
          body.additionalOrderEntryQuestions,
          null,
          4,
        );
      }
      setProgramValues(body);
    } else {
      setNotificationBody({
        kind: NotificationKinds.error,
        title: <FormattedMessage id="notification.title" />,
        message: <FormattedMessage id="error.add.edited.msg" />,
      });
    }
  }

  function isJson(item) {
    let value = typeof item !== "string" ? JSON.stringify(item) : item;
    try {
      value = JSON.parse(value);
    } catch (e) {
      return false;
    }

    return typeof value === "object" && value !== null;
  }

  function handleSubmit(event) {
    event.preventDefault();
    setIsSubmitting(true);
    var submitValues = { ...programValues };
    if (submitValues.additionalOrderEntryQuestions) {
      submitValues.additionalOrderEntryQuestions = JSON.parse(
        submitValues.additionalOrderEntryQuestions,
      );
    } else {
      delete submitValues["additionalOrderEntryQuestions"];
    }
    postToOpenElisServerFullResponse(
      "/program",
      JSON.stringify(submitValues),
      displayStatus,
    );
  }

  useEffect(() => {
    getFromOpenElisServer("/rest/displayList/PROGRAM", fetchPrograms);
    getFromOpenElisServer(
      "/rest/displayList/TEST_SECTION_ACTIVE",
      fetchTestSections,
    );

    return () => {
      componentMounted.current = false;
    };
  }, []);

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <div className="adminPageContent">
      <Grid >
          <Column lg={16}>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="edit.add.program.title" />
                </Heading>
              </Section>
            </Section>
          </Column>
        </Grid>
        <Form onSubmit={handleSubmit}>
          <div className="formInlineDiv">
            <Select
              id="additionalQuestionsSelect"
              labelText="program"
              onChange={handleProgramSelection}
            >
              <SelectItem
                value=""
                text={intl.formatMessage({ id :"new.program.label"})}
              />
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
            {loading && <Loading />}
          </div>

          <div className="formInlineDiv">
            <input
              type="hidden"
              name="program.id"
              value={programValues.program.id}
              onChange={handleFieldChange}
            />
            <TextInput
              type="text"
              name="program.programName"
              id="program.programName"
              labelText={<FormattedMessage id="program.name.label" />}
              value={programValues.program.programName}
              onChange={handleFieldChange}
            />
          </div>
          <div className="formInlineDiv">
            <Select
              id="test_section"
              labelText={<FormattedMessage id="test.section.label" />}
              name="testSectionId"
              value={programValues.testSectionId}
              onChange={handleFieldChange}
            >
              <SelectItem value="" text="" />
              {testSections.map((testSection) => {
                return (
                  <SelectItem
                    key={testSection.id}
                    value={testSection.id}
                    text={testSection.value}
                  />
                );
              })}
            </Select>
          </div>
          <div className="formInlineDiv">
            <TextInput
              type="text"
              name="program.questionnaireUUID"
              id="program.questionnaireUUID"
              labelText="UUID"
              disabled={programValues.program.id !== "" ? true : false}
              value={programValues.program.questionnaireUUID}
              onChange={handleFieldChange}
            />
            <TextInput
              type="text"
              name="program.code"
              id="program.code"
              labelText="Code"
              maxLength="10"
              value={programValues.program.code}
              onChange={handleFieldChange}
            />
          </div>
          <div className="formInlineDiv">
            <TextArea
              name="additionalOrderEntryQuestions"
              id="additionalOrderEntryQuestions"
              labelText="Questionnaire"
              value={programValues.additionalOrderEntryQuestions}
              onChange={handleFieldChange}
            />
            {isJson(programValues.additionalOrderEntryQuestions) && (
              <div>
                <FormLabel>Example</FormLabel>
                <div className="exampleDiv">
                  <Questionnaire
                    questionnaire={JSON.parse(
                      programValues.additionalOrderEntryQuestions,
                    )}
                  />
                </div>
              </div>
            )}
          </div>

          <div>
            <Button type="submit">
              <FormattedMessage id="label.button.submit" />
              {isSubmitting && <Loading small={true} />}
            </Button>
          </div>
        </Form>
      </div>
    </>
  );
}

export default ProgramManagement;
