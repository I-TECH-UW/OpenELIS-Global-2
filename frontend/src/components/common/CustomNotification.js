import React, { useContext, useEffect } from "react";
import { ToastNotification } from "@carbon/react";
import { NotificationContext } from "../layout/Layout";

export const NotificationKinds = {
  info: "info",
  error: "error",
  success: "success",
  warning: "warning",
};

export const AlertDialog = () => {
  const { notificationBody } = useContext(NotificationContext);

  return (
    <div className="toastDisplay">
      <ToastNotification
        title={notificationBody.title}
        timeout={
          notificationBody.kind !== NotificationKinds.error ? 8000 : 100000
        }
        onClose={() => {}}
        onCloseButtonClick={() => {}}
        lowContrast={true}
        kind={notificationBody.kind}
        subtitle={notificationBody.subtitle}
      >
        {notificationBody.message}
        <br />
        <br />
      </ToastNotification>
    </div>
  );
};
