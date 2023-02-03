import React from 'react';
import { useTranslation } from 'react-i18next';
import { parseTime } from './helpers';
import { ObsRecord } from '../panel-view/types';
import Timeline from './timeline.component';

interface PanelTimelineComponentProps {
  activePanel: ObsRecord;
  groupedObservations: Record<string, Array<ObsRecord>>;
}

const PanelTimelineComponent: React.FC<PanelTimelineComponentProps> = ({ activePanel, groupedObservations }) => {
  const { t } = useTranslation();
  const rows: Array<ObsRecord> = activePanel ? [activePanel, ...activePanel?.relatedObs] : [];
  const mappedObservations = Object.fromEntries(rows.map((obs) => [obs.name, groupedObservations[obs.conceptUuid]]));
  const allTimes = []
    .concat(...Object.values(mappedObservations).map((obsRecords) => obsRecords.map((obs) => obs.effectiveDateTime)))
    .sort((time1, time2) => Date.parse(time2) - Date.parse(time1));

  const parsedTime = parseTime(allTimes);

  const timelineData: Record<string, Array<ObsRecord>> = Object.fromEntries(
    Object.entries(mappedObservations).map(([title, observations]) => [
      title,
      allTimes.map((time) => observations.find((obs) => obs.effectiveDateTime === time)),
    ]),
  );
  if (!activePanel) {
    return <p>{t('panelTimelineInstructions', 'Select a panel to view the timeline')}</p>;
  }

  return (
    <Timeline
      rowData={timelineData}
      parsedTime={parsedTime}
      panelName={activePanel?.name}
      sortedTimes={allTimes}
      testUuid={activePanel.conceptUuid}
    />
  );
};

export default PanelTimelineComponent;
