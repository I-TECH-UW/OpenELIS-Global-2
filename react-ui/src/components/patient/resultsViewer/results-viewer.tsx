import React, { useCallback, useContext, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { ContentSwitcher, Switch } from '@carbon/react';
import { EmptyState, ErrorState } from './commons';
import { testResultsBasePath } from './helpers';
import { FilterContext, FilterProvider } from './filter';
import { useGetManyObstreeData } from './grouped-timeline';
import TabletOverlay from './tablet-overlay';
import Trendline from './trendline/trendline.component';
//import styles from './results-viewer.styles.scss';
import  './results-viewer.styles.scss';
import { useParams } from 'react-router-dom';
import TreeViewWrapper from './tree-view';

type viewOpts = 'split' | 'full';
type panelOpts = 'tree' | 'panel';

interface ResultsViewerProps {
  basePath: string;
  patientUuid?: string;
  patientFhirUuid?: string;
  loading?: boolean;
}

const RoutedResultsViewer: React.FC<ResultsViewerProps> = ({ basePath, patientUuid ,patientFhirUuid}) => {


  const { roots, loading, error } = useGetManyObstreeData(patientUuid);
  const { t } = useTranslation();

  if (error) {
    return <ErrorState error={error} headerTitle={t('dataLoadError', 'Data Load Error')} />;
  }

  if (roots?.length) {
    return (
      <FilterProvider roots={loading ? roots : []}>
        <ResultsViewer patientUuid={patientUuid} patientFhirUuid={patientFhirUuid} basePath={basePath} loading={loading} />
      </FilterProvider>
    );
  }

  return (
    <EmptyState
      headerTitle={t('testResults', 'Test Results')}
      displayText={t('testResultsData', 'Test results data')}
    />
  );
};

const ResultsViewer: React.FC<ResultsViewerProps> = ({ patientUuid,patientFhirUuid, basePath, loading }) => {
  const { t } = useTranslation();
  const [view, setView] = useState<viewOpts>('split');
  const { totalResultsCount } = useContext(FilterContext);
  const expanded = view === 'full';
  const { type, testUuid } = useParams();



  return (
    <div className="resultsContainer">
      <div className="resultsHeader">
        <div className="leftSection leftHeaderSection">
          <h4 style={{ flexGrow: 1 }}>{`${t('Results', 'Results')} ${
            totalResultsCount ? `(${totalResultsCount})` : ''
          }`}</h4>
        </div>
      </div>

      <div className="flex"> 
          <TreeViewWrapper
            patientUuid={patientUuid}
            basePath={basePath}
            type={type}
            expanded={expanded}
            testUuid={testUuid}
          />
      </div>
    </div>
  );
};

export default RoutedResultsViewer;