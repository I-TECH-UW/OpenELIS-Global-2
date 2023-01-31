import { Observable } from "rxjs";
import isPlainObject from "lodash-es/isPlainObject";
import { FetchResponse } from "../types";
import {  navigate } from "../../commons"
import panelData from "../../../../data/dummy/panelData.json"
import concept from "../../../../data/dummy/concepts.json"


export const sessionEndpoint = "/ws/rest/v1/session";

export function makeUrl(path: string) {
    return window.openmrsBase + path;
  }

export function openmrsFetch<T = any>(
    path: string,
    fetchInit: FetchConfig = {}
  ): Promise<FetchResponse<T>> {
    
    if (typeof path !== "string") {
      throw Error(
        "The first argument to @openmrs/api's openmrsFetch function must be a url string"
      );
    }
  
    if (typeof fetchInit !== "object") {
      throw Error(
        "The second argument to @openmrs/api's openmrsFetch function must be a plain object."
      );
    }
  
    // @ts-ignore
    // if (!window.openmrsBase) {
    //   throw Error(
    //     "@openmrs/api is running in a browser that doesn't have window.openmrsBase, which is provided by openmrs-module-spa's HTML file."
    //   );
    // }
  
    
    // Prefix the url with the openmrs spa base
    // @ts-ignore
    const url = makeUrl(path);
  
    // We're going to need some headers
    if (!fetchInit.headers) {
      fetchInit.headers = {};
    }
  
    /* Automatically stringify javascript objects being sent in the
     * request body.
     */
    if (isPlainObject(fetchInit.body)) {
      fetchInit.body = JSON.stringify(fetchInit.body);
    }
  
    /* Add a request header to tell the server to respond with json,
     * since frontend code almost always wants json and the OpenMRS
     * server won't give you json unless you explicitly ask for it.
     * If a different Accept header is preferred, pass it into the fetchInit.
     * If no Accept header is desired, pass it in explicitly as null.
     */
    if (typeof fetchInit.headers.Accept === "undefined") {
      fetchInit.headers.Accept = "application/json";
    }
  
    if (fetchInit.headers.Accept === null) {
      delete fetchInit.headers.Accept;
    }
  
    /* This tells the OpenMRS REST API not to return a WWW-Authenticate
     * header. Returning that header is useful when using the API, but
     * not from a UI.
     */
    if (typeof fetchInit.headers["Disable-WWW-Authenticate"] === "undefined") {
      fetchInit.headers["Disable-WWW-Authenticate"] = "true";
    }
  
    /* We capture the stacktrace before making the request, so that if an error occurs we can
     * log a full stacktrace that includes the code that made the request and handled the response
     * Otherwise, we could run into situations where the stacktrace doesn't even show which code
     * called @openmrs/api.
     */
    const requestStacktrace = Error();
    
   // const response  = window.fetch(url, fetchInit as RequestInit).then() as FetchResponse<T>;
   // return response.data;

    return window.fetch("https://cat-fact.herokuapp.com/facts/", fetchInit as RequestInit).then(async (r) => {
      const response = r as FetchResponse<T>;
      // response.data = panelData as T;
      // return response;
      if (response.ok) {
        if (response.status === 204) {
          /* HTTP 204 - No Content
           * We should not try to download the empty response as json. Instead,
           * we return null since there is no response body.
           */
          response.data = null as unknown as T;
          return response;
        } else {
          // HTTP 200s - The request succeeded
          return response.text().then((responseText) => {
            try {
              if (responseText) {
                //response.data = JSON.parse(responseText);
                if(path.includes("/ws/fhir2/R4/Observation")){
                  response.data = panelData as T;
                }else if (path.includes("/ws/rest/v1/concept")){
                  response.data = concept as T;
                }
                
              }
            } catch (err) {
              // Server didn't respond with json
            }
            return response;
          });
        }
      } else {
        /* HTTP response status is not in 200s. Usually this will mean
         * either HTTP 400s (bad request from browser) or HTTP 500s (server error)
         * Our goal is to come up with best possible stacktrace and error message
         * to help developers understand the problem and debug
         */
  
        /*
         *Redirect to given url when redirect on auth failure is enabled
         */
       //const { redirectAuthFailure } = await getConfig("@openmrs/esm-api");
  
        if (
          (url === makeUrl(sessionEndpoint) && response.status === 403) ||
          (true )
        ) {
          navigate({ to: "redirectAuthFailure.url" });
  
          /* We sometimes don't really want this promise to resolve since there's no response data,
           * nor do we want it to reject because that would trigger error handling. We instead
           * want it to remain in pending status while the navigation occurs.
           */
          return "redirectAuthFailure.resolvePromise"
            ? (Promise.resolve() as unknown as Promise<FetchResponse>)
            : new Promise<FetchResponse>(() => {});
        } else {
          // Attempt to download a response body, if it has one
          return response.text().then(
            (responseText) => {
              let responseBody = responseText;
              try {
                responseBody = JSON.parse(responseText);
              } catch (err) {
                // Server didn't respond with json, so just go with the response text string
              }
  
              /* Make the fetch promise go into "rejected" status, with the best
               * possible stacktrace and error message.
               */
              throw new OpenmrsFetchError(
                url,
                response,
                responseBody,
                requestStacktrace
              );
            },
            (err) => {
              /* We weren't able to download a response body for this error.
               * Time to just give the best possible stacktrace and error message.
               */
              throw new OpenmrsFetchError(url, response, null, requestStacktrace);
            }
          );
        }
      }
    });
  }

/**
 * The openmrsObservableFetch function is a wrapper around openmrsFetch
 * that returns an [Observable](https://rxjs-dev.firebaseapp.com/guide/observable)
 * instead of a promise. It exists in case using an Observable is
 * preferred or more convenient than a promise.
 *
 * @param url See [[openmrsFetch]]
 * @param fetchInit See [[openmrsFetch]]
 * @returns An Observable that produces exactly one Response object.
 * The response object is exactly the same as for [[openmrsFetch]].
 *
 * #### Example
 *
 * ```js
 * import { openmrsObservableFetch } from '@openmrs/esm-api'
 * const subscription = openmrsObservableFetch('/ws/rest/v1/session').subscribe(
 *   response => console.log(response.data),
 *   err => {throw err},
 *   () => console.log('finished')
 * )
 * subscription.unsubscribe()
 * ```
 *
 * #### Cancellation
 *
 * To cancel the network request, simply call `subscription.unsubscribe();`
 *
 * @category API
 */



export class OpenmrsFetchError extends Error {
  constructor(
    url: string,
    response: Response,
    responseBody: ResponseBody | null,
    requestStacktrace: Error
  ) {
    super();
    this.message = `Server responded with ${response.status} (${response.statusText}) for url ${url}. Check err.responseBody or network tab in dev tools for more info`;
    requestStacktrace.message = this.message;
    this.responseBody = responseBody;
    this.response = response;
    this.stack = `Stacktrace for outgoing request:\n${requestStacktrace.stack}\nStacktrace for incoming response:\n${this.stack}`;
  }
  response: Response;
  responseBody: string | FetchResponseJson | null;
}

interface FetchConfig extends Omit<Omit<RequestInit, "body">, "headers"> {
  headers?: FetchHeaders;
  body?: FetchBody | string;
}

type ResponseBody = string | FetchResponseJson;

export interface FetchHeaders {
  [key: string]: string | null;
}

interface FetchBody {
  [key: string]: any;
}

export interface FetchResponseJson {
  [key: string]: any;
}
