import { Renew, NotificationFilled, Email, Filter } from "@carbon/icons-react";
import { useEffect, useState } from "react";
import {
  formatTimestamp,
  getFromOpenElisServer,
  putToOpenElisServer,
} from "../utils/Utils";

export default function SlideOverNotifications() {
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    const getNotifications = async () => {
      try {
        getFromOpenElisServer("/rest/notifications", (data) => {
          setNotifications(data);
        });
      } catch (error) {
        console.error("Failed to fetch notifications", error);
      }
    };

    getNotifications();
  }, []);

  const markNotificationAsRead = (notificationId) => {
    console.log("Marking notification as read", notificationId);

    putToOpenElisServer(
      "/rest/notification/markasread/" + notificationId,
      null,
      (data) => {
        {
          console.log("Notification marked as read", data);
        }
      },
    );
  };

  const NotificationButton = ({ icon, label, id }) => (
    <button
      onClick={() => {
        markNotificationAsRead(id);
      }}
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        backgroundColor: "white",
        padding: "0.5rem 0.8rem",
        fontWeight: "600",
        border: "none",
        borderRadius: "0.3rem",
        transition: "background-color 0.2s ease-in-out",
        color: "#837994",
        whiteSpace: "nowrap",
      }}
      onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = "#DFDAE8")}
      onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = "white")}
    >
      {icon}
      <span style={{ fontSize: "0.75rem", marginLeft: "0.5rem" }}>{label}</span>
    </button>
  );

  return (
    <div
      style={{
        backgroundColor: "white",
        borderRadius: "0.3rem",
        transition: "background-color 0.2s ease-in-out",
        padding: "1rem",
        maxWidth: "600px",
        margin: "0 auto",
      }}
    >
      <div style={{ display: "flex", flexDirection: "column", gap: "0.5rem" }}>
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            gap: "0.5rem",
            flexWrap: "wrap",
          }}
        >
          {[
            { icon: <Renew className="spin" />, label: "Reload" },
            { icon: <NotificationFilled />, label: "Subscribe on this Device" },
            { icon: <Email />, label: "Mark all as Read" },
            { icon: <Filter />, label: "Show Unread" },
          ].map(({ icon, label }, index) => (
            <NotificationButton key={index} icon={icon} label={label} />
          ))}
        </div>

        {notifications?.map((notification, index) => (
          <div
            key={index}
            style={{
              position: "relative",
              cursor: "pointer",
              borderRadius: "0.5rem",
              padding: "1rem",
              transition: "all 0.2s ease-in-out",
              backgroundColor: "white",
              marginTop: "0.5rem",
            }}
            onMouseOver={(e) =>
              (e.currentTarget.style.backgroundColor = "#f3f3f3")
            }
            onMouseOut={(e) =>
              (e.currentTarget.style.backgroundColor = "white")
            }
            onFocus={(e) => (e.currentTarget.style.backgroundColor = "#e2e8f0")}
          >
            <div
              style={{ display: "flex", padding: "2px 0px", margin: "1px 0px" }}
            ></div>
            <div
              style={{
                paddingTop: "0.25rem",
                fontSize: "0.9rem",
                fontWeight: "500",
              }}
            >
              {notification.message}
            </div>
            <div
              style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "flex-end",
                gap: "0.5rem",
              }}
            >
              <div
                style={{
                  paddingTop: "0.25rem",
                  textAlign: "right",
                  fontSize: "0.75rem",
                  color: "#4b5563",
                }}
              >
                {formatTimestamp(notification.createdDate)}
              </div>
              <div
                style={{
                  display: "flex",
                  justifyContent: "flex-end",
                  gap: "0.5rem",
                }}
              >
                <NotificationButton
                id={notification.id}
                  icon={<Email />}
                  label="Mark as Read"
                />
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
