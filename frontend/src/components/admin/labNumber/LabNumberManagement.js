import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Form,
  FormLabel,
  Heading,
  Checkbox,
  TextInput,
  Select,
  SelectItem,
  Button,
  Loading,
  Grid,
  Column,
  Section,
} from "@carbon/react";
import LabNumberFormValues from "./LabNumberFormValues";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
  convertAlphaNumLabNumForDisplay,
} from "../../utils/Utils";
import { NotificationContext } from "../../layout/Layout";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification";
import { FormattedMessage, useIntl } from "react-intl";
import { ConfigurationContext } from "../../layout/Layout";

function LabNumberManagement() {
  const intl = useIntl();

  const componentMounted = useRef(true);

  const { configurationProperties, reloadConfiguration } =
    useContext(ConfigurationContext);
  const { notificationVisible, setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);

  const [currentLabNumForDisplay, setCurrentLabNumForDisplay] = useState(
    convertAlphaNumLabNumForDisplay("23000000"),
  );
  const [sampleLabNumForDisplay, setSampleLabNumForDisplay] = useState(
    convertAlphaNumLabNumForDisplay("23000000"),
  );
  const [loading, setLoading] = useState(true);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [labNumberValues, setLabNumberValues] = useState(LabNumberFormValues);

  useEffect(() => {
    loadValues();
    return () => {
      componentMounted.current = false;
    };
  }, []);

  useEffect(() => {
    fetchCurrentLabNumberNoIncrement();
    return () => {
      componentMounted.current = false;
    };
  }, [configurationProperties]);

  useEffect(() => {
    if (!(labNumberValues.labNumberType === "ALPHANUM")) {
      fetchLegacyLabNumNoIncrement();
    }
    generateSampleLabNum();
    return () => {
      componentMounted.current = false;
    };
  }, [labNumberValues]);

  const handleFieldChange = (e) => {
    const { name, value } = e.target;
    //TODO use better strategy developed with greg
    var names = name.split(".");
    const updatedValues = { ...labNumberValues };
    if (names.length === 1) {
      updatedValues[name] = value;
    } else if (names.length === 2) {
      updatedValues[names[0]][names[1]] = value;
    }
    setLabNumberValues(updatedValues);
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
      var body = await res.json();
      setLabNumberValues(body);
    } else {
      setNotificationBody({
        kind: NotificationKinds.error,
        title: <FormattedMessage id="notification.title" />,
        message: <FormattedMessage id="error.add.edited.msg" />,
      });
    }
    reloadConfiguration();
  }

  const loadValues = () => {
    getFromOpenElisServer("/rest/labnumbermanagement", (body) => {
      setLabNumberValues(body);
      setLoading(false);
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setIsSubmitting(true);
    var submitValues = { ...labNumberValues };
    postToOpenElisServerFullResponse(
      "/rest/labnumbermanagement",
      JSON.stringify(submitValues),
      displayStatus,
    );
  };

  const fetchCurrentLabNumberNoIncrement = () => {
    getFromOpenElisServer(
      "/rest/SampleEntryGenerateScanProvider?noIncrement=true",
      (res) => {
        if (res.status) {
          if (configurationProperties.AccessionFormat != "ALPHANUM") {
            setCurrentLabNumForDisplay(res.body);
          } else {
            setCurrentLabNumForDisplay(
              convertAlphaNumLabNumForDisplay(res.body),
            );
          }
        }
      },
    );
  };

  const generateSampleLabNum = () => {
    let dateDigits = new Date().getFullYear() % 100;
    let labNumber = "" + dateDigits;
    if (labNumberValues.usePrefix && labNumberValues.alphanumPrefix) {
      labNumber = labNumber + labNumberValues.alphanumPrefix;
    }
    labNumber = labNumber + "000000";
    setSampleLabNumForDisplay(convertAlphaNumLabNumForDisplay(labNumber));
  };

  const fetchLegacyLabNumNoIncrement = () => {
    getFromOpenElisServer(
      "/rest/SampleEntryGenerateScanProvider?noIncrement=true&format=SITEYEARNUM",
      (res) => {
        if (res.status) {
          setSampleLabNumForDisplay(res.body);
        }
      },
    );
  };

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      {loading && <Loading />}
      <div className="adminPageContent">
        <Grid >
          <Column lg={16}>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="configure.labNumber.title" />
                </Heading>
              </Section>
            </Section>
          </Column>
        </Grid>
        <Form onSubmit={handleSubmit}>
          <div className="inlineDiv">
            <Select
              id="lab_number_type"
              labelText={intl.formatMessage({ id: "labNumber.type" })}
              name="labNumberType"
              value={labNumberValues.labNumberType}
              onChange={handleFieldChange}
            >
              <SelectItem value="ALPHANUM" text="Alpha Numeric" />
              <SelectItem value="SITEYEARNUM" text="Legacy" />
            </Select>
          </div>
          {labNumberValues.labNumberType === "ALPHANUM" && (
            <>
              <div className="inlineDiv">
                <Checkbox
                  type="checkbox"
                  name="usePrefix"
                  id="usePrefix"
                  labelText={intl.formatMessage({ id: "labNumber.usePrefix" })}
                  checked={labNumberValues.usePrefix}
                  onClick={() => {
                    const updatedValues = { ...labNumberValues };
                    updatedValues.usePrefix = !labNumberValues.usePrefix;
                    setLabNumberValues(updatedValues);
                  }}
                />
              </div>
              <div className="inlineDiv">
                <TextInput
                  type="text"
                  name="alphanumPrefix"
                  id="alphanumPrefix"
                  labelText={intl.formatMessage({ id: "labNumber.prefix" })}
                  disabled={!labNumberValues.usePrefix}
                  value={labNumberValues.alphanumPrefix}
                  onChange={handleFieldChange}
                  enableCounter={true}
                  maxCount={5}
                />
              </div>
            </>
          )}
          <div className="inlineDiv">
            <FormattedMessage id="labNumber.format.current" />:{" "}
            {currentLabNumForDisplay}
          </div>
          <div className="inlineDiv">
            <FormattedMessage id="labNumber.format.new" />:{" "}
            {sampleLabNumForDisplay}
          </div>
          <div className="inlineDiv">
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

export default LabNumberManagement;
