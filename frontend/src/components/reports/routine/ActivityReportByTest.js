import React from 'react';
import ActivityReport from './ActivityReport';
import TestSelectForm from '../../workplan/TestSelectForm';

const ActivityReportByTest = () => {
  return (
    <ActivityReport
      reportType="test"
      SelectorComponent={TestSelectForm}
    />
  );
};

export default ActivityReportByTest;
