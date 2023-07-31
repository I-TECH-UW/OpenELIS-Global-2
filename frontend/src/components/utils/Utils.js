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
            // if (response.url.includes("LoginPage")) {
            //     throw "No Login Session";
            // }
            const contentType = response.headers.get("content-type");
            if (contentType && contentType.indexOf("application/json") !== -1) {
                return response.json().then(jsonResp => {
                    callback(jsonResp);
                });
            } else {
                callback();
            }
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
    // if (request.response.url.includes("LoginPage")) {
    //     throw "No Login Session";
    // }
    callback(JSON.parse(request.response));
}

export const postToOpenElisServerForPDF = (endPoint, payLoad) => {
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
    .then((response) => response.blob())
    .then((blob) => {   
        let link = document.createElement('a')           
        link.href = window.URL.createObjectURL(blob,{type:'application/pdf'});
        link.target='_blank'
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
    }).catch(error => {
        console.log(error)
    })
}

export const hasRole = (userSessionDetails, role) => {
      return userSessionDetails.roles && userSessionDetails.roles.includes(role)
}