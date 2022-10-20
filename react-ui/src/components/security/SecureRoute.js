import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import IdleTimer from 'react-idle-timer'
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css' // Import css

const idleTimeout = 1000 * 60 * 15; // milliseconds until idle warning will appear
const idleWarningTimeout = 1000 * 60 * 1; // milliseconds until logout is automatically processed from idle warning

class SecureRoute extends React.Component {

    constructor(props) {
        super(props);
        this.idleTimer = null
        this.state = {
            authenticated: false,
            isIdle: false,
            refreshTimeoutSet: false,
            requiredPermission: props.requiredPermission,
        }
    }

    componentDidMount() {
        fetch(this.props.config.serverBaseUrl + `/session`,
            //includes the browser sessionId in the Header for Authentication on the backend server
            { credentials: "include" }
        )
            .then((response) => response.json()).then(jsonResp => {
                console.log(JSON.stringify(jsonResp)) // access json.body here
                if (jsonResp.authenticated) {
                    console.info("Authenticated");
                    console.log(JSON.stringify(jsonResp))
                    this.setState({ authenticated: true });
                    this.props.onAuth(jsonResp);

                    const hasRole = !!jsonResp.roles.find(role => {
                        return role === this.props.role
                    })
                    console.log("...." + hasRole);
                } else {
                    window.location.href = this.props.config.loginRedirect;
                }
            }).catch(error => console.log(error));
    }

    handleOnAction = (event) => {
    }

    handleOnActive = (event) => {
        console.log('user is active', event)
        this.setState({ isIdle: false });
    }

    handleOnIdle = (event) => {
        console.log('user is idle', event)
        this.setState({ isIdle: true });

        const timer = () => setTimeout(() => {
            this.props.logout();
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

    render() {
        return (
            <>
                <IdleTimer
                    ref={ref => { this.idleTimer = ref }}
                    timeout={idleTimeout}
                    onActive={this.handleOnActive}
                    onIdle={this.handleOnIdle}
                    onAction={this.handleOnAction}
                    debounce={250}
                />
                <Route {...this.props} />
            </>
        );
    }

}

export default SecureRoute;