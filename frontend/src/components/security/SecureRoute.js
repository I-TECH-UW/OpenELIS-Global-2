import React, {useState, useContext, useEffect, useRef} from "react";
import UserSessionDetailsContext from "../../UserSessionDetailsContext"
import { Route, useParams } from "react-router-dom";
import IdleTimer from 'react-idle-timer'
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css' // Import css
import { Loading } from '@carbon/react/';

const idleTimeout = 1000 * 60 * 15; // milliseconds until idle warning will appear
const idleWarningTimeout = 1000 * 60 * 1; // milliseconds until logout is automatically processed from idle warning

function SecureRoute(props) {

    const [loading, setLoading] = useState(true);
    const [authenticated, setAuthenticated] = useState(false);
    const [permissionGranted, setPermissionGranted] = useState(false);
    const [isIdle, setIsIdle] = useState(false);

    const idleTimer = useRef();

    const { userSessionDetails, setUserSessionDetails } = useContext(UserSessionDetailsContext);

    useEffect(()=>{
        console.log('component mounted!');
        fetch(props.config.serverBaseUrl + `/session`,
            //includes the browser sessionId in the Header for Authentication on the backend server
            { credentials: "include" }
        )
            .then((response) => response.json()).then(jsonResp => {
                console.log(JSON.stringify(jsonResp))
                setLoading(false);
                if (jsonResp.authenticated) {
                    console.info("Authenticated");
                   // console.log(JSON.stringify(jsonResp))
                    setAuthenticated(true);
                    props.onAuth(jsonResp);
                    setUserSessionDetails(jsonResp);
                    localStorage.setItem("CSRF", jsonResp.csrf)
                    if (hasPermission(jsonResp)) {
                        console.info("Access Allowed");
                    } else {
                        const options = {
                            title: 'Access Denied',
                            message: 'You do not have access to this module ,please contact your system administrator',
                            buttons: [
                                {
                                    label: 'OK',
                                    onClick: () => {
                                        window.location.href = window.location.origin
                                    }
                                }
                            ],
                            closeOnClickOutside: false,
                            closeOnEscape: false
                        }
                        confirmAlert(options);
                    }

                } else {
                    setAuthenticated(false);
                    window.location.href = props.config.loginRedirect;
                }
            }).catch(error => {
                console.log(error);
               setLoading(false);
                const options = {
                    title: 'System Error',
                    message: "Error : " + error.message,
                    buttons: [
                        {
                            label: 'OK',
                            onClick: () => {
                                window.location.href = window.location.origin
                            }
                        }
                    ],
                    closeOnClickOutside: false,
                    closeOnEscape: false
                }
                confirmAlert(options)
            }
            );
      },[]);


  useEffect(() => {
    setPermissionGranted(hasPermission());
  }, [userSessionDetails]);


    const hasPermission = (userDetails = userSessionDetails) => {
        var hasRole = !props.role || [].concat(props.role).some(role => userDetails.roles && userDetails.roles.includes(role))
        var containsLabUnitRole = false;
        if (props.labUnitRole) {
            Object.keys(props.labUnitRole).forEach(labunit => {
                if (userDetails.userLabRolesMap) {
                    const userRoles = userDetails.userLabRolesMap["AllLabUnits"]? userDetails.userLabRolesMap["AllLabUnits"] : userDetails.userLabRolesMap[labunit] || [];
                    const roles = props.labUnitRole[labunit];
                    roles.forEach(r => {
                        if (userRoles.includes(r)) {
                            containsLabUnitRole = true;
                        }
                    })

                }
            })
        }
        var hasLabUnitRole = !props.labUnitRole || containsLabUnitRole
        return hasRole && hasLabUnitRole
    }

    const handleOnAction = (event) => {
    }

    const handleOnActive = (event) => {
        console.log('user is active', event)
        setIsIdle(false);
    }

    const handleOnIdle = (event) => {
        console.log('user is idle', event)
        setIsIdle(true);

        const timer = () => setTimeout(() => {
            props.logout();
        }, idleWarningTimeout);
        const timeoutEventID = timer();

        const options = {
            title: 'Still there?',
            message: 'user session is about to time out',
            buttons: [
                {
                    label: 'Yes',
                    onClick: () => {
                        clearTimeout(timeoutEventID);
                    }
                }
            ],
        }
        confirmAlert(options);
    }

        return (
            <>
            {loading && 
                <Loading/>}
            {!loading && !authenticated &&
                "Not Authenticated"
            }
            {!loading && authenticated && permissionGranted &&
                <>
                <IdleTimer
                    ref={idleTimer}
                    timeout={idleTimeout}
                    onActive={handleOnActive}
                    onIdle={handleOnIdle}
                    onAction={handleOnAction}
                    debounce={250}
                />
                <Route {...props} />
                </>
            }
            </>
        )
}

export default SecureRoute;