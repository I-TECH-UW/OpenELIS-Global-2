import React from "react";
import { injectIntl } from "react-intl";
import HomeDashBoard from "./home/Dashboard.tsx";
import HomeBreadCrumb from "./common/HomeBreadCrumb.js";

let breadcrumbs = [{ label: "home.label", link: "/" }];

function Home() {
  return (
    <>
      <HomeBreadCrumb breadcrumbs={breadcrumbs} />

      <div>
        <HomeDashBoard />
      </div>
    </>
  );
}

export default injectIntl(Home);
