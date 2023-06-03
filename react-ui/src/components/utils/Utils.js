import config from '../../config.json';

export const getFromOpenElisServer = (endPoint, callback) => {
    fetch(config.serverBaseUrl + endPoint,

        {
            //includes the browser sessionId in the Header for Authentication on the backend server
            credentials: "include",
            method: "GET"

        }
    )
        .then(response => {
            console.log("checking response");
            if (response.url.includes("LoginPage")) {
                throw "No Login Session";
            }
            return response.json();
        }).then(jsonResp => {
            callback(jsonResp);
            //console.log(JSON.stringify(jsonResp))
        }).catch(error => {
            console.log(error)

        })
}

export const postToOpenElisServer = (endPoint, payLoad, callback, extraParams) => {
    fetch(config.serverBaseUrl + endPoint,

        {
            //includes the browser sessionId in the Header for Authentication on the backend server
            credentials: "include",
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                "X-CSRF-Token": localStorage.getItem("CSRF")
            },
            body: payLoad
        }
    )
        .then(response => response.status).then(status => {
            
            callback(status, extraParams);
            //console.log(JSON.stringify(jsonResp))
        }).catch(error => {
            console.log(error)
        })
}

export const postToOpenElisServerFullResponse = (endPoint, payLoad, callback, extraParams) => {
    fetch(config.serverBaseUrl + endPoint,

        {
            //includes the browser sessionId in the Header for Authentication on the backend server
            credentials: "include",
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                "X-CSRF-Token": localStorage.getItem("CSRF")
            },
            body: payLoad
        }
    )
        .then( response =>
            callback(response, extraParams)
        ).catch(error => {
            console.log(error)
        })
}

//provides Synchronous calls to the api
export const getFromOpeElisServerSync = (endPoint, callback) => {
    const request = new XMLHttpRequest()
    request.open('GET', config.serverBaseUrl + endPoint, false);
    request.setRequestHeader("credentials", "include");
    request.send();
    if (request.response.url.includes("LoginPage")) {
        throw "No Login Session";
    }
    callback(JSON.parse(request.response));
}
