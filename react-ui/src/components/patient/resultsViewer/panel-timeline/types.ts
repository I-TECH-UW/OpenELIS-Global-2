import { ParsedTimeType } from '../filter/filter-types';
import { ObsRecord } from '../panel-view/types';

export interface TimelineData {
  parsedTimes: ParsedTimeType;
  timelineData: Record<string, Array<ObsRecord>>;
  panelName: string;
}
