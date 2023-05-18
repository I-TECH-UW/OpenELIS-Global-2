import config from '../../config.json';


export const getFromOpenElisServer = (endPoint, callback) => {
    fetch(config.serverBaseUrl + endPoint,

        {
            //includes the browser sessionId in the Header for Authentication on the backend server
            credentials: "include",
            method: "GET"

        }
    )
        .then(response => response.json()).then(jsonResp => {
            callback(jsonResp);
            //console.log(JSON.stringify(jsonResp))
        }).catch(error => {
            console.log(error)
        })
}

export const postToOpenElisServer = (endPoint, payLoad, callback) => {
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
            console.log("postToOpenElisServer:" + status);
            
            //if (status == 500) console.log("postToOpenElisServer1:" + status);
            //callback(status);
            //console.log(JSON.stringify(jsonResp))
        }).catch(error => {
            console.log(error)
        })
}

//provides Synchronous calls to the api
export const getFromOpeElisServerSync = (endPoint, callback) => {
    const request = new XMLHttpRequest()
    request.open('GET', config.serverBaseUrl + endPoint, false);
    request.setRequestHeader("credentials", "include");
    request.send();
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