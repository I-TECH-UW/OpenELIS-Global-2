import React from 'react';
import { useTranslation } from 'react-i18next';
import { Tab, Tabs, TabList } from '@carbon/react';
import styles from './trendline.scss';

const RangeSelector: React.FC<{ setLowerRange: (lowerRange: Date) => void; upperRange: Date }> = ({
  setLowerRange,
  upperRange,
}) => {
  const { t } = useTranslation();
  const ranges: Array<[string, () => void]> = [
    [
      t('trendlineRangeSelector1Day', '1 day'),
      () => setLowerRange(new Date(Date.parse(upperRange.toString()) - 1 * 24 * 3600 * 1000)),
    ],
    [
      t('trendlineRangeSelector5Days', '5 days'),
      () => setLowerRange(new Date(Date.parse(upperRange.toString()) - 5 * 24 * 3600 * 1000)),
    ],
    [
      t('trendlineRangeSelector1Month', '1 month'),
      () => setLowerRange(new Date(Date.parse(upperRange.toString()) - 30 * 24 * 3600 * 1000)),
    ],
    [
      t('trendlineRangeSelectorMonths', '6 months'),
      () => setLowerRange(new Date(Date.parse(upperRange.toString()) - 182 * 24 * 3600 * 1000)),
    ],
    [
      t('trendlineRangeSelector1Year', '1 year'),
      () => setLowerRange(new Date(Date.parse(upperRange.toString()) - 365 * 24 * 3600 * 1000)),
    ],
    [
      t('trendlineRangeSelector5Years', '5 years'),
      () => setLowerRange(new Date(Date.parse(upperRange.toString()) - 5 * 365 * 24 * 3600 * 1000)),
    ],
    [t('trendlineRangeSelectorAll', 'All'), () => setLowerRange(new Date(0))],
  ];

  return (
    <Tabs light selected={6} className={styles['range-tabs']}>
      <TabList aria-label="Trendline range tabs">
        {ranges.map(([label, onClick], index) => (
          <Tab onClick={onClick} key={index}>
            {label}
          </Tab>
        ))}
      </TabList>
    </Tabs>
  );
};

export default React.memo(RangeSelector);
