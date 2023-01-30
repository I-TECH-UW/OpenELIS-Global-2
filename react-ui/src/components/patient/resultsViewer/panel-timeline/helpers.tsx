import * as React from 'react';
import { OBSERVATION_INTERPRETATION } from '../commons';
import { ObsRecord } from '../panel-view/types';
import styles from './timeline.scss';
import { ConfigurableLink, formatDate, formatTime, parseDate, usePatient } from '../commons';
import { ParsedTimeType } from '../filter/filter-types';
import { testResultsBasePath } from '../helpers';

function getPatientUuidFromUrl(): string {
  const match = /\/patient\/([a-zA-Z0-9\-]+)\/?/.exec("location");
  return match && match[1];
}

const patientUuid = getPatientUuidFromUrl();

export const parseTime: (sortedTimes: Array<string>) => ParsedTimeType = (sortedTimes) => {
  const yearColumns: Array<{ year: string; size: number }> = [],
    dayColumns: Array<{ year: string; day: string; size: number }> = [],
    timeColumns: string[] = [];

  sortedTimes.forEach((datetime) => {
    const parsedDate = parseDate(datetime);
    const year = parsedDate.getFullYear().toString();
    const date = formatDate(parsedDate, { mode: 'wide', year: false });
    const time = formatTime(parsedDate);

    const yearColumn = yearColumns.find(({ year: innerYear }) => year === innerYear);
    if (yearColumn) yearColumn.size++;
    else yearColumns.push({ year, size: 1 });

    const dayColumn = dayColumns.find(({ year: innerYear, day: innerDay }) => date === innerDay && year === innerYear);
    if (dayColumn) dayColumn.size++;
    else dayColumns.push({ day: date, year, size: 1 });

    timeColumns.push(time);
  });

  return { yearColumns, dayColumns, timeColumns, sortedTimes };
};

export const Grid: React.FC<{
  children?: React.ReactNode;
  style: React.CSSProperties;
  padding?: boolean;
  dataColumns: number;
}> = ({ dataColumns, style = {}, padding = false, ...props }) => (
  <div
    style={{
      ...style,
      gridTemplateColumns: `${padding ? '9em ' : ''} repeat(${dataColumns}, 5em)`,
      margin: '1px',
    }}
    className={styles['grid']}
    {...props}
  />
);

export const PaddingContainer = React.forwardRef<HTMLElement, any>((props, ref) => (
  <div ref={ref} className={styles['padding-container']} {...props} />
));

const TimeSlotsInner: React.FC<{
  style?: React.CSSProperties;
  className?: string;
}> = ({ className, ...props }) => (
  <div className={styles['time-slot-inner'] + (className ? ' ' + className : '')} {...props} />
);

export const Main: React.FC = () => <main className={styles['padded-main']} />;

export const ShadowBox: React.FC = () => <div className={styles['shadow-box']} />;

export const TimelineCell: React.FC<{
  text: string;
  interpretation?: OBSERVATION_INTERPRETATION;
  zebra: boolean;
}> = ({ text, interpretation = 'NORMAL', zebra }) => {
  let additionalClassname: string;

  switch (interpretation) {
    case 'OFF_SCALE_HIGH':
      additionalClassname = styles['off-scale-high'];
      break;

    case 'CRITICALLY_HIGH':
      additionalClassname = styles['critically-high'];
      break;

    case 'HIGH':
      additionalClassname = styles['high'];
      break;

    case 'OFF_SCALE_LOW':
      additionalClassname = styles['off-scale-low'];
      break;

    case 'CRITICALLY_LOW':
      additionalClassname = styles['critically-low'];
      break;

    case 'LOW':
      additionalClassname = styles['low'];
      break;

    case 'NORMAL':
    default:
      additionalClassname = '';
  }

  return (
    <div
      className={`${styles['timeline-data-cell']} ${zebra ? styles['timeline-cell-zebra'] : ''} ${additionalClassname}`}
    >
      <p>{text}</p>
    </div>
  );
};

export const RowStartCell = ({ title, range, units, shadow = false, testUuid, isString = false }) => {
  return (
    <div
      className={styles['row-start-cell']}
      style={{
        boxShadow: shadow ? '8px 0 20px 0 rgba(0,0,0,0.15)' : undefined,
      }}
    >
      <span className={styles['trendline-link']}>
        {!isString ? (
          <ConfigurableLink to={`${testResultsBasePath(`/patient/${patientUuid}/chart`)}/trendline/${testUuid}`}>
            {title}
          </ConfigurableLink>
        ) : (
          title
        )}
      </span>
      <span className={styles['range-units']}>
        {range} {units}
      </span>
    </div>
  );
};

export const TimeSlots: React.FC<{
  children?: React.ReactNode;
  style?: React.CSSProperties;
  className?: string;
}> = ({ children = undefined, ...props }) => (
  <TimeSlotsInner {...props}>
    <span>{children}</span>
  </TimeSlotsInner>
);

export const GridItems = React.memo<{
  sortedTimes: Array<string>;
  obs: Array<ObsRecord>;
  zebra: boolean;
}>(({ sortedTimes, obs, zebra }) => (
  <>
    {sortedTimes.map((_, i) => {
      if (!obs[i]) return <TimelineCell key={i} text={'--'} zebra={zebra} />;
      const interpretation = obs[i].interpretation;
      return <TimelineCell key={i} text={`${obs[i].value}`} interpretation={interpretation} zebra={zebra} />;
    })}
  </>
));
