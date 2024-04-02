import React from "react";
import ActivityReport from "./ActivityReport";
import PanelSelectForm from "../../workplan/PanelSelectForm";

const ActivityReportByPanel = () => {
  return (
    <ActivityReport
      reportType="panel"
      SelectorComponent={PanelSelectForm}
    />
  );
};

export default ActivityReportByPanel;
