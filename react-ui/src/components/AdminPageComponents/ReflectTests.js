import React from 'react'
import '../Style.css'
import { FormattedMessage, injectIntl } from 'react-intl'
import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading } from '@carbon/react';
import { Formik } from "formik";


class ReflectTests extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      hasSubmitted: false,
      isSubmitting: false
    }
  }
  validationHandler = ({ email, password, village }) => {
    let errors = {};

    if (!email) {
      //errors.email = "Required";
    }
    if (!password) {
      //  errors.password = "Required";
    }

    if (!village) {
      //  errors.village = "Required";
    }

    return errors;
  }

  submissionHandler = (values) => {
    console.log(JSON.stringify(values));
    this.setState({
      isSubmitting: true,
      hasSubmitted: false
    });
    setTimeout(() => {
      this.setState({
        isSubmitting: false,
        hasSubmitted: true
      })
    }, 800);
  }


  render() {
    return (
      <>
        <div className='adminPageContent'>
          <b> <FormattedMessage id="language.select.admin" /></b>
          <Formik
            initialValues={{
              email: "",
              password: "",
              village: ""
            }}
            validate={this.validationHandler}
            onSubmit={this.submissionHandler}
          >
            {({
              values,
              errors,
              touched,
              handleChange,
              handleBlur,
              handleSubmit
            }) => (
              <Form
                onSubmit={handleSubmit}
                onChange={handleChange}
                onBlur={handleBlur}
              >
                <Stack gap={7}>
                  <TextInput
                    helperText="Optional helper text here; if message is more than one line text should wrap (~100 character count maximum)"
                    id="email"
                    name="email"
                    labelText="Text input label"
                    placeholder="Placeholder text"
                    value={values.email}
                    invalidText={errors.email}
                    invalid={Boolean(touched.email && errors.email)}
                    className="inputText"
                  //disabled={this.state.hasSubmitted}
                  />

                  <TextInput
                    id="password"
                    type="password"
                    name="password"
                    labelText="Password"
                    placeholder="Enter a password"
                    value={values.password}
                    invalidText={errors.password}
                    invalid={Boolean(touched.password && errors.password)}
                    className="inputText"
                  // disabled={this.state.hasSubmitted}
                  />
                  <Select
                    defaultValue={values.village}
                    id="select"
                    name="Vilage"
                    invalidText={errors.village}
                    labelText="Select"
                    className="inputText"
                  // disabled={this.state.hasSubmitted}
                  >
                    <SelectItem
                      text=""
                      value=""
                    />
                    <SelectItem
                      text="Luweero"
                      value="LWRO"
                    />
                    <SelectItem
                      text="Masaka"
                      value="MSK"
                    />
                  </Select>

                  {this.state.isSubmitting ? (
                    <InlineLoading
                      success={this.state.hasSubmitted.toString()}
                      icondescription="Active loading indicator"
                      description={
                        this.state.hasSubmitted ? "Submission successful" : "Submitting data..."
                      }
                    />
                  ) : (
                    <Button type="submit" disabled={this.state.isSubmitting}>
                      Submit
                    </Button>
                  )}
                </Stack>
              </Form>
            )}
          </Formik>
        </div>
      </>
    );

  }
}

export default injectIntl(ReflectTests)