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
  const { notificationBody, setNotificationVisible } =
    useContext(NotificationContext);

  useEffect(() => {
    setTimeout(
      () => {
        setNotificationVisible(false);
      },
      notificationBody.kind !== NotificationKinds.error ? 7000 : 100000,
    );
  }, []);
  return (
    <div className="toastDisplay">
      <ToastNotification
        title={notificationBody.title}
        timeout={
          notificationBody.kind !== NotificationKinds.error ? 8000 : 100000
        }
        lowContrast={true}
        kind={notificationBody.kind}
        subtitle={notificationBody.message}
      />
    </div>
  );
};
