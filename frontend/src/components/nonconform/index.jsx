import PageBreadCrumb from "../common/PageBreadCrumb";
import { ReportNonConformingEvent } from "./common/ReportNonConformingEvent";

const NonConformIndex = () => {
  return (
    <div>
      <br />
      <PageBreadCrumb breadcrumbs={[{ label: "home.label", link: "/" }]} />
      <div className="orderLegendBody">
        <ReportNonConformingEvent />
      </div>
    </div>
  );
};

export default NonConformIndex;
