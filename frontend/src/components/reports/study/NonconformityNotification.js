import React, { useState, useEffect } from "react";
import { Grid, Column, Section, Button, Form, FormLabel, Select, SelectItem } from "@carbon/react";
import { FormattedMessage, useIntl } from "react-intl";
import CustomLabNumberInput from '../../common/CustomLabNumberInput';
import config from "../../../config.json";
// Removed PageBreadCrumb import as it's not used in the provided code snippet
import { getFromOpenElisServer } from "../../utils/Utils";
import { AlertDialog } from "../../common/CustomNotification";

const NonconformityNotification = () => {
  const intl = useIntl();
  const [accessionNumber, setAccessionNumber] = useState('');
  const [selectedSite, setSelectedSite] = useState('');
  const [loading, setLoading] = useState(false);
  const [notificationMessage, setNotificationMessage] = useState('');
  const [error, setError] = useState('');
  const [siteOptions, setSiteOptions] = useState([]);
  const [showAlert, setShowAlert] = useState(false); // Added for AlertDialog

  useEffect(() => {
    getFromOpenElisServer('/rest/site-names')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        const options = data.map(site => ({
          label: site.name, 
          value: site.id    
        }));
        setSiteOptions(options); 
      })
      .catch(error => {
        console.error('There was a problem fetching data:', error);
      });
  }, []); 

  const handleAccessionNumberChange = (event) => {
    setAccessionNumber(event.target.value);
  };

  const handleSiteChange = (event) => {
    setSelectedSite(event.target.value);
  };

  const handlePrinting = () => {
    if (!accessionNumber) {
      setError('Accession number is required.');
      return;
    }

    setLoading(true);
    const baseParams = "report=nonConformityNotification&type=patient";
    const baseUrl = `${config.serverBaseUrl}/ReportPrint`;
    const url = `${baseUrl}?${baseParams}&accessionDirect=${accessionNumber}&selectList.selection=${selectedSite}`;

    const check = window.open(url, "_blank");
    if (check) {
      setLoading(false);
      setNotificationMessage('Form submitted successfully.');
      setAccessionNumber('');
      setSelectedSite('');
      setError('');
      setShowAlert(false); // Reset the alert show state
    } else {
      setLoading(false);
      setShowAlert(true); // Show alert dialog if window.open fails
    }
  };

  return (
    <>
      {showAlert && <AlertDialog />} {/* Conditionally render the AlertDialog */}
      <Form onSubmit={handlePrinting}>
        <Section>
          <h3>
            <FormattedMessage id="nonConformity.header" defaultMessage="Non-Conformity Notification" />
          </h3>
        </Section>
        <br />
        <Grid fullWidth={true}>
          <Column lg={8} md={4} sm={2}>
            <CustomLabNumberInput
              id="accessionDirect"
              labelText={intl.formatMessage({ id: 'from.title', defaultMessage: 'From' })}
              value={accessionNumber}
              onChange={handleAccessionNumberChange}
              maxLength={11}
              invalid={!!error}
              invalidText={error}
            />
            <Select
              id="selectList"
              labelText={intl.formatMessage({ id: 'select.site', defaultMessage: 'Select site' })}
              onChange={handleSiteChange}
              value={selectedSite}
            >
              {siteOptions.map((option, index) => (
                <SelectItem key={index} text={option.label} value={option.value} />
              ))}
            </Select>
            <p>
              If a service is selected then any non-conformity notifications which have not yet been printed will be printed. 
              If there are no services listed then all notifications have already been printed.
              <br />
              Entering a laboratory number will print a notification even if it has already been printed. 
              A service does not have to be chosen.
            </p>
            <Button kind="primary" type="submit">
              {loading ? (
                <FormattedMessage id="button.loading" defaultMessage="Loading..." />
              ) : (
                <FormattedMessage id="button.generate" defaultMessage="Generate printable version" />
              )}
            </Button>
          </Column>
        </Grid>
      </Form>
    </>
  );
};

export default NonconformityNotification;