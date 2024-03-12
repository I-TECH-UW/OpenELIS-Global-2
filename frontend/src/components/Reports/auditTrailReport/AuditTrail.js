import React, { useState } from 'react';
import {
  Form,
  Heading,
  Select,
  SelectItem,
  Grid,
  Column,
  Section,
  Button,
  Loading,
} from "@carbon/react";
import "../../Style.css";

import { AlertDialog } from '../../common/CustomNotification';
import config from "../../../config.json";
import CustomLabNumberInput from '../../common/CustomLabNumberInput';
import { FormattedMessage, useIntl } from 'react-intl';


const AuditTrail = () => {
    const [labNo, setLabNo] = useState("");
    const [isLabNoError, setIsLabNoError] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [showNotification, setShowNotification] = useState(false);

    const intl = useIntl();

    const handleViewReport = () => {
        if(labNo.length === 0){
            setIsLabNoError(true);
            return;
        }

        setIsLoading(true);
        const url = config.serverBaseUrl + `/AuditTrailReport?accessionNumberSearch=${labNo}`;

        window.open(url);
        setIsLoading(false);
        setShowNotification(true);
    }

    return (
        <>
        <br />
        <Grid fullWidth={true}>
            <Column lg={4} md={4} sm={4}>
            <Section>
                <Section>
                <Heading>
                    <FormattedMessage id="reports.auditTrail" />
                </Heading>
                </Section>
            </Section>
            <br/>
            </Column>
        </Grid>
        <br />
        {showNotification && <AlertDialog />}
        <br/>
        <Form>
          <Grid fullWidth={true}>
            <Column lg={6} md={16} sm={4}>
              <CustomLabNumberInput
                id="labNo"
                labelText={intl.formatMessage({
                    id: "label.audittrail",
                    defaultMessage: "Lab No",
                })}
                className="inputText"
                value={labNo}
                onChange={(event) => setLabNo(event.target.value)}
                invalid={isLabNoError ? intl.formatMessage({
                    id: "label.audittrail.labNo.missing"
                }): ""}
                invalidText={intl.formatMessage({
                    id: "label.audittrail.labNo.missing"
                })}
              />
            </Column>
          </Grid>
          <br />
          <br />
          <Grid fullWidth={true}>
            <Column lg={16}>
              <Section>
                <Button type="button" onClick={handleViewReport}>
                    <FormattedMessage id="label.button.viewReport" />
                    <Loading
                        small={true}
                        withOverlay={false}
                        className={isLoading ? "show" : "hidden"}
                    />
                </Button>
              </Section>
            </Column>
          </Grid>
        </Form>
      </>
    )
}

export default AuditTrail