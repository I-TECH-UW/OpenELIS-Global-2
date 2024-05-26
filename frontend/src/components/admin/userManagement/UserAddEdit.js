import React from "react";
import {
  Grid,
  Column,
  Section,
  Heading,
  Form,
  TextInput,
  UnorderedList,
  ListItem,
  RadioButton,
  RadioButtonGroup,
  Button,
  Select,
  SelectItem,
} from "@carbon/react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import CustomCheckBox from "../../common/CustomCheckBox";

const breadcrumbs = [
  { label: "home.label", link: "/" },
  // { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
];

function UserAddEdit() {
  const intl = useIntl();

  return (
    <>
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <Grid>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="unifiedSystemUser.add.user" />
                </Heading>
              </Section>
            </Section>
          </Column>
        </Grid>
        <div className="orderLegendBody">
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Form
              // onSubmit={handleSubmit}
              // onChange={setSaveButton(false)}
              // onBlur={handleBlur}
              >
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="login.login.name" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    {/* {orgInfo && ( */}
                    <TextInput
                      id="login-name"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.login.name",
                      })}
                      required
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      // value={orgInfo.organizationName}
                      onChange={(e) => handleOrgNameChange(e)}
                    />
                    {/* )} */}
                  </Column>
                </Grid>
                <br />
                <Grid fullWidth={true}>
                  <Column lg={16} md={8} sm={4}>
                    <h5>
                      <FormattedMessage id="login.complexity.message" />
                    </h5>
                    <br />

                    <h6>
                      <UnorderedList nested={true}>
                        <ListItem>
                          <FormattedMessage id="login.complexity.message.1" />
                        </ListItem>
                        <ListItem>
                          <FormattedMessage id="login.complexity.message.2" />
                        </ListItem>
                        <ListItem>
                          <FormattedMessage id="login.complexity.message.3" />
                        </ListItem>
                        <ListItem>
                          <FormattedMessage id="login.complexity.message.4" />
                        </ListItem>
                      </UnorderedList>
                    </h6>
                  </Column>
                </Grid>
                <br />
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="login.login.password" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    {/* {orgInfo && ( */}
                    <TextInput
                      id="login-password"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.login.password",
                      })}
                      required
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      // value={orgInfo.organizationName}
                      onChange={(e) => handleOrgNameChange(e)}
                    />
                    {/* )} */}
                  </Column>
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="login.login.repeat.password" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    {/* {orgInfo && ( */}
                    <TextInput
                      id="login-repeat-password"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.login.repeat.password",
                      })}
                      required
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      // value={orgInfo.organizationName}
                      onChange={(e) => handleOrgNameChange(e)}
                    />
                    {/* )} */}
                  </Column>
                </Grid>
                <br />
                <br />
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="login.login.first" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    {/* {orgInfo && ( */}
                    <TextInput
                      id="first-name"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.login.first",
                      })}
                      required
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      // value={orgInfo.organizationName}
                      onChange={(e) => handleOrgNameChange(e)}
                    />
                    {/* )} */}
                  </Column>
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="login.login.last" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    {/* {orgInfo && ( */}
                    <TextInput
                      id="last-name"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.login.last",
                      })}
                      required
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      // value={orgInfo.organizationName}
                      onChange={(e) => handleOrgNameChange(e)}
                    />
                    {/* )} */}
                  </Column>
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="login.password.expired.date" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    {/* {orgInfo && ( */}
                    <TextInput
                      id="password-expire-date"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.password.expired.date.placeholder",
                      })}
                      required
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      // value={orgInfo.organizationName}
                      onChange={(e) => handleOrgNameChange(e)}
                    />
                    {/* )} */}
                  </Column>
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="login.timeout" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    {/* {orgInfo && ( */}
                    <TextInput
                      id="login-timeout"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.timeout.placeholder",
                      })}
                      required
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      // value={orgInfo.organizationName}
                      onChange={(e) => handleOrgNameChange(e)}
                    />
                    {/* )} */}
                  </Column>
                </Grid>
                <br />
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="login.account.locked" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    <RadioButtonGroup name="account-locked">
                      <RadioButton labelText="Y" value="radio-1" id="radio-1" />
                      <RadioButton labelText="N" value="radio-2" id="radio-2" />
                    </RadioButtonGroup>
                  </Column>
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="login.account.disabled" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    <RadioButtonGroup name="account-disabled">
                      <RadioButton labelText="Y" value="radio-3" id="radio-3" />
                      <RadioButton labelText="N" value="radio-4" id="radio-4" />
                    </RadioButtonGroup>
                  </Column>
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <>
                      <FormattedMessage id="systemuser.isActive" />
                      <span className="requiredlabel">*</span> :
                    </>
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    <RadioButtonGroup name="account-isActive">
                      <RadioButton labelText="Y" value="radio-5" id="radio-5" />
                      <RadioButton labelText="N" value="radio-6" id="radio-6" />
                    </RadioButtonGroup>
                  </Column>
                </Grid>
              </Form>
              <br />
              <hr />
              <br />
              <Grid fullWidth={true} className="gridBoundary">
                <Column lg={16} md={8} sm={4}>
                  <Section>
                    <Section>
                      <Heading>
                        <FormattedMessage id="systemuser.role" />
                      </Heading>
                    </Section>
                  </Section>
                </Column>
                <Column lg={16} md={8} sm={4}>
                  <br />
                  <FormattedMessage id="systemuserrole.instruction.1" />
                  <br />
                  <br />
                  <FormattedMessage id="systemuserrole.instruction.2" />
                  <br />
                  <br />
                  <FormattedMessage id="systemuserrole.instruction.3" />
                  <br />
                  <br />
                  <FormattedMessage id="systemuserrole.instruction.4" />
                  <br />
                  <br />
                  <FormattedMessage id="systemuserrole.instruction.5" />
                  <br />
                  <br />
                </Column>
              </Grid>
              <br />
              <hr />
              <br />
              <Grid fullWidth={true}>
                <Column lg={8} md={4} sm={4}>
                  <>
                    <FormattedMessage id="systemuserrole.copypermisions" />
                    <span className="requiredlabel">*</span> :
                  </>
                </Column>
                <Column lg={8} md={4} sm={4}>
                  {/* {orgInfo && ( */}
                  <TextInput
                    id="copy-permissions"
                    className="defalut"
                    type="text"
                    labelText=""
                    placeholder={intl.formatMessage({
                      id: "systemuserrole.copypermisions.placeholder",
                    })}
                    required
                    // invalid={errors.order && touched.order}
                    // invalidText={errors.order}
                    // value={orgInfo.organizationName}
                    onChange={(e) => handleOrgNameChange(e)}
                  />
                  {/* )} */}
                </Column>
                <br />
                <Button type="button">
                  <FormattedMessage id="systemuserrole.apply" />
                </Button>
              </Grid>
              <hr />
              <br />
              <Grid fullWidth={true}>
                <Column lg={8} md={4} sm={4}>
                  <FormattedMessage id="systemuserrole.roles.global" />
                  <br />
                  <CustomCheckBox
                    id="global-administrator"
                    label={"Global Administrator"}
                  />
                  <br />
                </Column>
                <Column lg={8} md={4} sm={4}>
                  <FormattedMessage id="systemuserrole.roles.labunit" />
                  <Select
                    id={`select-3`}
                    noLabel={true}
                    defaultValue="option-3"
                  >
                    <SelectItem value="option-1" text="Option 1" />
                    <SelectItem value="option-2" text="Option 2" />
                    <SelectItem value="option-3" text="Option 3" />
                  </Select>
                  {/* selectables */}
                </Column>
              </Grid>
              <Grid fullWidth={true}>
                <Column lg={16} md={8} sm={4}>
                  <Button>
                    <FormattedMessage id="label.button.save" />
                  </Button>{" "}
                  <Button kind="tertiary">
                    <FormattedMessage id="label.button.exit" />
                  </Button>
                </Column>
              </Grid>
            </Column>
          </Grid>
        </div>
      </div>
    </>
  );
}

export default injectIntl(UserAddEdit);

// name as props [ add / edit user ]
// conditonal rendering
