import { Renew, NotificationFilled, Email, Filter } from "@carbon/icons-react";

export default function SlideOverNotifications() {
  return (
    <div>
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          padding: "5px",
        }}
      >
        <div>
          <button>
            <Renew />
            Reload
          </button>
          <button>
            <NotificationFilled />
            Subscribe on this Device
          </button>
        </div>
        <div>
          <button>
            <Email />
            Mark all as Read
          </button>
          <button>
            <Filter />
            Show Unread
          </button>
        </div>
      </div>
    </div>
  );
}
