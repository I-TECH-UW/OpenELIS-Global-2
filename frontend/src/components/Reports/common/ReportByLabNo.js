import React, { useState } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import { Form, Grid, Column, Section, Button } from "@carbon/react";
import CustomLabNumberInput from "../../common/CustomLabNumberInput";
import { AlertDialog } from "../../common/CustomNotification";

function ReportByLabNo(props) {
 
  const intl = useIntl();
  const [values, setValues] = useState({ from: '', to: '' });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setValues(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form values:", values);
    // You can perform further actions here, such as API calls or state updates
  };

  return (
    <>
      <Form onSubmit={handleSubmit}>
       
            <Section>
            <Section>
              <h3>
                {/* <FormattedMessage
                  id={props.id || "default.id"}
                  defaultMessage="Hello"
                /> */} ARV - Initial
              </h3>
            </Section>
            </Section>


              <br />
      
              <Grid fullWidth={true}>
          <Column lg={16}>
          <Section>
              <h5>
                {/* <FormattedMessage id="sample.search.scanner.instructions" /> */}
                Generate a report or range of reports by Order Number / Lab Number
              </h5>
              <h7>
              Scan or Enter Manually. For a single report, leave the box at the right empty
                {/* <FormattedMessage id="sample.search.scanner.instructions.highaccession" /> */}
              </h7>
              
              </Section>
          </Column>
        </Grid>
        <br />
        <Grid fullWidth={true}>


          <Column lg={7}>
            <CustomLabNumberInput
              name="from"
              value={values.from}
              labelText={intl.formatMessage({
                id: "from.title",
                defaultMessage: "From",
              })}
              id="from"
              className="inputText"
              onChange={handleChange}
            />
          </Column>
          <Column lg={7}>
            <CustomLabNumberInput
              name="to"
              value={values.to}
              labelText={intl.formatMessage({
                id: "to.title",
                defaultMessage: "To",
              })}
              id="to"
              className="inputText"
              onChange={handleChange}
            />
          </Column>
        
        </Grid>
        <br />
              <br />
        <Grid fullWidth={true}>
          <Column lg={16}>
           <Section>
                <Button type="submit">
                  <FormattedMessage id="label.button.generatePrintableVersion" />
                </Button>
                </Section>
          </Column>
        </Grid>
      </Form>
    </>
  );
}

export default injectIntl(ReportByLabNo);