import { QueryClient } from "@tanstack/react-query";
import { getFromOpenElisServer } from "./Utils";

/**
 * Reads a GET endpoint through the same helper every screen already uses, so a
 * screen opts into the cache by changing how it calls, not what it calls.
 *
 * getFromOpenElisServer hands its result to a callback and reports failure by
 * passing undefined, which a cache cannot tell apart from an empty body. The
 * query layer needs a rejected promise for that, so an undefined result is
 * treated as a failed read.
 */
export const fetchFromServer = <T>(
  endPoint: string,
  signal?: AbortSignal,
): Promise<T> =>
  new Promise((resolve, reject) => {
    getFromOpenElisServer<T>(
      endPoint,
      (response) => {
        if (response === undefined) {
          reject(new Error(`Request failed: ${endPoint}`));
          return;
        }
        resolve(response);
      },
      signal ?? null,
    );
  });

/**
 * The endpoint is the key, so a screen invalidates exactly what it read and
 * anything sharing a prefix. Keys are arrays for that reason: ["users", url]
 * can be invalidated whole by ["users"].
 */
export const serverQuery = <T>(key: unknown[], endPoint: string) => ({
  queryKey: key,
  queryFn: ({ signal }: { signal?: AbortSignal }) =>
    fetchFromServer<T>(endPoint, signal),
});

/**
 * Refetching is explicit here: a write invalidates what it changed. Retries and
 * focus-refetching stay off so screens behave as they were written to, and a
 * failed read surfaces rather than being retried silently.
 */
export const createQueryClient = (): QueryClient =>
  new QueryClient({
    defaultOptions: {
      queries: {
        retry: false,
        refetchOnWindowFocus: false,
        staleTime: 0,
      },
    },
  });
