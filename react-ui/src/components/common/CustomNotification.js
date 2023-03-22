import {useContext, useEffect} from 'react';
import {ToastNotification} from "@carbon/react";
import {NotificationContext} from "../layout/Layout";

export const NotificationKinds = {
    info: "info",
    error: "error",
    success: "success",
    warning: "warning"
};

export const AlertDialog = () => {
    const { notificationBody,setNotificationVisible } = useContext(NotificationContext);

    useEffect(()=>{
        setTimeout(
            () => {
                setNotificationVisible(false);
            },
            7000
        )
    },[]);
    return (
        <div className="toastDisplay">
            <ToastNotification
                title={notificationBody.title}
                timeout={7000}
                lowContrast={true}
                kind={notificationBody.kind}
                subtitle={notificationBody.message}
            />
        </div>
    );
}

