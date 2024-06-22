import { useState } from "react";
import { getFromOpenElisServer, postToOpenElisServer } from "../utils/Utils";

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
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        maxWidth: "200px",
        padding: "20px",
      }}
    >
      <div style={{ marginBottom: "10px" }}>
        <h1>Notification Test Page</h1>
      </div>

      <button onClick={getAllNotifications} style={{ marginBottom: "10px" }}>
        Get Request
      </button>

      <input
        type="text"
        name="message"
        id="message"
        value={data.message}
        onChange={(e) => setData({ ...data, message: e.target.value })}
        style={{ marginBottom: "10px" }}
      />

      <button onClick={submit} style={{ marginBottom: "10px" }}>
        Submit
      </button>
    </div>
  );
}
