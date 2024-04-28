import React, { useState, useEffect, useContext } from "react";
import {
  Grid,
  Column,
  Section,
  Heading,
  RadioButtonGroup,
  RadioButton,
  TextInput,
  Button,
  Loading,
  Dropdown,
} from "@carbon/react";
import {
  getFromOpenElisServer,
  postToOpenElisServer,
} from "../../../utils/Utils.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../../common/CustomNotification.js";
import config from "../../../../config.json";
import { NotificationContext } from "../../../layout/Layout.js";

import { FormattedMessage, useIntl } from "react-intl";

const GenericConfigEdit = ({ menuType, ID }) => {
  const intl = useIntl();

  const [FormEntryConfig, setFormEntryConfig] = useState(null);
  const [radioValue, setRadioValue] = useState("");
  const [textInputEnglishValue, setTextInputEnglishValue] = useState("");
  const [textInputFrenchValue, setTextInputFrenchValue] = useState("");
  const [textInputValue, setTextInputValue] = useState("");
  const [selectedDictionaryValue, setSelectedDictionaryValue] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  useEffect(() => {
    setIsLoading(true);
    getFromOpenElisServer(`/rest/${menuType}?ID=${ID}`, handleMenuItems);
  }, [menuType, ID]);

  const handleMenuItems = (res) => {
    setFormEntryConfig(res);
    if (res.localization) {
      setTextInputEnglishValue(res.localization.localeValues.en);
      setTextInputFrenchValue(res.localization.localeValues.fr);
    }
    if (res.valueType === "boolean") {
      setRadioValue(res.value);
    }
    if (res.valueType === "dictionary") {
      setSelectedDictionaryValue(res.value);
    }
    setTextInputValue(res.value);
    setIsLoading(false);
  };

  const updateFormEntryConfig = (newState) => {
    setFormEntryConfig((prevState) => ({
      ...prevState,
      ...newState,
    }));
  };

  const handleRadioChange = (value) => {
    setRadioValue(value);
    updateFormEntryConfig({ value });
  };

  const handleInputChange = (event) => {
    const newValue = event.target.value;
    setTextInputValue(newValue);
    updateFormEntryConfig({ value: newValue });
  };

  const handleInputEnglishChange = (event) => {
    const newValue = event.target.value;
    setTextInputEnglishValue(newValue);
    updateFormEntryConfig({
      localization: {
        ...FormEntryConfig.localization,
        localeValues: {
          ...FormEntryConfig.localization.localeValues,
          en: newValue,
        },
      },
    });
  };

  const handleInputFrenchChange = (event) => {
    const newValue = event.target.value;
    setTextInputFrenchValue(newValue);
    updateFormEntryConfig({
      localization: {
        ...FormEntryConfig.localization,
        localeValues: {
          ...FormEntryConfig.localization.localeValues,
          fr: newValue,
        },
      },
    });
  };

  const handleDictionaryChange = (event) => {
    const newValue = event.selectedItem;
    setSelectedDictionaryValue(newValue);
    updateFormEntryConfig({ value: newValue });
  };

  const showAlertMessage = (msg, kind) => {
    setNotificationVisible(true);
    addNotification({
      kind: kind,
      title: intl.formatMessage({ id: "notification.title" }),
      message: msg,
    });
  };

  const handleSubmitButton = () => {
    const body = JSON.stringify(FormEntryConfig);
    postToOpenElisServer(`/rest/${menuType}?ID=${ID}`, body, handleSubmit);
  };

  const handleSubmit = (status) => {
    if (status === 200) {
      showAlertMessage(
        intl.formatMessage({ id: "save.config.success.msg" }),
        NotificationKinds.success,
      );
    } else {
      showAlertMessage(
        intl.formatMessage({ id: "server.error.msg" }),
        NotificationKinds.error,
      );
    }
  };

  return (
    <div className="adminPageContent">
      {isLoading && (
        <Loading
          description={intl.formatMessage({ id: "loading.description" })}
        />
      )}
      {notificationVisible === true ? <AlertDialog /> : ""}
      {FormEntryConfig && (
        <>
          <Grid>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Heading>
                  <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.editRecord" />
                </Heading>
              </Section>
              <br />
            </Column>
          </Grid>
          <div className="orderLegendBody">
            <div className="gridBoundary">
              <Grid fullWidth={true}>
                <Column lg={3}>
                  <h4>
                    <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.name" />
                  </h4>
                </Column>
                <Column lg={3}>{FormEntryConfig.paramName}</Column>
              </Grid>
              <br />
              <Grid fullWidth={true}>
                <Column lg={3}>
                  <h4>
                    <FormattedMessage id="description.label" />
                  </h4>
                </Column>
                <Column lg={7}>{FormEntryConfig.description}</Column>
              </Grid>
              <br />
              {FormEntryConfig.valueType === "boolean" && (
                <Grid fullWidth={true}>
                  <Column lg={3}>
                    <h4>
                      <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.value" />
                    </h4>
                  </Column>
                  <Column lg={5}>
                    <RadioButtonGroup
                      name="radioValue"
                      valueSelected={radioValue}
                      onChange={handleRadioChange}
                    >
                      <RadioButton
                        labelText={intl.formatMessage({ id: "true.label" })}
                        value="true"
                        id="radio-1"
                      />
                      <RadioButton
                        labelText={intl.formatMessage({ id: "false.label" })}
                        value="false"
                        id="radio-2"
                      />
                    </RadioButtonGroup>
                  </Column>
                </Grid>
              )}
              {FormEntryConfig.valueType === "dictionary" && (
                <>
                  <Grid fullWidth={true}>
                    <Column lg={3}>
                      <h4>
                        <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.value" />
                      </h4>
                    </Column>
                    <Column lg={3}>
                      <Dropdown
                        id="dictionaryDropdown"
                        items={FormEntryConfig.dictionaryValues}
                        selectedItem={selectedDictionaryValue}
                        onChange={handleDictionaryChange}
                      />
                    </Column>
                  </Grid>
                </>
              )}
              {FormEntryConfig.valueType === "text" && (
                <>
                  <Grid fullWidth={true}>
                    <Column lg={3}>
                      <h4>
                        <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.value" />
                      </h4>
                    </Column>
                    {!FormEntryConfig.tag && (
                      <Column lg={3}>
                        <TextInput
                          id="textInput"
                          value={textInputValue}
                          onChange={handleInputChange}
                        />
                      </Column>
                    )}
                  </Grid>
                  {FormEntryConfig.tag === "localization" && (
                    <>
                      <br />
                      <Grid>
                        <Column lg={3}>
                          <TextInput
                            id="myInputEnglish"
                            labelText={<FormattedMessage id="english.label" />}
                            value={textInputEnglishValue}
                            onChange={handleInputEnglishChange}
                          />
                        </Column>
                        <Column lg={3}>
                          <TextInput
                            id="myInputFrench"
                            labelText={<FormattedMessage id="french.label" />}
                            value={textInputFrenchValue}
                            onChange={handleInputFrenchChange}
                          />
                        </Column>
                      </Grid>
                    </>
                  )}
                </>
              )}
              <br />
              <br />
              <Grid fullWidth={true}>
                <Column lg={2}>
                  <Button onClick={handleSubmitButton} disabled={isLoading}>
                    <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.save" />
                  </Button>
                </Column>
                <Column lg={2}>
                  <Button onClick={() => window.location.reload()}>
                    <FormattedMessage id="admin.page.configuration.formEntryConfigMenu.button.exit" />
                  </Button>
                </Column>
              </Grid>
            </div>
          </div>
        </>
      )}
    </div>
  );
};

export default GenericConfigEdit;
