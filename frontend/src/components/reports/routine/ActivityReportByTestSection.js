import React from 'react';
import ActivityReport from './ActivityReport';
import TestSelectForm from '../../workplan/TestSelectForm';
import TestSectionSelectForm from '../../workplan/TestSectionSelectForm';

const ActivityReportByTestSection = () => {
  return (
    <ActivityReport
      reportType="testSection"
      SelectorComponent={TestSectionSelectForm}
    />
  );
};
export default  ActivityReportByTestSection;
