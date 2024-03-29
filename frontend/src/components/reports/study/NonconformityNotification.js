import React, { useState } from "react";
import { Grid, Column, Section, Button, Form, FormLabel } from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import CustomDatePicker from "../../common/CustomDatePicker";
import config from "../../../config.json";

const NonconformityNotification = () => {
  const [accessionNumber, setAccessionNumber] = useState('');
  const [selectedSite, setSelectedSite] = useState('');
  const [loading, setLoading] = useState(false);
  const [notificationMessage, setNotificationMessage] = useState('');
  const [error, setError] = useState('');
  
  
  
  const siteOptions = [
    { label: '', value: '' },
    { label: 'Site 1', value: 'site1' },
    { label: 'Site 2', value: 'site2' },
  ];

  const handleAccessionNumberChange = (event) => {
    setAccessionNumber(event.target.value);
  };

  const handleSiteChange = (event) => {
    setSelectedSite(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!accessionNumber) {
      setError('Accession number is required.');
      return;
    }
    setLoading(true);
    // Simulating API call delay with setTimeout
    setTimeout(() => {
      setLoading(false);
      setNotificationMessage('Form submitted successfully.');
      setAccessionNumber('');
      setSelectedSite('');
      setError('');
    }, 2000);
  };

  useEffect(() => {
    const timer = setTimeout(() => {
      setNotificationMessage('');
    }, 3000);
    return () => clearTimeout(timer);
  }, [notificationMessage]);

  return (
    <div>
      <h2>Non-conformity notification</h2>
      <Form onSubmit={handleSubmit}>
        <FormGroup legendText="Generate a report or range of reports by Lab Number">
          <TextInput
            id="accessionDirect"
            labelText="From"
            value={accessionNumber}
            onChange={handleAccessionNumberChange}
            maxLength={11}
            invalid={!!error}
            invalidText={error}
          />
        </FormGroup>
        <Select
          id="selectList"
          labelText="Select site"
          onChange={handleSiteChange}
          value={selectedSite}
        >
          {siteOptions.map((option, index) => (
            <SelectItem key={index} text={option.label} value={option.value} />
          ))}
        </Select>
        <br />
        <InlineNotification
          kind="success"
          hideCloseButton
          title={notificationMessage}
        />
        <Button kind="primary" type="submit">
          {loading ? <Loading small /> : 'Generate printable version'}
        </Button>
      </Form>
    </div>
  );
};

export default NonconformityNotification;