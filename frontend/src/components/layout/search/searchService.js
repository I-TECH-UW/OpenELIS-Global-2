
import { getFromOpenElisServer } from "../../utils/Utils";

export const fetchMenuData = (callback) => {
  getFromOpenElisServer("/rest/menu", callback);
};

export const flattenMenuItems = (menuItems) => {
  return menuItems.reduce((acc, item) => {
    acc.push(item);
    if (item.childMenus && item.childMenus.length > 0) {
      acc = acc.concat(flattenMenuItems(item.childMenus));
    }
    return acc;
  }, []);
};

export const filterMenuItems = (menuItems, query) => {
  if (!query) return menuItems;

  const lowerCaseQuery = query.toLowerCase();

  return flattenMenuItems(menuItems).filter((item) => {
    return (
      item.menu.displayKey.toLowerCase().includes(lowerCaseQuery) ||
      item.menu.toolTipKey.toLowerCase().includes(lowerCaseQuery)
    );
  });
};

export const fetchPatientDetails = (patientID, callback) => {
  const endpoint = `/rest/patient-details?patientID=${patientID}`;
  let isMounted = true;

  getFromOpenElisServer(endpoint, (response) => {
    if (isMounted) {
      if (response) {
        callback([response]); // Wrap the response in an array for consistency
      } else {
        callback([]);
      }
    }
  });

  return () => {
    isMounted = false;
  };
};

export const fetchPatientData = (query, callback) => {
  const [firstName, lastName,guid] = query.split(" ");
  const endpoints = [
    `/rest/patient-search-results?guid=${guid}`,
    `/rest/patient-search-results?firstName=${firstName}`,
    `/rest/patient-search-results?lastName=${lastName || query}`,
    `/rest/patient-search-results?gender=${query}`,
    lastName ? `/rest/patient-search-results?firstName=${firstName}&lastName=${lastName}` : null
  ].filter(Boolean);

  let isMounted = true;

  Promise.all(endpoints.map(endpoint =>
    new Promise((resolve) => {
      getFromOpenElisServer(endpoint, (response) => {
        if (response && response.patientSearchResults) {
          resolve(response.patientSearchResults);
        } else {
          resolve([]);
        }
      });
    })
  )).then(results => {
    if (isMounted) {
      const combinedResults = [].concat(...results);
      const uniqueResults = combinedResults.filter((value, index, self) =>
        index === self.findIndex((t) => (
          t.patientID === value.patientID
        ))
      );
      callback(uniqueResults);
    }
  });

  return () => {
    isMounted = false;
  };
};


 