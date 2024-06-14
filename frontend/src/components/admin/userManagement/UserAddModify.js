import React, { useContext, useState, useEffect, useRef } from "react";
import { useLocation } from "react-router-dom";
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
  Loading,
  Select,
  SelectItem,
  PasswordInput,
  CheckboxGroup,
  Checkbox,
  FormGroup,
} from "@carbon/react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import { NotificationContext } from "../../layout/Layout.js";
import {
  getFromOpenElisServer,
  postToOpenElisServer,
  postToOpenElisServerJsonResponse,
} from "../../utils/Utils.js";

const breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
];

function UserAddEdit() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const componentMounted = useRef(false);
  const intl = useIntl();

  const [saveButton, setSaveButton] = useState(true);
  const [isLoading, setIsLoading] = useState(true);
  const [isLocked, setIsLocked] = useState("radio-2");
  const [isDisabled, setIsDisabled] = useState("radio-4");
  const [isActive, setIsActive] = useState("radio-6");
  const [copyUserPermission, setCopyUserPermission] = useState("0");
  const [copyUserPermissionList, setCopyUserPermissionList] = useState(null);
  const [testSectionsSelect, setTestSectionsSelect] = useState("option-1");
  const [userData, setUserData] = useState(null);
  const [userDataShow, setUserDataShow] = useState({});
  const [userDataPost, setUserDataPost] = useState(null);
  const [selectedGlobalLabUnitRoles, setSelectedGlobalLabUnitRoles] = useState(
    [],
  );

  const id = new URLSearchParams(useLocation().search).get("ID");

  useEffect(() => {
    componentMounted.current = true;
    setIsLoading(true);
    if (id) {
      getFromOpenElisServer(
        `/rest/UnifiedSystemUser?ID=${id}&startingRecNo=1&roleFilter=`,
        handleUserData,
      );
    } else {
      setTimeout(() => {
        window.location.assign("/MasterListsPage#userManagement");
      }, 1000);
    }
    return () => {
      componentMounted.current = false;
      setIsLoading(false);
    };
  }, [id]);

  const handleUserData = (res) => {
    if (!res) {
      setIsLoading(true);
    } else {
      setUserData(res);
    }
  };

  useEffect(() => {
    componentMounted.current = true;
    setIsLoading(true);
    getFromOpenElisServer(`/rest/rest/users`, handleCopyUserPermissionsList);
    return () => {
      componentMounted.current = false;
      setIsLoading(false);
    };
  }, []);

  const handleCopyUserPermissionsList = (res) => {
    if (!res) {
      setIsLoading(true);
    } else {
      setCopyUserPermissionList(res);
    }
  };

  useEffect(() => {
    if (userData) {
      const userManagementInfoToShow = {
        accountActive: userData.accountActive,
        accountDisabled: userData.accountDisabled,
        accountLocked: userData.accountLocked,
        allowCopyUserRoles: userData.allowCopyUserRoles,
        cancelAction: userData.cancelAction,
        cancelMethod: userData.cancelMethod,
        confirmPassword: userData.confirmPassword,
        expirationDate: userData.expirationDate,
        formAction: userData.formAction,
        formMethod: userData.formMethod,
        formName: userData.formName,
        loginUserId: userData.loginUserId,
        selectedRoles: userData.selectedRoles,
        systemUserId: userData.systemUserId,
        systemUserIdToCopy: userData.systemUserIdToCopy,
        systemUserLastupdated: userData.systemUserLastupdated,
        timeout: userData.timeout,
        userFirstName: userData.userFirstName,
        userLastName: userData.userLastName,
        userLoginName: userData.userLoginName,
        userPassword: userData.userPassword,
      };

      const userManagementInfoToPost = {
        accountActive: userData.accountActive,
        accountDisabled: userData.accountDisabled,
        accountLocked: userData.accountLocked,
        allowCopyUserRoles: userData.allowCopyUserRoles,
        cancelAction: userData.cancelAction,
        cancelMethod: userData.cancelMethod,
        confirmPassword: userData.confirmPassword,
        expirationDate: userData.expirationDate,
        formAction: userData.formAction,
        formMethod: userData.formMethod,
        formName: userData.formName,
        globalRoles: userData.globalRoles,
        labUnitRoles: userData.labUnitRoles,
        loginUserId: userData.loginUserId,
        selectedRoles: userData.selectedRoles,
        systemUserId: userData.systemUserId,
        systemUserIdToCopy: userData.systemUserIdToCopy,
        systemUserLastupdated: userData.systemUserLastupdated,
        systemUsers: userData.systemUsers,
        testSections: userData.testSections,
        timeout: userData.timeout,
        userFirstName: userData.userFirstName,
        userLastName: userData.userLastName,
        userLoginName: userData.userLoginName,
        userPassword: userData.userPassword,
      };
      setUserDataShow(userManagementInfoToShow);
      setUserDataPost(userManagementInfoToPost);

      const globalRoles = userData.globalRoles.map((item) => {
        return {
          childrenID: item.childrenID,
          elementID: item.elementID,
          groupingRole: item.groupingRole,
          nestingLevel: item.nestingLevel,
          parentRole: item.parentRole,
          roleId: item.roleId,
          roleName: item.roleName,
        };
      });

      const labUnitRoles = userData.labUnitRoles.map((item) => {
        return {
          childrenID: item.childrenID,
          elementID: item.elementID,
          groupingRole: item.groupingRole,
          nestingLevel: item.nestingLevel,
          parentRole: item.parentRole,
          roleId: item.roleId,
          roleName: item.roleName,
        };
      });

      const testSections = userData.testSections.map((item) => {
        return {
          id: item.id,
          value: item.value,
        };
      });

      if (id !== "0") {
        const selectedGlobalLabUniRoles = userData.selectedRoles.map(
          (item) => item,
        );
        setSelectedGlobalLabUnitRoles(selectedGlobalLabUniRoles);
      } else {
        setSelectedGlobalLabUnitRoles([]);
      }

      setUserDataShow((userDataShow) => ({
        ...userDataShow,
        globalRoles,
        labUnitRoles,
        testSections,
      }));
    }
  }, [userData]);

  useEffect(() => {
    if (userDataShow) {
      setIsLocked(userDataShow.accountLocked === "Y" ? "radio-1" : "radio-2");
    }
  }, [userDataShow]);

  useEffect(() => {
    if (userDataShow) {
      setIsDisabled(
        userDataShow.accountDisabled === "Y" ? "radio-3" : "radio-4",
      );
    }
  }, [userDataShow]);

  useEffect(() => {
    if (userDataShow) {
      setIsActive(userDataShow.accountActive === "Y" ? "radio-5" : "radio-6");
    }
  }, [userDataShow]);

  function userSavePostCall(event) {
    event.preventDefault();
    setIsLoading(true);
    postToOpenElisServerJsonResponse(
      `/rest/UnifiedSystemUser`,
      JSON.stringify(userData),
      userSavePostCallback(),
    );
  }

  function userSavePostCallback() {
    setIsLoading(false);
    setNotificationVisible(true);
    addNotification({
      title: intl.formatMessage({
        id: "notification.title",
      }),
      message: intl.formatMessage({
        id: "notification.organization.post.save.success",
      }),
      kind: NotificationKinds.success,
    });
    // setTimeout(() => {
    //   window.location.reload();
    // }, 2000);
  }

  function handleUserLoginNameChange(e) {
    setSaveButton(false);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      userLoginName: e.target.value,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      userLoginName: e.target.value,
    }));
  }

  function handleUserPasswordChange(e) {
    setSaveButton(false);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      userPassword: e.target.value,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      userPassword: e.target.value,
    }));
  }

  function handleConfirmPasswordChange(e) {
    setSaveButton(false);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      confirmPassword: e.target.value,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      confirmPassword: e.target.value,
    }));
  }

  function handleUserFirstNameChange(e) {
    setSaveButton(false);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      userFirstName: e.target.value,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      userFirstName: e.target.value,
    }));
  }

  function handleUserLastNameChange(e) {
    setSaveButton(false);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      userLastName: e.target.value,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      userLastName: e.target.value,
    }));
  }

  function handleExpirationDateChange(e) {
    setSaveButton(false);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      expirationDate: e.target.value,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      expirationDate: e.target.value,
    }));
  }

  function handleTimeoutChange(e) {
    setSaveButton(false);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      timeout: e.target.value,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      timeout: e.target.value,
    }));
  }

  function handleCopyUserPermissionsChange(e) {
    setSaveButton(false);
    setCopyUserPermission(e.target.value);
  }

  // need to make seperate copyUserPerChg function to manuplated data as expected

  function handleTestSectionsSelectChange(e) {
    setTestSectionsSelect(e.target.value);
    setSaveButton(false);
  }

  function handleCheckboxChange(roleId) {
    let updatedRoles;
    if (selectedGlobalLabUnitRoles.includes(roleId)) {
      updatedRoles = selectedGlobalLabUnitRoles.filter((id) => id !== roleId);
    } else {
      updatedRoles = [...selectedGlobalLabUnitRoles, roleId];
    }

    setSelectedGlobalLabUnitRoles(updatedRoles);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      selectedRoles: updatedRoles,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      selectedRoles: updatedRoles,
    }));
  }

  if (!isLoading) {
    return (
      <>
        <Loading />
      </>
    );
  }

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <Grid>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <Section>
                <Heading>
                  {id === "0" ? (
                    <FormattedMessage id="unifiedSystemUser.add.user" />
                  ) : (
                    <FormattedMessage id="unifiedSystemUser.edit.user" />
                  )}
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
                    <TextInput
                      id="login-name"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.login.name",
                      })}
                      required={true}
                      value={
                        userData && userData.userLoginName
                          ? userData.userLoginName
                          : ""
                      }
                      onChange={(e) => handleUserLoginNameChange(e)}
                    />
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
                    <PasswordInput
                      id="login-password"
                      className="defalut"
                      type="password"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.login.password",
                      })}
                      required={true}
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      value={
                        userData && userData.userPassword
                          ? userData.userPassword
                          : ""
                      }
                      onChange={(e) => handleUserPasswordChange(e)}
                    />
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
                    <PasswordInput
                      id="login-repeat-password"
                      className="defalut"
                      type="password"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.login.repeat.password",
                      })}
                      required={true}
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      value={
                        userData && userData.confirmPassword
                          ? userData.confirmPassword
                          : ""
                      }
                      onChange={(e) => handleConfirmPasswordChange(e)}
                    />
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
                    <TextInput
                      id="first-name"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.login.first",
                      })}
                      required={true}
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      value={
                        userData && userData.userFirstName
                          ? userData.userFirstName
                          : ""
                      }
                      onChange={(e) => handleUserFirstNameChange(e)}
                    />
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
                    <TextInput
                      id="last-name"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.login.last",
                      })}
                      required={true}
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      value={
                        userData && userData.userLastName
                          ? userData.userLastName
                          : ""
                      }
                      onChange={(e) => handleUserLastNameChange(e)}
                    />
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
                    <TextInput
                      id="password-expire-date"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.password.expired.date.placeholder",
                      })}
                      required={true}
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      value={
                        userData && userData.expirationDate
                          ? userData.expirationDate
                          : ""
                      }
                      onChange={(e) => handleExpirationDateChange(e)}
                    />
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
                    <TextInput
                      id="login-timeout"
                      className="defalut"
                      type="text"
                      labelText=""
                      placeholder={intl.formatMessage({
                        id: "login.timeout.placeholder",
                      })}
                      required={true}
                      // invalid={errors.order && touched.order}
                      // invalidText={errors.order}
                      value={
                        userData && userData.timeout ? userData.timeout : ""
                      }
                      onChange={(e) => handleTimeoutChange(e)}
                    />
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
                    <RadioButtonGroup
                      defaultSelected={isLocked}
                      name="account-locked"
                    >
                      <RadioButton
                        checked={isLocked === "radio-1"}
                        labelText="Y"
                        value="radio-1"
                        id="radio-1"
                      />
                      <RadioButton
                        checked={isLocked === "radio-2"}
                        labelText="N"
                        value="radio-2"
                        id="radio-2"
                      />
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
                    <RadioButtonGroup
                      defaultSelected={isDisabled}
                      name="account-disabled"
                    >
                      <RadioButton
                        checked={isDisabled === "radio-3"}
                        labelText="Y"
                        value="radio-3"
                        id="radio-3"
                      />
                      <RadioButton
                        checked={isDisabled === "radio-4"}
                        labelText="N"
                        value="radio-4"
                        id="radio-4"
                      />
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
                    <RadioButtonGroup
                      defaultSelected={isActive}
                      name="account-isActive"
                    >
                      <RadioButton
                        checked={isActive === "radio-5"}
                        labelText="Y"
                        value="radio-5"
                        id="radio-5"
                      />
                      <RadioButton
                        checked={isActive === "radio-6"}
                        labelText="N"
                        value="radio-6"
                        id="radio-6"
                      />
                    </RadioButtonGroup>
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
                    {/* {userData && ( */}
                    <Select
                      id="copy-permissions"
                      noLabel={true}
                      defaultValue={
                        copyUserPermissionList &&
                        copyUserPermissionList.length > 0
                          ? copyUserPermissionList[0].id
                          : ""
                      }
                      onChange={(e) => handleCopyUserPermissionsChange(e)}
                    >
                      <SelectItem key="0" value="0" text="Select User..." />
                      {copyUserPermissionList &&
                      copyUserPermissionList.length > 0 ? (
                        copyUserPermissionList.map((section) => (
                          <SelectItem
                            key={section.id}
                            value={section.id}
                            text={section.value}
                          />
                        ))
                      ) : (
                        <SelectItem
                          key=""
                          value=""
                          text="No options available"
                        />
                      )}
                    </Select>
                    {/* )} */}
                  </Column>
                  <br />
                  <Button
                    type="button"
                    // onClick={}
                  >
                    <FormattedMessage id="systemuserrole.apply" />
                  </Button>
                </Grid>
                <hr />
                <br />
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <FormattedMessage id="systemuserrole.roles.global" />
                    <br />
                    <FormGroup legendId="globalRules" legendText="">
                      {userDataShow &&
                      userDataShow.globalRoles &&
                      userDataShow.globalRoles.length > 0 ? (
                        userDataShow.globalRoles.map((section) => (
                          <Checkbox
                            key={section.elementID}
                            id={section.elementID}
                            value={section.roleId}
                            labelText={section.roleName}
                            checked={selectedGlobalLabUnitRoles.includes(
                              section.roleId,
                            )}
                            onChange={() => {
                              handleCheckboxChange(section.roleId);
                            }}
                          />
                        ))
                      ) : (
                        <Checkbox
                          id="no-options"
                          value=""
                          labelText="No options available"
                        />
                      )}
                    </FormGroup>
                    <br />
                  </Column>
                  <Column lg={8} md={4} sm={4}>
                    <FormattedMessage id="systemuserrole.roles.labunit" />
                    <br />
                    <br />
                    <Select
                      id="select-3"
                      noLabel={true}
                      defaultValue={
                        userDataShow &&
                        userDataShow.testSections &&
                        userDataShow.testSections.length > 0
                          ? userDataShow.testSections[0].id
                          : ""
                      }
                      onChange={handleTestSectionsSelectChange}
                    >
                      <SelectItem
                        key="option-1"
                        value="option-1"
                        text="All Lab Units"
                      />
                      {userDataShow &&
                      userDataShow.testSections &&
                      userDataShow.testSections.length > 0 ? (
                        userDataShow.testSections.map((section) => (
                          <SelectItem
                            key={section.id}
                            value={section.id}
                            text={section.value}
                          />
                        ))
                      ) : (
                        <SelectItem
                          key=""
                          value=""
                          text="No options available"
                        />
                      )}
                    </Select>
                    <br />
                    <Checkbox
                      id="all-permissions"
                      labelText={"All Permissions"}
                      checked={["4", "5", "7", "10"].every((num) =>
                        selectedGlobalLabUnitRoles.includes(num),
                      )}
                      onChange={() => {
                        const numbersToAdd = ["4", "5", "7", "10"];
                        const updatedRoles = [...selectedGlobalLabUnitRoles];
                        const numbersToRemove = numbersToAdd.filter((num) =>
                          updatedRoles.includes(num),
                        );
                        if (numbersToRemove.length > 0) {
                          numbersToRemove.forEach((num) => {
                            const index = updatedRoles.indexOf(num);
                            if (index !== -1) {
                              updatedRoles.splice(index, 1);
                            }
                          });
                        } else {
                          updatedRoles.push(...numbersToAdd);
                        }
                        setSelectedGlobalLabUnitRoles(updatedRoles);
                      }}
                    />
                    <br />
                    <FormGroup legendId="labUnitRoles" legendText="">
                      {userDataShow &&
                      userDataShow.labUnitRoles &&
                      userDataShow.labUnitRoles.length > 0 ? (
                        userDataShow.labUnitRoles.map((section) => (
                          <Checkbox
                            key={section.elementID}
                            id={section.elementID}
                            value={section.roleId}
                            labelText={section.roleName}
                            checked={selectedGlobalLabUnitRoles.includes(
                              section.roleId,
                            )}
                            onChange={() => {
                              handleCheckboxChange(section.roleId);
                            }}
                          />
                        ))
                      ) : (
                        <Checkbox
                          id="no-options"
                          value=""
                          labelText="No options available"
                        />
                      )}
                    </FormGroup>
                    <br />
                  </Column>
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={16} md={8} sm={4}>
                    <Button
                      disabled={saveButton}
                      onClick={userSavePostCall}
                      type="button"
                    >
                      <FormattedMessage id="label.button.save" />
                    </Button>{" "}
                    <Button
                      onClick={() =>
                        window.location.assign(
                          "/MasterListsPage#userManagement",
                        )
                      }
                      kind="tertiary"
                      type="button"
                    >
                      <FormattedMessage id="label.button.exit" />
                    </Button>
                  </Column>
                </Grid>
              </Form>
            </Column>
          </Grid>
        </div>
      </div>
      <button
        onClick={() => {
          console.error(userData);
        }}
      >
        userData
      </button>
      <button
        onClick={() => {
          console.error(userDataShow);
        }}
      >
        userDataManuplated
      </button>
      <button
        onClick={() => {
          console.error(selectedGlobalLabUnitRoles);
        }}
      >
        selectedGlobalLabUniRoles
      </button>
      <button
        onClick={() => {
          console.error(userDataPost);
        }}
      >
        userDataPost
      </button>
      <button
        onClick={() => {
          console.error(copyUserPermissionList);
        }}
      >
        copyPermissionUserList
      </button>
      <button
        onClick={() => {
          console.error(copyUserPermission);
        }}
      >
        copyPermissionUser
      </button>
      <button
        onClick={() => {
          console.error(testSectionsSelect);
        }}
      >
        testSectionsSelect
      </button>
    </>
  );
}

export default injectIntl(UserAddEdit);

// on change of checkbox need a fix
// adding another permission if ( all permission is not selected )
// expiration date fix
// radio button fixes
// copy permission fix [ name display fix ]
// checkBox function destructuer
// onSelect of Global Administration it should check/add all other roles
