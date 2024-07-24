import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Heading,
  Button,
  Loading,
  Grid,
  Column,
  Section,
  DataTable,
  Table,
  TableHead,
  TableRow,
  TableBody,
  TableHeader,
  TableCell,
  TableSelectRow,
  TableSelectAll,
  TableContainer,
  Pagination,
  Search,
  Modal,
  TextInput,
  Dropdown,
  TextArea,
  Checkbox,
} from "@carbon/react";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../utils/Utils.js";
import {
  ConfigurationContext,
  NotificationContext,
} from "../../layout/Layout.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import { ArrowLeft, ArrowRight, Cost } from "@carbon/icons-react";
import ActionPaginationButtonType from "../../common/ActionPaginationButtonType.js";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
  {
    label: "testnotificationconfig.browse.title",
    link: "/MasterListsPage#testNotificationConfigMenu",
  },
];

function TestNotificationConfigEdit() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const componentMounted = useRef(false);
  const [indMsg, setIndMsg] = useState("0");

  return (
    <>
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <Grid fullWidth={true}>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <Section>
                <Heading>
                  <FormattedMessage id="testnotificationconfig.browse.title" />
                </Heading>
              </Section>
            </Section>
          </Column>
        </Grid>
        <div className="orderLegendBody">
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Section>
                  <Heading>
                    <FormattedMessage id="props.name" />
                  </Heading>
                </Section>
              </Section>
            </Column>
          </Grid>
          <br />
          <Grid fullWidth={true}>
            <Column lg={4} md={2} sm={2}>
              <Checkbox
              // key={section.elementID}
              // id={section.elementID}
              // value={section.roleId}
              // labelText={section.roleName}
              // checked={selectedGlobalLabUnitRoles.includes(section.roleId)}
              // onChange={() => {
              //   handleCheckboxChange(section.roleId);
              // }}
              />
            </Column>
            <Column lg={4} md={2} sm={2}>
              <Checkbox />
            </Column>
            <Column lg={4} md={2} sm={2}>
              <Checkbox />
            </Column>
            <Column lg={4} md={2} sm={2}>
              <Checkbox />
            </Column>
          </Grid>
          <br />
          <hr />
          <br />
          <Grid fullWidth={true} className="gridBoundary">
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Section>
                  <Heading>
                    <FormattedMessage id="testnotification.instructions.header" />
                  </Heading>
                </Section>
              </Section>
              <br />
              <FormattedMessage id="testnotification.instructions.body" />
              <br />
              <br />
              <FormattedMessage id="testnotification.instructions.body.0" />
              <br />
              <br />
              <FormattedMessage id="testnotification.instructions.body.1" />
              <br />
              <br />
              <FormattedMessage id="testnotification.instructions.body.2" />
              <br />
              <br />
              <FormattedMessage id="testnotification.instructions.body.3" />
              <br />
              <br />
              <Section>
                <Section>
                  <Heading>
                    <FormattedMessage id="testnotification.instructionis.variables.header" />
                  </Heading>
                </Section>
              </Section>
              <br />
              <FormattedMessage id="testnotification.instructionis.variables.body" />
              <br />
              <br />
              <FormattedMessage id="testnotification.instructionis.variables.body.0" />
              <br />
              <br />
              <FormattedMessage id="testnotification.instructionis.variables.body.1" />
              <br />
              <br />
              <FormattedMessage id="testnotification.instructionis.variables.body.2" />
              <br />
              <br />
            </Column>
          </Grid>
          <br />
          <hr />
          <br />
          <div>
            <Grid fullWidth={true}>
              <Column lg={14} md={6} sm={2}>
                <Section>
                  <Section>
                    <Section>
                      <Heading>
                        <FormattedMessage id="testnotification.systemdefault.template" />
                      </Heading>
                    </Section>
                  </Section>
                </Section>
              </Column>
              <Column lg={2} md={2} sm={2}>
                <Button
                  onClick={() => {
                    console.log("Edit");
                  }}
                >
                  Edit
                </Button>
              </Column>
            </Grid>
            <br />
            <Grid fullWidth={true}>
              <Column lg={8} md={4} sm={2}>
                <FormattedMessage id="testnotification.subjecttemplate" />
              </Column>
              <Column lg={8} md={4} sm={2}>
                <TextInput
                  id="subject"
                  type="text"
                  labelText=""
                  placeholder={intl.formatMessage({
                    id: "systemDefaultPayloadTemplate.subjectTemplate",
                  })}
                  // invalid={
                  //   userDataShow &&
                  //   userDataShow.userLoginName &&
                  //   !loginNameRegex.test(userDataShow.userLoginName)
                  // }
                  // // invalidText={errors.order}
                  // required={true}
                  // value={
                  //   userDataShow && userDataShow.userLoginName
                  //     ? userDataShow.userLoginName
                  //     : ""
                  // }
                  // onChange={(e) => handleUserLoginNameChange(e)}
                />
              </Column>
            </Grid>
            <br />
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <FormattedMessage id="testnotification.messagetemplate" />
              </Column>
            </Grid>
            <br />
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <TextArea
                  id="message"
                  type="text"
                  labelText=""
                  placeholder={intl.formatMessage({
                    id: "systemDefaultPayloadTemplate.messageTemplate",
                  })}
                  // invalid={
                  //   userDataShow &&
                  //   userDataShow.userLoginName &&
                  //   !loginNameRegex.test(userDataShow.userLoginName)
                  // }
                  // // invalidText={errors.order}
                  // required={true}
                  // value={
                  //   userDataShow && userDataShow.userLoginName
                  //     ? userDataShow.userLoginName
                  //     : ""
                  // }
                  // onChange={(e) => handleUserLoginNameChange(e)}
                />
              </Column>
            </Grid>
          </div>
          <br />
          <hr />
          <br />
          <div>
            <Grid fullWidth={true}>
              <Column lg={14} md={6} sm={2}>
                <Section>
                  <Section>
                    <Section>
                      <Heading>
                        <FormattedMessage id="testnotification.testdefault.template" />
                      </Heading>
                    </Section>
                  </Section>
                </Section>
              </Column>
            </Grid>
            <br />
            <Grid fullWidth={true}>
              <Column lg={8} md={4} sm={2}>
                <FormattedMessage id="testnotification.subjecttemplate" />
              </Column>
              <Column lg={8} md={4} sm={2}>
                <TextInput
                  id="subject"
                  type="text"
                  labelText=""
                  // invalid={
                  //   userDataShow &&
                  //   userDataShow.userLoginName &&
                  //   !loginNameRegex.test(userDataShow.userLoginName)
                  // }
                  // // invalidText={errors.order}
                  // required={true}
                  // value={
                  //   userDataShow && userDataShow.userLoginName
                  //     ? userDataShow.userLoginName
                  //     : ""
                  // }
                  // onChange={(e) => handleUserLoginNameChange(e)}
                />
              </Column>
            </Grid>
            <br />
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <FormattedMessage id="testnotification.messagetemplate" />
              </Column>
            </Grid>
            <br />
            <Grid fullWidth={true}>
              <Column lg={16} md={8} sm={4}>
                <TextArea
                  id="message"
                  type="text"
                  labelText=""
                  // invalid={
                  //   userDataShow &&
                  //   userDataShow.userLoginName &&
                  //   !loginNameRegex.test(userDataShow.userLoginName)
                  // }
                  // // invalidText={errors.order}
                  // required={true}
                  // value={
                  //   userDataShow && userDataShow.userLoginName
                  //     ? userDataShow.userLoginName
                  //     : ""
                  // }
                  // onChange={(e) => handleUserLoginNameChange(e)}
                />
              </Column>
            </Grid>
          </div>
          <br />
          <hr />
          <br />
          <div>
            <Grid fullWidth={true}>
              <Column lg={14} md={6} sm={2}>
                <Section>
                  <Section>
                    <Section>
                      <Heading>
                        <FormattedMessage id="testnotification.options" />
                      </Heading>
                    </Section>
                  </Section>
                </Section>
              </Column>
            </Grid>
            <br />
            <Grid fullWidth={true}>
              <Column lg={4} md={4} sm={2}>
                <Button
                  onClick={() => {
                    setIndMsg("0");
                  }}
                  kind="tertiary"
                >
                  <FormattedMessage id="testnotification.patient.email" />
                </Button>
              </Column>{" "}
              <Column lg={4} md={4} sm={2}>
                <Button
                  onClick={() => {
                    setIndMsg("1");
                  }}
                  kind="tertiary"
                >
                  <FormattedMessage id="testnotification.patient.sms" />
                </Button>
              </Column>{" "}
              <Column lg={4} md={4} sm={2}>
                <Button
                  onClick={() => {
                    setIndMsg("2");
                  }}
                  kind="tertiary"
                >
                  <FormattedMessage id="testnotification.provider.email" />
                </Button>
              </Column>{" "}
              <Column lg={4} md={4} sm={2}>
                <Button
                  onClick={() => {
                    setIndMsg("3");
                  }}
                  kind="tertiary"
                >
                  <FormattedMessage id="testnotification.provider.sms" />
                </Button>
              </Column>
            </Grid>
            <br />
            <hr />
            <br />
            {indMsg === "0" || indMsg === "2" ? (
              <>
                <Grid fullWidth={true}>
                  <Column lg={16} md={8} sm={4}>
                    <Section>
                      <Section>
                        <Section>
                          <Heading>
                            {indMsg === "0" ? (
                              <>
                                <FormattedMessage id="testnotification.provider.email" />
                              </>
                            ) : (
                              <>
                                <FormattedMessage id="testnotification.patient.email" />
                              </>
                            )}
                          </Heading>
                        </Section>
                      </Section>
                    </Section>
                  </Column>
                </Grid>
                <br />
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={2}>
                    <FormattedMessage id="testnotification.bcc" />
                  </Column>
                  <Column lg={8} md={4} sm={2}>
                    <TextInput
                      id="subject"
                      type="text"
                      labelText=""
                      // invalid={
                      //   userDataShow &&
                      //   userDataShow.userLoginName &&
                      //   !loginNameRegex.test(userDataShow.userLoginName)
                      // }
                      // // invalidText={errors.order}
                      // required={true}
                      // value={
                      //   userDataShow && userDataShow.userLoginName
                      //     ? userDataShow.userLoginName
                      //     : ""
                      // }
                      // onChange={(e) => handleUserLoginNameChange(e)}
                    />
                  </Column>
                </Grid>
                <br />
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={2}>
                    <FormattedMessage id="testnotification.subjecttemplate" />
                  </Column>
                  <Column lg={8} md={4} sm={2}>
                    <TextInput
                      id="subject"
                      type="text"
                      labelText=""
                      // invalid={
                      //   userDataShow &&
                      //   userDataShow.userLoginName &&
                      //   !loginNameRegex.test(userDataShow.userLoginName)
                      // }
                      // // invalidText={errors.order}
                      // required={true}
                      // value={
                      //   userDataShow && userDataShow.userLoginName
                      //     ? userDataShow.userLoginName
                      //     : ""
                      // }
                      // onChange={(e) => handleUserLoginNameChange(e)}
                    />
                  </Column>
                </Grid>
                <br />
                <Grid fullWidth={true}>
                  <Column lg={16} md={8} sm={4}>
                    <FormattedMessage id="testnotification.messagetemplate" />
                  </Column>
                </Grid>
                <br />
                <Grid fullWidth={true}>
                  <Column lg={16} md={8} sm={4}>
                    <TextArea
                      id="message"
                      type="text"
                      labelText=""
                      // invalid={
                      //   userDataShow &&
                      //   userDataShow.userLoginName &&
                      //   !loginNameRegex.test(userDataShow.userLoginName)
                      // }
                      // // invalidText={errors.order}
                      // required={true}
                      // value={
                      //   userDataShow && userDataShow.userLoginName
                      //     ? userDataShow.userLoginName
                      //     : ""
                      // }
                      // onChange={(e) => handleUserLoginNameChange(e)}
                    />
                  </Column>
                </Grid>
              </>
            ) : (
              <>
                <Grid fullWidth={true}>
                  <Column lg={16} md={8} sm={4}>
                    <Section>
                      <Section>
                        <Section>
                          <Heading>
                            {indMsg === "1" ? (
                              <>
                                <FormattedMessage id="testnotification.provider.sms" />
                              </>
                            ) : (
                              <>
                                <FormattedMessage id="testnotification.patient.sms" />
                              </>
                            )}
                          </Heading>
                        </Section>
                      </Section>
                    </Section>
                  </Column>
                </Grid>
                <br />
                <Grid fullWidth={true}>
                  <Column lg={16} md={8} sm={4}>
                    <FormattedMessage id="testnotification.messagetemplate" />
                  </Column>
                </Grid>
                <br />
                <Grid fullWidth={true}>
                  <Column lg={16} md={8} sm={4}>
                    <TextArea
                      id="message"
                      type="text"
                      labelText=""
                      // invalid={
                      //   userDataShow &&
                      //   userDataShow.userLoginName &&
                      //   !loginNameRegex.test(userDataShow.userLoginName)
                      // }
                      // // invalidText={errors.order}
                      // required={true}
                      // value={
                      //   userDataShow && userDataShow.userLoginName
                      //     ? userDataShow.userLoginName
                      //     : ""
                      // }
                      // onChange={(e) => handleUserLoginNameChange(e)}
                    />
                  </Column>
                </Grid>
              </>
            )}
          </div>
        </div>
      </div>
    </>
  );
}

export default injectIntl(TestNotificationConfigEdit);
