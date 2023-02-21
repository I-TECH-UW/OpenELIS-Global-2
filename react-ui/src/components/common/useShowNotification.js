import { useState } from 'react';
import { ToastNotification } from "@carbon/react";

export const NotificationKinds = {
    info: "info",
    error: "error",
    success: "success",
    warning: "warning"
};

export const useShowNotification = (initialState = false) => {

    const [notificationVisible, setNotificationVisible] = useState(initialState);
    const [notificationBody, setNotificationBody] = useState({
        title: "",
        message: "",
        kind: NotificationKinds.info
    });


    const AlertDialog = () => {
        return (
            <div className="toastDisplay">
                <ToastNotification
                    title={notificationBody.title}
                    timeout={8000}
                    lowContrast={true}
                    kind={notificationBody.kind}
                    subtitle={notificationBody.message}
                />
            </div>
        );
    };
    return [notificationVisible, setNotificationVisible, setNotificationBody, AlertDialog];
}

