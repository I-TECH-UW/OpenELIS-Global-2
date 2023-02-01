import React from 'react';
import { PaddingContainer, TimeSlots, Grid, RowStartCell, GridItems, ShadowBox } from './helpers';
import { EmptyState } from '../commons';
import useScrollIndicator from './useScroll';
//import styles from './timeline.scss';
import  './timeline.scss';
import { ParsedTimeType } from '../filter/filter-types';
import { ObsRecord } from '../panel-view/types';

const RecentResultsGrid = (props) => {
  return <div {...props} className='recent-results-grid' />;
};

interface PanelNameCornerProps {
  showShadow: boolean;
  panelName: string;
}

const PanelNameCorner: React.FC<PanelNameCornerProps> = ({ showShadow, panelName }) => (
  <TimeSlots className="corner-grid-element">
    {panelName}
  </TimeSlots>
);

interface DateHeaderGridProps {
  timeColumns: Array<string>;
  yearColumns: Array<Record<string, number | string>>;
  dayColumns: Array<Record<string, number | string>>;
  showShadow: boolean;
}

const DateHeaderGrid: React.FC<DateHeaderGridProps> = ({ timeColumns, yearColumns, dayColumns, showShadow }) => (
  <Grid
    dataColumns={timeColumns.length}
    style={{
      gridTemplateRows: 'repeat(3, 24px)',
      position: 'sticky',
      top: '0px',
      zIndex: 2,
      boxShadow: showShadow ? '8px 0 20px 0 rgba(0,0,0,0.15)' : undefined,
    }}
  >
    {yearColumns.map(({ year, size }) => {
      return (
        <TimeSlots key={year} className='year-column' style={{ gridColumn: `${size} span` }}>
          {year}
        </TimeSlots>
      );
    })}
    {dayColumns.map(({ day, year, size }) => {
      return (
        <TimeSlots key={`${day} - ${year}`} className='day-column' style={{ gridColumn: `${size} span` }}>
          {day}
        </TimeSlots>
      );
    })}
    {timeColumns.map((time, i) => {
      return (
        <TimeSlots key={time + i} className='time-column'>
          {time}
        </TimeSlots>
      );
    })}
  </Grid>
);

interface DataRowsProps {
  rowData: Record<string, Array<ObsRecord>>;
  timeColumns: Array<string>;
  sortedTimes: Array<string>;
  showShadow: boolean;
  testUuid: string;
}

const DataRows: React.FC<DataRowsProps> = ({ timeColumns, rowData, sortedTimes, showShadow, testUuid }) => (
  <Grid dataColumns={timeColumns.length} padding style={{ gridColumn: 'span 2' }}>
    {Object.entries(rowData).map(([title, obs], rowCount) => {
      const { meta, conceptUuid } = obs.find((x) => !!x);
      const range = meta?.range ?? '';
      const units = meta?.units ?? '';
      return (
        <React.Fragment key={conceptUuid}>
          <RowStartCell
            {...{
              units,
              range,
              title,
              shadow: showShadow,
              testUuid,
              isString: isNaN(parseFloat(obs?.[0]?.value)),
            }}
          />
          <GridItems {...{ sortedTimes, obs, zebra: !!(rowCount % 2) }} />
        </React.Fragment>
      );
    })}
  </Grid>
);

interface TimelineParams {
  parsedTime: ParsedTimeType;
  rowData: Record<string, Array<ObsRecord>>;
  panelName: string;
  sortedTimes: Array<string>;
  testUuid: string;
}

export const Timeline: React.FC<TimelineParams> = ({ parsedTime, rowData, panelName, sortedTimes, testUuid }) => {
  const [xIsScrolled, yIsScrolled, containerRef] = useScrollIndicator(0, 32);
  const { yearColumns, dayColumns, timeColumns } = parsedTime;

  if (yearColumns && dayColumns && timeColumns)
    return (
      <div className="timelineHeader" style={{ top: '6.5rem' }}>
       <div className="timelineHeader" style={{ top: '6.5rem' }}>
        <div className="dateHeaderContainer">
          <PanelNameCorner showShadow={xIsScrolled} panelName={panelName} />
          <DateHeaderGrid
            {...{
              timeColumns,
              yearColumns,
              dayColumns,
              showShadow: yIsScrolled,
            }}
          />
          <DataRows
            {...{
              timeColumns,
              rowData,
              sortedTimes,
              showShadow: xIsScrolled,
              panelUuid: '',
              testUuid,
            }}
          />
          <ShadowBox />
        </div>
        </div>
        </div>
    );
  return <EmptyState displayText={'timeline data'} headerTitle="Data Timeline" />;
};

export default Timeline;
