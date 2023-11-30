import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Form,
  FormLabel,
  Heading,
  TextInput,
  Button,
  Loading,
  Grid,
  Column,
  Section,
  Checkbox,
} from "@carbon/react";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../utils/Utils";
import { NotificationContext } from "../../layout/Layout";
import {
  AlertDialog,
  NotificationKinds,
} from "../../common/CustomNotification";
import { FormattedMessage, useIntl } from "react-intl";

function BillingMenuManagement() {
  const { notificationVisible, setNotificationVisible, setNotificationBody } =
    useContext(NotificationContext);
  const intl = useIntl();

  const componentMounted = useRef(true);

  const [loading, setLoading] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [menuItem, setMenuItem] = useState({ menu: {}, childMenus: [] });

  async function displayStatus(res) {
    setNotificationVisible(true);
    setIsSubmitting(false);
    if (res.status == "200") {
      setNotificationBody({
        kind: NotificationKinds.success,
        title: <FormattedMessage id="notification.title" />,
        message: <FormattedMessage id="success.add.edited.msg" />,
      });
      var body = await res.json();
      setMenuItem(body);
    } else {
      setNotificationBody({
        kind: NotificationKinds.error,
        title: <FormattedMessage id="notification.title" />,
        message: <FormattedMessage id="error.add.edited.msg" />,
      });
    }
  }

  function handleSubmit(event) {
    event.preventDefault();
    setIsSubmitting(true);
    postToOpenElisServerFullResponse(
      "/rest/menu/menu_billing",
      JSON.stringify(menuItem),
      displayStatus,
    );
  }

  const handleMenuItems = (res) => {
    if (res) {
      setMenuItem(res);
    }
  };

  useEffect(() => {
    getFromOpenElisServer("/rest/menu/menu_billing", handleMenuItems);
    return () => {
      componentMounted.current = false;
    };
  }, []);

  return (
    <>
      {notificationVisible === true ? <AlertDialog /> : ""}
      {loading && <Loading />}
      <div className="adminPageContent">
        <Grid>
          <Column lg={16}>
            <Section>
              <Heading>
                <FormattedMessage id="menu.billing.title" />
              </Heading>
            </Section>
            <Section>
              <Form onSubmit={handleSubmit}>
                <div className="formInlineDiv">
                  <TextInput
                    id="billing address"
                    labelText={intl.formatMessage({
                      id: "menu.billing.address",
                    })}
                    value={menuItem.menu.actionURL}
                    onChange={(e) => {
                      setMenuItem({
                        ...menuItem,
                        menu: { ...menuItem.menu, actionURL: e.target.value },
                      });
                    }}
                    type="text"
                  />
                </div>
                <div className="formInlineDiv">
                  <Checkbox
                    id="billing_active"
                    labelText={intl.formatMessage({
                      id: "menu.billing.active",
                    })}
                    checked={menuItem.menu.isActive}
                    onChange={(_, { checked }) => {
                      setMenuItem({
                        ...menuItem,
                        menu: { ...menuItem.menu, isActive: checked },
                      });
                    }}
                  />
                </div>
                <div>
                  <Button type="submit">
                    <FormattedMessage id="label.button.submit" />
                    {isSubmitting && <Loading small={true} />}
                  </Button>
                </div>
              </Form>
            </Section>
          </Column>
        </Grid>
      </div>
    </>
  );
}

export default BillingMenuManagement;
