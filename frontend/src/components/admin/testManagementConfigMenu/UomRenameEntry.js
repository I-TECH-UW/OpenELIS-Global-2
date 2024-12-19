import React, { useContext, useState, useEffect, useRef } from "react";
import { Heading, Button, Grid, Column, Section } from "@carbon/react";
import {
  getFromOpenElisServer,
  postToOpenElisServerJsonResponse,
} from "../../utils/Utils.js";
import { NotificationContext } from "../../layout/Layout.js";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification.js";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import RenameModelBox from "./renameModel/RenameModelBox.js";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
  {
    label: "master.lists.page.test.management",
    link: "/MasterListsPage#testManagementConfigMenu",
  },
  {
    label: "configuration.uom.rename",
    link: "/MasterListsPage#UomRenameEntry",
  },
];

function UomRenameEntry() {
  const { notificationVisible, setNotificationVisible, addNotification } =
    useContext(NotificationContext);

  const intl = useIntl();

  const componentMounted = useRef(false);
  const modalHeading = intl.formatMessage({
    id: "uom.browse.title",
  });

  const [isLoading, setIsLoading] = useState(false);
  const [finished, setFinished] = useState(true);
  const [isAddModalOpen, setIsAddModalOpen] = useState(false);
  const [confirmationStep, setConfirmationStep] = useState(false);
  const [inputError, setInputError] = useState(false);
  const [uomRename, setUomRename] = useState({});
  const [uomListShow, setUomListShow] = useState([]);
  const [uomRenamePost, setUomRenamePost] = useState({});
  const [entityNamesProvider, setEntityNamesProvider] = useState({});
  const [entityNamesProviderPost, setEntityNamesProviderPost] = useState({});
  const [entityId, setEntityId] = useState();
  const [entityName, setEntityName] = useState("unitOfMeasure");
  const [selectedItem, setSelectedItem] = useState({});

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/UomRenameEntry", handleUomRename);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  const handleUomRename = (res) => {
    if (!res) {
      setIsLoading(true);
    } else {
      setUomRename(res);
      setUomRenamePost(res);
      setUomListShow(res.uomList);
    }
  };

  useEffect(() => {
    getFromOpenElisServer(
      `/rest/EntityNamesProvider?entityId=${entityId}&entityName=${entityName}`,
      handelEntityNamesProvider,
    );
  }, [entityId]);

  const handelEntityNamesProvider = (res) => {
    if (!res) {
      setIsLoading(true);
    } else {
      setEntityNamesProvider(res);
      setEntityNamesProviderPost(res);
    }
  };

  function uomRenameUpdatePost() {
    setIsLoading(true);
    if (confirmationStep) {
      postToOpenElisServerJsonResponse(
        `/rest/UomRenameEntry`,
        JSON.stringify(uomRenamePost),
        (res) => {
          uomRenameUpdatePostCallback(res);
        },
      );
    } else {
      setConfirmationStep(true);
    }
  }

  function uomRenameUpdatePostCallback(res) {
    if (res) {
      setIsLoading(false);
      setFinished(false);
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
      setIsAddModalOpen(false);
    } else {
      addNotification({
        kind: NotificationKinds.error,
        title: intl.formatMessage({ id: "notification.title" }),
        message: intl.formatMessage({ id: "server.error.msg" }),
      });
      setNotificationVisible(true);
      setTimeout(() => {
        window.location.reload();
      }, 200);
    }
  }

  const openAppModle = (item) => {
    setConfirmationStep(false);
    setIsAddModalOpen(true);
    setEntityId(item.id);
    // setEntityName(test.value);
    setSelectedItem(item);
  };

  const onInputChangeEn = (e) => {
    const englishName = e.target.value;
    setEntityNamesProviderPost((prev) => ({
      name: {
        ...prev.name,
        english: englishName,
      },
    }));
    setInputError(false);
  };

  const onInputChangeFr = (e) => {
    const frenchName = e.target.value;
    setEntityNamesProviderPost((prev) => ({
      name: {
        ...prev.name,
        french: frenchName,
      },
    }));
    setInputError(false);
  };

  useEffect(() => {
    if (entityId && entityNamesProviderPost && entityNamesProviderPost.name) {
      setUomRenamePost((prev) => ({
        ...prev,
        uomId: entityId,
        nameEnglish: entityNamesProviderPost.name.english,
        nameFrench: entityNamesProviderPost.name.french,
      }));
    }
  }, [entityNamesProviderPost, entityId]);

  const closeAddModal = () => {
    setIsAddModalOpen(false);
  };

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      <div className="adminPageContent">
        <PageBreadCrumb breadcrumbs={breadcrumbs} />
        <div className="orderLegendBody">
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Section>
                <Heading>
                  <FormattedMessage id="configuration.uom.rename" />
                </Heading>
              </Section>
            </Column>
          </Grid>
          <br />
          <hr />
          <br />
          <Grid fullWidth={true}>
            <Column lg={16} md={8} sm={4}>
              <Button
                disabled={finished}
                id="finishdButton"
                type="button"
                onClick={() => {
                  window.location.reload();
                }}
              >
                <FormattedMessage id="label.button.finished" />
              </Button>
            </Column>
          </Grid>
          <br />
          <RenameModelBox
            data={uomListShow}
            isModalOpen={isAddModalOpen}
            openModel={openAppModle}
            closeModel={closeAddModal}
            onSubmit={uomRenameUpdatePost}
            onInputChangeEn={onInputChangeEn}
            onInputChangeFr={onInputChangeFr}
            isLoading={isLoading}
            modalHeading={modalHeading}
            heading="banner.menu.patientEdit"
            mainLabel="uom.uomName"
            confirmationStep={confirmationStep}
            inputError={inputError}
            lang={entityNamesProvider}
            langPost={entityNamesProviderPost}
            selectedItem={selectedItem}
          />
        </div>
      </div>
    </>
  );
}

export default injectIntl(UomRenameEntry);
