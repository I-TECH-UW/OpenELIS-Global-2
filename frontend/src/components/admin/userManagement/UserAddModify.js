import React, { useContext, useState, useEffect, useRef } from "react";
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
  Button,
  Loading,
  Select,
  SelectItem,
  PasswordInput,
  Checkbox,
  FormGroup,
} from "@carbon/react";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import {
  ConfigurationContext,
  NotificationContext,
} from "../../layout/Layout.js";
import {
  getFromOpenElisServer,
  postToOpenElisServer,
  postToOpenElisServerJsonResponse,
} from "../../utils/Utils.js";
import AutoComplete from "../../common/AutoComplete.js";

const breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
  {
    label: "unifiedSystemUser.browser.title",
    link: "/MasterListsPage#userManagement",
  },
];

function UserAddModify() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);
  const { configurationProperties } = useContext(ConfigurationContext);

  const componentMounted = useRef(false);
  const intl = useIntl();

  const [saveButton, setSaveButton] = useState(true);
  const [isLoading, setIsLoading] = useState(true);
  const [isLocked, setIsLocked] = useState("radio-2");
  const [isDisabled, setIsDisabled] = useState("radio-4");
  const [isActive, setIsActive] = useState("radio-6");
  const [copyUserPermission, setCopyUserPermission] = useState("0");
  const [copyUserPermissionList, setCopyUserPermissionList] = useState(null);
  const [testSectionsSelect, setTestSectionsSelect] = useState("AllLabUnits");
  const [userData, setUserData] = useState(null);
  const [userDataShow, setUserDataShow] = useState({});
  const [userDataPost, setUserDataPost] = useState(null);
  const [selectedGlobalLabUnitRoles, setSelectedGlobalLabUnitRoles] = useState(
    [],
  );
  const [selectedTestSectionLabUnits, setSelectedTestSectionLabUnits] =
    useState({});

  const ID = (() => {
    const hash = window.location.hash;
    if (hash.includes("?")) {
      const queryParams = hash.split("?")[1];
      const urlParams = new URLSearchParams(queryParams);
      return urlParams.get("ID");
    }
    return "0";
  })();

  useEffect(() => {
    componentMounted.current = true;
    setIsLoading(true);
    if (ID) {
      getFromOpenElisServer(
        `/rest/UnifiedSystemUser?ID=${ID}&startingRecNo=1&roleFilter=`,
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
  }, [ID]);

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
        selectedTestSectionLabUnits: userData.selectedTestSectionLabUnits,
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
        selectedTestSectionLabUnits: userData.selectedTestSectionLabUnits,
        systemUserId: userData.systemUserId,
        systemUserIdToCopy: userData.systemUserIdToCopy,
        systemUserLastupdated: userData.systemUserLastupdated,
        testSections: userData.testSections,
        timeout: userData.timeout,
        userFirstName: userData.userFirstName,
        userLastName: userData.userLastName,
        userLoginName: userData.userLoginName,
        userPassword: userData.userPassword,
      };
      setUserDataShow(userManagementInfoToShow);
      setUserDataPost(userManagementInfoToPost);

      if (userData.globalRoles) {
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
        setUserDataShow((prevUserDataShow) => ({
          ...prevUserDataShow,
          globalRoles: globalRoles,
        }));
      }

      if (userData.labUnitRoles) {
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
        setUserDataShow((prevUserDataShow) => ({
          ...prevUserDataShow,
          labUnitRoles: labUnitRoles,
        }));
      }

      if (userData.testSections) {
        const testSections = userData.testSections.map((item) => {
          return {
            id: item.id,
            value: item.value,
          };
        });
        const updatedTestSections = [
          { id: "AllLabUnits", value: "All Lab Units" },
          ...testSections,
        ];
        setUserDataShow((prevUserDataShow) => ({
          ...prevUserDataShow,
          testSections: updatedTestSections,
        }));
      }

      if (userData.selectedRoles) {
        if (ID !== "0") {
          const selectedGlobalLabUniRoles = userData.selectedRoles.map(
            (item) => item,
          );
          setSelectedGlobalLabUnitRoles(selectedGlobalLabUniRoles);
        } else {
          setSelectedGlobalLabUnitRoles([]);
        }
      }

      if (userData.selectedTestSectionLabUnits) {
        if (ID !== "0") {
          setSelectedTestSectionLabUnits(userData.selectedTestSectionLabUnits);
        } else {
          setSelectedTestSectionLabUnits({});
        }
      }
    }
  }, [userData]);

  useEffect(() => {
    if (userDataShow) {
      setIsLocked(userDataShow.accountLocked === "Y" ? "radio-1" : "radio-2");
      setIsDisabled(
        userDataShow.accountDisabled === "Y" ? "radio-3" : "radio-4",
      );
      setIsActive(userDataShow.accountActive === "Y" ? "radio-5" : "radio-6");
    }
  }, [userDataShow]);

  function userSavePostCall() {
    setIsLoading(true);
    postToOpenElisServerJsonResponse(
      `/rest/UnifiedSystemUser`,
      JSON.stringify(userDataPost),
      (res) => {
        userSavePostCallback(res);
      },
    );
  }

  function userSavePostCallback(res) {
    if (res) {
      setIsLoading(false);
      addNotification({
        title: intl.formatMessage({
          id: "notification.title",
        }),
        message: intl.formatMessage({
          id: "notification.user.post.save.success",
        }),
        kind: NotificationKinds.success,
      });
      setNotificationVisible(true);
      setTimeout(() => {
        window.location.reload();
      }, 2000);
    } else {
      addNotification({
        kind: NotificationKinds.error,
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({ id: "server.error.msg" }),
      });
      setNotificationVisible(true);
      setTimeout(() => {
        window.location.reload();
      }, 2000);
    }
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

  function handleAccountActiveChange(e) {
    setSaveButton(false);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      accountActive: e.target.value,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      accountActive: e.target.value,
    }));
  }

  function handleAccountDisabledChange(e) {
    setSaveButton(false);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      accountDisabled: e.target.value,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      accountDisabled: e.target.value,
    }));
  }

  function handleAccountLockedChange(e) {
    setSaveButton(false);
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      accountLocked: e.target.value,
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      accountLocked: e.target.value,
    }));
  }

  function handleCopyUserPermissionsChange() {
    if (copyUserPermission.length > 0) {
      setSaveButton(false);
    }
  }

  function handleAutoCompleteCopyUserPermissionsChange(selectedUserId) {
    setCopyUserPermission(selectedUserId);
    setSaveButton(false);
  }

  useEffect(() => {
    setUserDataPost((prevUserDataPost) => ({
      ...prevUserDataPost,
      systemUserIdToCopy: copyUserPermission,
      allowCopyUserRoles: "Y",
    }));
    setUserDataShow((prevUserData) => ({
      ...prevUserData,
      systemUserIdToCopy: copyUserPermission,
      allowCopyUserRoles: "Y",
    }));
    setUserData((prevUserData) => ({
      ...prevUserData,
      systemUserIdToCopy: copyUserPermission,
      allowCopyUserRoles: "Y",
    }));
    setSaveButton(false);
  }, [copyUserPermission]);

  function handleCopyUserPermissionsChangeClick() {
    setSelectedTestSectionLabUnits([]);
    userSavePostCall();
  }

  function handleCheckboxChange(roleId) {
    const numberToUpdate = ["71", "72", "73", "11", "2"];
    let updatedRoles = [...selectedGlobalLabUnitRoles];

    if (roleId === "1") {
      if (selectedGlobalLabUnitRoles.includes(roleId)) {
        updatedRoles = updatedRoles.filter((role) => role !== roleId);
      } else {
        updatedRoles = Array.from(
          new Set([...updatedRoles, roleId, ...numberToUpdate]),
        );
      }
    } else {
      if (selectedGlobalLabUnitRoles.includes(roleId)) {
        updatedRoles = updatedRoles.filter((id) => id !== roleId);
      } else {
        updatedRoles = [...updatedRoles, roleId];
      }
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
    setSaveButton(false);
  }

  function handleTestSectionsSelectChange(e, key) {
    const selectedValue = e.target.value;

    if (Object.keys(selectedTestSectionLabUnits).includes(selectedValue)) {
      alert(`Section ${selectedValue} is already selected.`);
      const updatedSelections = { ...selectedTestSectionLabUnits };
      delete updatedSelections[selectedValue];
      setSelectedTestSectionLabUnits(updatedSelections);
      return;
    }

    let updatedTestSectionLabUnits = { ...selectedTestSectionLabUnits };

    if (!Object.keys(updatedTestSectionLabUnits).includes(selectedValue)) {
      updatedTestSectionLabUnits[selectedValue] = [];
    } else {
      delete updatedTestSectionLabUnits[selectedValue];
    }

    if (key !== selectedValue) {
      delete updatedTestSectionLabUnits[key];
    }

    setSelectedTestSectionLabUnits(updatedTestSectionLabUnits);

    setTestSectionsSelect(selectedValue);
    setSaveButton(false);
  }

  const addRoleToSelectedUnits = (key, roleIdToAdd) => {
    setSelectedTestSectionLabUnits((prevUnits) => {
      const updatedUnits = { ...prevUnits };
      const currentRoles = updatedUnits[key] || [];
      if (!currentRoles.includes(roleIdToAdd)) {
        updatedUnits[key] = [...currentRoles, roleIdToAdd];
        setSaveButton(false);
      }
      return updatedUnits;
    });
  };

  const removeRoleFromSelectedUnits = (key, roleIdToRemove) => {
    setSelectedTestSectionLabUnits((prevUnits) => {
      const updatedUnits = { ...prevUnits };
      if (updatedUnits[key]) {
        updatedUnits[key] = updatedUnits[key].filter(
          (roleId) => roleId !== roleIdToRemove,
        );
        setSaveButton(false);
      }
      return updatedUnits;
    });
  };

  const addNewSection = () => {
    const newSectionsToAdd = userDataShow.testSections.filter(
      (section) =>
        !Object.keys(selectedTestSectionLabUnits).includes(section.id),
    );

    if (newSectionsToAdd.length > 0) {
      const nextSectionToAdd = newSectionsToAdd[0];
      setSelectedTestSectionLabUnits((prev) => ({
        ...prev,
        [nextSectionToAdd.id]: [],
      }));
    }
  };

  const removeSection = (keyToRemove) => {
    const updatedSections = { ...selectedTestSectionLabUnits };
    delete updatedSections[keyToRemove];
    setSelectedTestSectionLabUnits(updatedSections);
  };

  useEffect(() => {
    if (selectedTestSectionLabUnits) {
      setUserDataPost((prevUserDataPost) => ({
        ...prevUserDataPost,
        selectedTestSectionLabUnits: selectedTestSectionLabUnits,
      }));

      setUserDataShow((prevUserData) => ({
        ...prevUserData,
        selectedTestSectionLabUnits: selectedTestSectionLabUnits,
      }));

      setUserData((prevUserData) => ({
        ...prevUserData,
        selectedTestSectionLabUnits: selectedTestSectionLabUnits,
      }));
    }
  }, [selectedTestSectionLabUnits]);

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
                  {ID === "0" ? (
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
                    <span style={{ display: "flex", alignItems: "center" }}>
                      <RadioButton
                        checked={isLocked === "radio-1"}
                        labelText="Y"
                        value="Y"
                        id="radio-1"
                        onClick={(e) => {
                          setIsLocked("radio-1");
                          handleAccountLockedChange(e);
                        }}
                      />
                      <RadioButton
                        checked={isLocked === "radio-2"}
                        labelText="N"
                        value="N"
                        id="radio-2"
                        onClick={(e) => {
                          setIsLocked("radio-2");
                          handleAccountLockedChange(e);
                        }}
                      />
                    </span>
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
                    <span style={{ display: "flex", alignItems: "center" }}>
                      <RadioButton
                        checked={isDisabled === "radio-3"}
                        labelText="Y"
                        value="Y"
                        id="radio-3"
                        onClick={(e) => {
                          setIsDisabled("radio-3");
                          handleAccountDisabledChange(e);
                        }}
                      />
                      <RadioButton
                        checked={isDisabled === "radio-4"}
                        labelText="N"
                        value="N"
                        id="radio-4"
                        onClick={(e) => {
                          setIsDisabled("radio-4");
                          handleAccountDisabledChange(e);
                        }}
                      />
                    </span>
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
                    <span style={{ display: "flex", alignItems: "center" }}>
                      <RadioButton
                        checked={isActive === "radio-5"}
                        labelText="Y"
                        value="Y"
                        id="radio-5"
                        onClick={(e) => {
                          setIsActive("radio-5");
                          handleAccountActiveChange(e);
                        }}
                      />
                      <RadioButton
                        checked={isActive === "radio-6"}
                        labelText="N"
                        value="N"
                        id="radio-6"
                        onClick={(e) => {
                          setIsActive("radio-6");
                          handleAccountActiveChange(e);
                        }}
                      />
                    </span>
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
                    <AutoComplete
                      name="copy-permissions"
                      id="copy-permissions"
                      allowFreeText={
                        !(
                          configurationProperties.restrictFreeTextProviderEntry ===
                          "true"
                        )
                      }
                      onChange={handleCopyUserPermissionsChange}
                      onSelect={handleAutoCompleteCopyUserPermissionsChange}
                      suggestions={
                        copyUserPermissionList?.length > 0
                          ? copyUserPermissionList
                          : []
                      }
                      required
                    />
                  </Column>
                  <br />
                  <Button
                    disabled={copyUserPermission === "0"}
                    type="button"
                    onClick={() => {
                      handleCopyUserPermissionsChangeClick();
                    }}
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
                </Grid>
                <Grid fullWidth={true}>
                  <Column lg={8} md={4} sm={4}>
                    <FormattedMessage id="systemuserrole.roles.labunit" />
                  </Column>
                </Grid>
                <br />
                <>
                  {Object.keys(selectedTestSectionLabUnits).map((key) => (
                    <Grid
                      fullWidth={true}
                      key={key}
                      style={{ paddingBottom: "10px" }}
                    >
                      <Column lg={8} md={4} sm={4}>
                        <Select
                          id={`select-${key}`}
                          noLabel={true}
                          defaultValue={
                            userDataShow &&
                            userDataShow.testSections &&
                            userDataShow.testSections.length > 0
                              ? userDataShow.testSections.find(
                                  (section) => section.id === key,
                                )?.id || userDataShow.testSections[0].id
                              : ""
                          }
                          onChange={(e) =>
                            handleTestSectionsSelectChange(e, key)
                          }
                        >
                          {userDataShow &&
                          userDataShow.testSections &&
                          userDataShow.testSections.length > 0 ? (
                            userDataShow.testSections
                              .filter(
                                (section) =>
                                  !Object.keys(
                                    selectedTestSectionLabUnits,
                                  ).includes(section.id) || section.id === key,
                              )
                              .map((section) => (
                                <SelectItem
                                  key={`${section.id}-${key}`}
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
                          id={`all-permissions-${key}`}
                          labelText={"All Permissions"}
                          checked={["4", "5", "7", "10"].every(
                            (num) =>
                              selectedTestSectionLabUnits[key] &&
                              selectedTestSectionLabUnits[key].includes(num),
                          )}
                          onChange={() => {
                            const numbersToAdd = ["4", "5", "7", "10"];
                            const updatedRoles = selectedTestSectionLabUnits[
                              key
                            ]
                              ? [...selectedTestSectionLabUnits[key]]
                              : [];
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
                            setSelectedTestSectionLabUnits((prev) => ({
                              ...prev,
                              [key]: updatedRoles,
                            }));
                            setSaveButton(false);
                          }}
                        />
                        <FormGroup
                          key={key}
                          legendId={`labUnitRoles-${key}`}
                          legendText=""
                        >
                          {userDataShow &&
                          userDataShow.labUnitRoles &&
                          userDataShow.labUnitRoles.length > 0 ? (
                            userDataShow.labUnitRoles.map((section) => (
                              <Checkbox
                                key={`${section.elementID}-${key}`}
                                id={`${section.elementID}-${key}`}
                                value={section.roleId}
                                labelText={section.roleName}
                                checked={
                                  selectedTestSectionLabUnits[key] &&
                                  selectedTestSectionLabUnits[key].includes(
                                    section.roleId,
                                  )
                                }
                                onChange={() => {
                                  if (
                                    selectedTestSectionLabUnits[key]?.includes(
                                      section.roleId,
                                    )
                                  ) {
                                    removeRoleFromSelectedUnits(
                                      key,
                                      section.roleId,
                                    );
                                  } else {
                                    addRoleToSelectedUnits(key, section.roleId);
                                  }
                                }}
                              />
                            ))
                          ) : (
                            <Checkbox
                              id={`no-options-${key}`}
                              value=""
                              labelText="No options available"
                            />
                          )}
                        </FormGroup>
                        <Button
                          onClick={() => removeSection(key)}
                          kind="tertiary"
                          type="button"
                        >
                          <FormattedMessage id="systemuserrole.rmpermissions" />
                        </Button>
                      </Column>
                    </Grid>
                  ))}
                </>
                <Grid fullWidth={true}>
                  <Column lg={16} md={8} sm={4}>
                    <Button onClick={addNewSection} type="button">
                      <FormattedMessage id="systemuserrole.newpermissions" />
                    </Button>
                  </Column>
                </Grid>
                <hr />
                <br />
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
    </>
  );
}

export default injectIntl(UserAddModify);
