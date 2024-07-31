import React from "react";
import {
    Breadcrumb,
    BreadcrumbItem,
    Grid,
    Column,
  } from "@carbon/react";
  import { useIntl } from "react-intl";
  import { Link, useLocation } from 'react-router-dom';

  const GenericHomeBreadCrumb = () => {
    const intl = useIntl();

    
const Breadcrumbs = ({ title, breadcrumbs }) => {
  return (
    <Grid fullWidth={true}>
    <Column lg={16}>
      <Breadcrumb>
        <BreadcrumbItem href="/">

        {breadcrumbs.map((crumb, index) => (
        <span key={index}>
          {index < breadcrumbs.length - 1 ? (
            <Link to={crumb.link}>{crumb.label}</Link>
          ) : (
            <span>{crumb.label}</span>
          )}
          {index < breadcrumbs.length - 1 && '>'}
        </span>
      ))}

        </BreadcrumbItem>
      </Breadcrumb>
    </Column>
   </Grid>
  );
};

const breadcrumbData = [
  { path: '/', title: intl.formatMessage({ id: "home.label" }) },
  { path: '/ResultValidation', title: intl.formatMessage({ id: "sidenav.label.validation" }) }
  // { path: '/validation/:productId', title: ({ match }) => `Product: ${match.params.productId}` },
  
];

const location = useLocation();
const currentPath = location.pathname;
const currentData = breadcrumbData[currentPath];


    return(
      <>
      {currentData && <Breadcrumbs {...currentData} />}
       <Grid fullWidth={true}>
        <Column lg={16}>
          <Breadcrumb>
            <BreadcrumbItem href="/">
              {intl.formatMessage({ id: "home.label" })}
            </BreadcrumbItem>
          </Breadcrumb>
        </Column>
       </Grid>
      </>
    );
  };

  export default GenericHomeBreadCrumb;
