import  { useContext, useState, useEffect, useRef } from "react";
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
import { FormattedMessage, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb";

let breadcrumbs = [{ label: "home.label", link: "/" }];
function ProgramManagement() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const componentMounted = useRef(false);

  const intl = useIntl();

  const [programs, setPrograms] = useState([]);
  const [testSections, setTestSections] = useState([]);
  const [loading, setLoading] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [programValues, setProgramValues] = useState(ProgramFormValues);

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
        "/rest/program/" + event.target.value,
        setAdditionalQuestions,
      );
    }
  };

  function setAdditionalQuestions(res) {
    console.debug(res);
    if (res.additionalOrderEntryQuestions) {
      res.additionalOrderEntryQuestions = JSON.stringify(
        res.additionalOrderEntryQuestions,
        null,
        4,
      );
    }
    const newProgramValues = {
      ...ProgramFormValues,
      ...res,
      program: { ...ProgramFormValues.program, ...res.program },
    };
    setProgramValues(newProgramValues);
    setLoading(false);
  }

  const handleFieldChange = (e) => {
    const { name, value } = e.target;
    const updatedValues = { ...programValues };
    var jp = require("jsonpath");
    jp.value(updatedValues, name, value);
    setProgramValues(updatedValues);
  };

  async function displayStatus(res) {
    setNotificationVisible(true);
    setIsSubmitting(false);
    if (res.status == "200") {
      addNotification({
        kind: NotificationKinds.success,
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({ id: "success.add.edited.msg" }),
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
      const newProgramValues = { ...ProgramFormValues, ...body };
      setProgramValues(newProgramValues);
    } else {
      addNotification({
        kind: NotificationKinds.error,
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({ id: "error.add.edited.msg" }),
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
      "/rest/program",
      JSON.stringify(submitValues),
      displayStatus,
    );
  }

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/displayList/PROGRAM", fetchPrograms);
    getFromOpenElisServer(
      "/rest/displayList/TEST_SECTION_ACTIVE",
      fetchTestSections,
    );

    return () => {
      componentMounted.current = false;
    };
  }, []);

  const additionalOrderEntryQuestionsAreJson = isJson(
    programValues.additionalOrderEntryQuestions,
  );

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <Grid>
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
              labelText="Program"
              onChange={handleProgramSelection}
            >
              <SelectItem
                value=""
                text={intl.formatMessage({ id: "new.program.label" })}
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
              labelText={intl.formatMessage({ id: "program.name.label" })}
              value={programValues.program.programName}
              onChange={handleFieldChange}
            />
            <TextInput
              type="text"
              name="program.questionnaireUUID"
              id="program.questionnaireUUID"
              labelText="UUID"
              disabled={programValues.program.id !== "" ? true : false}
              value={programValues.program.questionnaireUUID}
              onChange={handleFieldChange}
            />
          </div>
          <div className="formInlineDiv">
            <TextInput
              type="text"
              name="program.code"
              id="program.code"
              labelText="Code"
              maxLength="10"
              value={programValues.program.code}
              onChange={handleFieldChange}
            />
            <Select
              id="test_section"
              labelText={intl.formatMessage({ id: "test.section.label" })}
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
            <TextArea
              name="additionalOrderEntryQuestions"
              id="additionalOrderEntryQuestions"
              labelText="Questionnaire"
              value={programValues.additionalOrderEntryQuestions || ""}
              onChange={handleFieldChange}
              invalid={
                !additionalOrderEntryQuestionsAreJson &&
                programValues.additionalOrderEntryQuestions !== ""
              }
              invalidText={intl.formatMessage({ id: "invalid.json" })}
            />
            {additionalOrderEntryQuestionsAreJson && (
              <div>
                <FormLabel>
                  <FormattedMessage id="example" />
                </FormLabel>
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
          <br></br>
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
