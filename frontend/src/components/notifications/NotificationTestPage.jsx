import { useState } from "react";
import { getFromOpenElisServer, postToOpenElisServer } from "../utils/Utils";
import { Button, Column, Grid, Heading, Loading, Section } from "@carbon/react";
import { AlertDialog } from "../common/CustomNotification";
import PageBreadCrumb from "../common/PageBreadCrumb";
import { FormattedMessage } from "react-intl";

export default function NotificationTestPage() {
  const [data, setData] = useState({
    message: "",
  });

  const submit = () => {
    if (data.message.trim() !== "") {
      postToOpenElisServer("/rest/notification", JSON.stringify(data), () => {
        console.log("Success");
      }).catch((error) => {
        console.error("Error posting notification:", error);
      });
    } else {
      console.error("Message cannot be empty");
    }
  };

  const getAllNotifications = () => {
    getFromOpenElisServer("/rest/notifications", (response) => {
      console.log(response);
    }).catch((error) => {
      console.error("Error getting notifications:", error);
    });
  };

  return (
    // <div
    //   className="adminPageContent"
    //   style={{
    //     display: "flex",
    //     flexDirection: "column",

    //     padding: "20px",
    //   }}
    // >
    //   <div style={{ marginBottom: "10px" }}>
    //     <h1>User Notification</h1>
    //   </div>

    //   <Button onClick={getAllNotifications} style={{ marginBottom: "10px" }}>
    //     Get Request
    //   </Button>

    //   <input
    //     type="text"
    //     name="message"
    //     id="message"
    //     value={data.message}
    //     onChange={(e) => setData({ ...data, message: e.target.value })}
    //     style={{ marginBottom: "10px" }}
    //   />

    //   <Button onClick={submit} style={{ marginBottom: "10px" }}>
    //     Submit
    //   </Button>
    // </div>
    <>
      <div className="adminPageContent">
        {false === true ? <AlertDialog /> : ""}
        <PageBreadCrumb breadcrumbs={[{ label: "home.label", link: "/" }]} />
        <Grid fullWidth={true}>
          <Column lg={16} md={8} sm={4}>
            <Section>
              <Heading>
                <FormattedMessage id="notify.main.title" />
              </Heading>
            </Section>
          </Column>
        </Grid>
        {false && <Loading />} {"" && <p>Error: {""}</p>}{" "}
        <div className="orderLegendBody">
          <div style={{ marginLeft: "2em" }} className="inlineDiv">
            <Button type="submit" onClick={() => {}}>
              <FormattedMessage id="button.send"   />
            </Button>
          </div>
        </div>
      </div>
    </>
  );
}
