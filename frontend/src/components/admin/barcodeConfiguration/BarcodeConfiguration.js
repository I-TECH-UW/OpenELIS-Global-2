import React, { useContext, useState, useEffect, useRef } from "react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import {
  Form,
  TextInput,
  Heading,
  Toggle,
  Button,
  Loading,
  Grid,
  Column,
  Section,
  Checkbox,
  TableCell,
  TableSelectRow,
  UnorderedList,
  ListItem,
} from "@carbon/react";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../utils/Utils.js";
import { NotificationContext } from "../../layout/Layout.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import config from "../../../config.json";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import { Formik } from "formik";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
];
function BarcodeConfiguration() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const componentMounted = useRef(false);
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(5);
  const [modifyButton, setModifyButton] = useState(true);
  const [selectedRowId, setSelectedRowId] = useState(null);
  const [startingRecNo, setStartingRecNo] = useState(1);
  const [formEntryConfigMenuList, setformEntryConfigMenuList] = useState([]);
  const [orderEntryConfigurationList, setOrderEntryConfigurationList] =
    useState([]);

  const [ConfigEdit, setConfigEdit] = useState(false);

  function handleModify(event) {
    event.preventDefault();
    setConfigEdit(true);
  }

  const handlePageChange = ({ page, pageSize }) => {
    setPage(page);
    setPageSize(pageSize);
  };

  const handleMenuItems = (res) => {
    if (res) {
      setformEntryConfigMenuList(res);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer(`/rest/BarcodeConfiguration`, handleMenuItems);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  useEffect(() => {
    if (formEntryConfigMenuList && formEntryConfigMenuList.menuList) {
      const newConfigList = formEntryConfigMenuList.menuList.map((item) => {
        let value = item.value;
        if (item.valueType === "text" && item.tag === "localization") {
          value =
            item.localization.localesAndValuesOfLocalesWithValues || value;
        }
        return {
          id: item.id,
          startingRecNo: startingRecNo,
          name: item.name,
          description: item.description,
          value: value,
          valueType: item.valueType,
        };
      });
      setOrderEntryConfigurationList(newConfigList);
    }
  }, [formEntryConfigMenuList]);

  const renderCell = (cell, row) => {
    if (cell.info.header === "select") {
      return (
        <TableSelectRow
          radio
          key={cell.id}
          id={cell.id}
          checked={selectedRowId === row.id}
          name="selectRowRadio"
          ariaLabel="selectRow"
          onSelect={() => {
            setModifyButton(false);
            setSelectedRowId(row.id);
          }}
        />
      );
    }
    return <TableCell key={cell.id}>{cell.value}</TableCell>;
  };

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <br />
        <Column lg={16} md={8} sm={4}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="barcodeconfiguration.browse.title" />
              </Heading>
            </Section>
          </Section>
        </Column>
        <br />
        <Grid fullWidth={true} className="gridBoundary">
          <Column lg={16} md={8} sm={4}>
            <Formik
            // initialValues={reportFormValues}
            // enableReinitialize={true}
            // // validationSchema={}
            // onSubmit
            // onChange
            >
              {(
                {
                  // values,
                  // errors,
                  // touched,
                  // setFieldValue,
                  // handleChange,
                  // handleBlur,
                  // handleSubmit,
                },
              ) => (
                <Form
                // onSubmit={handleSubmit}
                // onChange={handleChange}
                // onBlur={handleBlur}
                >
                  <Section>
                    <h4>
                      <FormattedMessage id="siteInfo.section.number" />
                    </h4>
                  </Section>
                  <hr />
                  <h5>
                    <FormattedMessage id="siteInfo.title.default.barcode" />
                  </h5>
                  <br />
                  <FormattedMessage id="siteInfo.description.default.barcode" />
                  <br />
                  <br />
                  <Grid fullWidth={true}>
                    <Column lg={8} md={8} sm={4}>
                      <TextInput
                        name="order"
                        className="defalut"
                        type="text"
                        // id={index + "order"}
                        labelText={
                          <FormattedMessage id="siteInfo.title.default.barcode.order" />
                        }
                        // value={rule.ruleName}
                        // onChange={(e) => handleRuleFieldChange(e, index)}
                      />
                    </Column>
                    <Column lg={8} md={8} sm={4}>
                      <TextInput
                        name="specimen"
                        className="defalut"
                        type="text"
                        // id={index + "order"}
                        labelText={
                          <FormattedMessage id="siteInfo.title.default.barcode.specimen" />
                        }
                        // value={rule.ruleName}
                        // onChange={(e) => handleRuleFieldChange(e, index)}
                      />
                    </Column>
                  </Grid>
                  <br />
                  <h5>
                    <FormattedMessage id="siteInfo.title.max.barcode" />
                  </h5>
                  <br />
                  <FormattedMessage id="siteInfo.description.max.barcode" />
                  <br />
                  <br />
                  <Grid fullWidth={true}>
                    <Column lg={8} md={8} sm={4}>
                      <TextInput
                        name="order"
                        className="defalut"
                        type="text"
                        // id={index + "order"}
                        labelText={
                          <FormattedMessage id="siteInfo.title.default.barcode.order" />
                        }
                        // value={rule.ruleName}
                        // onChange={(e) => handleRuleFieldChange(e, index)}
                      />
                    </Column>
                    <Column lg={8} md={8} sm={4}>
                      <TextInput
                        name="specimen"
                        className="defalut"
                        type="text"
                        // id={index + "order"}
                        labelText={
                          <FormattedMessage id="siteInfo.title.default.barcode.specimen" />
                        }
                        // value={rule.ruleName}
                        // onChange={(e) => handleRuleFieldChange(e, index)}
                      />
                    </Column>
                  </Grid>
                  <hr />
                  <Section>
                    <h4>
                      <FormattedMessage id="siteInfo.section.elements" />
                    </h4>
                    <br />
                    <FormattedMessage id="siteInfo.description.elements" />
                    <br />
                    <br />
                    <Grid fullWidth={true}>
                      <Column lg={16} md={8} sm={4}>
                        <h4>
                          <FormattedMessage id="siteInfo.elements.mandatory" />
                        </h4>
                        <br />
                        <Grid fullWidth={true}>
                          <Column lg={8} md={8} sm={4}>
                            <div>
                              <FormattedMessage id="siteInfo.title.default.barcode.order" />
                              <br />
                              <br />
                              <UnorderedList nested={true}>
                                <ListItem>
                                  <FormattedMessage id="barcode.label.info.labnumber" />
                                </ListItem>
                                <ListItem>
                                  <FormattedMessage id="barcode.label.info.patientdobfull" />
                                </ListItem>
                                <ListItem>
                                  <FormattedMessage id="barcode.label.info.patientid" />
                                </ListItem>
                                <ListItem>
                                  <FormattedMessage id="barcode.label.info.patientname" />
                                </ListItem>
                                <ListItem>
                                  <FormattedMessage id="datasubmission.siteid" />
                                </ListItem>
                              </UnorderedList>
                            </div>
                          </Column>
                          <Column lg={8} md={8} sm={4}>
                            <div>
                              <FormattedMessage id="siteInfo.title.default.barcode.specimen" />
                              <br />
                              <br />
                              <UnorderedList nested={true}>
                                <ListItem>
                                  <FormattedMessage id="barcode.label.info.labnumber" />
                                </ListItem>
                                <ListItem>
                                  <FormattedMessage id="barcode.label.info.patientdobfull" />
                                </ListItem>
                                <ListItem>
                                  <FormattedMessage id="barcode.label.info.patientid" />
                                </ListItem>
                                <ListItem>
                                  <FormattedMessage id="barcode.label.info.patientname" />
                                </ListItem>
                              </UnorderedList>
                            </div>
                          </Column>
                        </Grid>
                      </Column>
                    </Grid>
                    <br />
                    <Grid fullWidth={true}>
                      <Column lg={16} md={8} sm={4}>
                        <h4>
                          <FormattedMessage id="siteInfo.elements.optional" />
                        </h4>
                        <br />
                        <Grid fullWidth={true}>
                          <Column lg={8} md={8} sm={4}>
                            <div>
                              <FormattedMessage id="siteInfo.title.default.barcode.order" />
                              <br />
                              <br />
                              <FormattedMessage id="siteInfo.title.default.barcode.order.none" />
                            </div>
                          </Column>
                          <Column lg={8} md={8} sm={4}>
                            <div>
                              <FormattedMessage id="siteInfo.title.default.barcode.specimen" />
                              <br />
                              <Checkbox
                                id="checkBox0"
                                labelText={
                                  <FormattedMessage id="barcode.label.info.collectiondatetime" />
                                }
                              />
                              <Checkbox
                                id="checkBox1"
                                labelText={
                                  <FormattedMessage id="barcode.label.info.collectedBy" />
                                }
                              />
                              <Checkbox
                                id="checkBox2"
                                labelText={
                                  <FormattedMessage id="barcode.label.info.tests" />
                                }
                              />
                              <Checkbox
                                id="checkBox3"
                                labelText={
                                  <FormattedMessage id="barcode.label.info.patientsexfull" />
                                }
                              />
                            </div>
                          </Column>
                        </Grid>
                      </Column>
                    </Grid>
                  </Section>
                  <hr />
                  <Section>
                    <h4>
                      <FormattedMessage id="siteInfo.section.altAccession" />
                    </h4>
                    <br />
                    <Checkbox
                      id="checkBox"
                      labelText={<FormattedMessage id="labno.alt.prefix.use" />}
                    />
                    <br />
                    <Grid fullWidth={true}>
                      <Column lg={8} md={4} sm={4}>
                        <FormattedMessage id="labno.alt.prefix.instruction" />
                      </Column>
                      <Column lg={8} md={4} sm={4}>
                        <TextInput
                          name="prefix"
                          className="defalut"
                          type="text"
                          // id={index + "order"}
                          labelText=""
                          // value={rule.ruleName}
                          // onChange={(e) => handleRuleFieldChange(e, index)}
                        />
                      </Column>
                    </Grid>
                    <br />
                    <FormattedMessage id="labno.alt.prefix.note" />
                    <br />
                  </Section>
                  <hr />
                  <Section>
                    <h4>
                      <FormattedMessage id="siteInfo.section.size" />
                    </h4>
                    <br />
                    <FormattedMessage id="siteInfo.description.dimensions" />
                    <br />
                    <br />
                    <Grid fullWidth={true}>
                      <Column lg={8} md={4} sm={2}>
                        <FormattedMessage id="siteInfo.title.default.barcode.order" />
                        <br />
                        <br />
                        <TextInput
                          name="prefix"
                          className="defalut"
                          type="text"
                          // id={index + "order"}
                          labelText={
                            <FormattedMessage id="siteInfo.title.default.barcode.height" />
                          }
                          helperText="Enter values in: mm"
                          // value={rule.ruleName}
                          // onChange={(e) => handleRuleFieldChange(e, index)}
                        />
                        <br />
                        <TextInput
                          name="prefix"
                          className="defalut"
                          type="text"
                          // id={index + "order"}
                          labelText={
                            <FormattedMessage id="siteInfo.title.default.barcode.width" />
                          }
                          helperText="Enter values in: mm"
                          // value={rule.ruleName}
                          // onChange={(e) => handleRuleFieldChange(e, index)}
                        />
                      </Column>
                      <Column lg={8} md={4} sm={2}>
                        <FormattedMessage id="siteInfo.title.default.barcode.specimen" />
                        <br />
                        <br />
                        <TextInput
                          name="prefix"
                          className="defalut"
                          type="text"
                          // id={index + "order"}
                          labelText={
                            <FormattedMessage id="siteInfo.title.default.barcode.height" />
                          }
                          helperText="Enter values in: mm"
                          // value={rule.ruleName}
                          // onChange={(e) => handleRuleFieldChange(e, index)}
                        />
                        <br />
                        <TextInput
                          name="prefix"
                          className="defalut"
                          type="text"
                          // id={index + "order"}
                          labelText={
                            <FormattedMessage id="siteInfo.title.default.barcode.width" />
                          }
                          helperText="Enter values in: mm"
                          // value={rule.ruleName}
                          // onChange={(e) => handleRuleFieldChange(e, index)}
                        />
                      </Column>
                    </Grid>
                    <br />
                    <Grid fullWidth={true}>
                      <Column lg={8} md={4} sm={2}>
                        <FormattedMessage id="siteInfo.title.default.barcode.block" />
                        <br />
                        <br />
                        <TextInput
                          name="prefix"
                          className="defalut"
                          type="text"
                          // id={index + "order"}
                          labelText={
                            <FormattedMessage id="siteInfo.title.default.barcode.height" />
                          }
                          helperText="Enter values in: mm"
                          // value={rule.ruleName}
                          // onChange={(e) => handleRuleFieldChange(e, index)}
                        />
                        <br />
                        <TextInput
                          name="prefix"
                          className="defalut"
                          type="text"
                          // id={index + "order"}
                          labelText={
                            <FormattedMessage id="siteInfo.title.default.barcode.width" />
                          }
                          helperText="Enter values in: mm"
                          // value={rule.ruleName}
                          // onChange={(e) => handleRuleFieldChange(e, index)}
                        />
                      </Column>
                      <Column lg={8} md={4} sm={2}>
                        <FormattedMessage id="siteInfo.title.default.barcode.slide" />
                        <br />
                        <br />
                        <TextInput
                          name="prefix"
                          className="defalut"
                          type="text"
                          // id={index + "order"}
                          labelText={
                            <FormattedMessage id="siteInfo.title.default.barcode.height" />
                          }
                          helperText="Enter values in: mm"
                          // value={rule.ruleName}
                          // onChange={(e) => handleRuleFieldChange(e, index)}
                        />
                        <br />
                        <TextInput
                          name="prefix"
                          className="defalut"
                          type="text"
                          // id={index + "order"}
                          labelText={
                            <FormattedMessage id="siteInfo.title.default.barcode.width" />
                          }
                          helperText="Enter values in: mm"
                          // value={rule.ruleName}
                          // onChange={(e) => handleRuleFieldChange(e, index)}
                        />
                      </Column>
                    </Grid>
                  </Section>
                  <hr />
                </Form>
              )}
            </Formik>
          </Column>
        </Grid>
        <Section>
          <Form onSubmit={handleModify}>
            <Column lg={16} md={8} sm={4}>
              <Button disabled={modifyButton} type="submit">
                <FormattedMessage id="label.button.save" />
              </Button>{" "}
              <Button
                onClick={() => window.location.reload()}
                kind="tertiary"
                type="submit"
              >
                <FormattedMessage id="label.button.cancel" />
              </Button>
            </Column>
          </Form>
        </Section>
      </div>
    </>
  );
}

export default injectIntl(BarcodeConfiguration);
