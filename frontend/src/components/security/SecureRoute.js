import React, { useState, useContext, useEffect, useRef } from "react";
import UserSessionDetailsContext from "../../UserSessionDetailsContext";
import { Route } from "react-router-dom";
import IdleTimer from "react-idle-timer";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css"; // Import css
import { Loading } from "@carbon/react/";
import config from "../../config.json";
import { useIntl } from "react-intl";

const idleTimeout = 1000 * 60 * 15; // milliseconds until idle warning will appear
const idleWarningTimeout = 1000 * 60 * 1; // milliseconds until logout is automatically processed from idle warning

function SecureRoute(props) {
  const [permissionGranted, setPermissionGranted] = useState(false);
  const [isIdle, setIsIdle] = useState(false);
  const [loading, setLoading] = useState(false);

  const idleTimer = useRef();
  const intl = useIntl();

  const {
    userSessionDetails,
    errorLoadingSessionDetails,
    isCheckingLogin,
    logout,
  } = useContext(UserSessionDetailsContext);

  useEffect(() => {
    setLoading(!errorLoadingSessionDetails && isCheckingLogin());
    if (userSessionDetails.authenticated) {
      console.info("Authenticated");
      if (hasPermission(userSessionDetails)) {
        console.info("Access Allowed");
      } else {
        const options = {
          title: intl.formatMessage({ id: "accessDenied.title" }),
          message: intl.formatMessage({ id: "accessDenied.message" }),
          buttons: [
            {
              label: intl.formatMessage({ id: "accessDenied.okButton" }),
              onClick: () => {
                window.location.href = window.location.origin;
              },
            },
          ],
          closeOnClickOutside: false,
          closeOnEscape: false,
        };
        confirmAlert(options);
      }
      setPermissionGranted(hasPermission());
    } else if ("authenticated" in userSessionDetails) {
      window.location.href = config.loginRedirect;
    }
  }, [userSessionDetails, errorLoadingSessionDetails]);

  const hasPermission = (userDetails = userSessionDetails) => {
    var hasRole =
      !props.role ||
      []
        .concat(props.role)
        .some((role) => userDetails.roles && userDetails.roles.includes(role));
    var containsLabUnitRole = false;
    if (props.labUnitRole) {
      Object.keys(props.labUnitRole).forEach((labunit) => {
        if (userDetails.userLabRolesMap) {
          const userRoles = userDetails.userLabRolesMap["AllLabUnits"]
            ? userDetails.userLabRolesMap["AllLabUnits"]
            : userDetails.userLabRolesMap[labunit] || [];
          const roles = props.labUnitRole[labunit];
          roles.forEach((r) => {
            if (userRoles.includes(r)) {
              containsLabUnitRole = true;
            }
          });
        }
      });
    }
    var hasLabUnitRole = !props.labUnitRole || containsLabUnitRole;
    return hasRole && hasLabUnitRole;
  };

  const handleOnAction = (event) => {
    console.debug("no action is defined on the IdleTimer", event);
  };

  const handleOnActive = (event) => {
    console.debug("user is active", event);
    setIsIdle(false);
  };

  const handleOnIdle = (event) => {
    console.debug("user is idle", event);
    setIsIdle(true);

    const timer = () =>
      setTimeout(() => {
        logout();
      }, idleWarningTimeout);
    const timeoutEventID = timer();

    const options = {
      title: intl.formatMessage({ id: "stillThere.title" }),
      message: intl.formatMessage({ id: "stillThere.message" }),
      buttons: [
        {
          label: intl.formatMessage({ id: "yes.option" }),
          onClick: () => {
            clearTimeout(timeoutEventID);
          },
        },
      ],
    };
    confirmAlert(options);
  };

  return (
    <>
      {loading && <Loading />}
      {!loading &&
        !userSessionDetails.authenticated &&
        intl.formatMessage({ id: "notAuthenticated" })}
      {!loading && userSessionDetails.authenticated && permissionGranted && (
        <>
          <IdleTimer
            ref={idleTimer}
            timeout={idleTimeout}
            onActive={handleOnActive}
            onIdle={handleOnIdle}
            onAction={handleOnAction}
            debounce={250}
          />
          {!isIdle && <Route {...props} />}
        </>
      )}
    </>
  );
}

export default SecureRoute;
