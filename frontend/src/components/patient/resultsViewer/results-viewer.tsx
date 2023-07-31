import React, { useContext,} from 'react';
import { useTranslation } from 'react-i18next';
import { EmptyState, ErrorState } from './commons';
import { FilterContext, FilterProvider } from './filter';
import { useGetManyObstreeData } from './grouped-timeline';
import  './results-viewer.styles.scss';
import { useParams } from 'react-router-dom';
import TreeViewWrapper from './tree-view';


interface ResultsViewerProps {
  basePath: string;
  patientUuid?: string;
  loading?: boolean;
}

const RoutedResultsViewer: React.FC<ResultsViewerProps> = ({ basePath, patientUuid }) => {

  const { roots, loading, error } = useGetManyObstreeData(patientUuid);
  const { t } = useTranslation();

  if (error) {
    return <ErrorState error={error} headerTitle={t('dataLoadError', 'Data Load Error')} />;
  }

  if (roots?.length) {
    return (
      <FilterProvider roots={loading ? roots : []}>
        <ResultsViewer patientUuid={patientUuid}  basePath={basePath} loading={loading} />
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

const ResultsViewer: React.FC<ResultsViewerProps> = ({ patientUuid, basePath, loading }) => {
  const { t } = useTranslation();
  const { totalResultsCount } = useContext(FilterContext);
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
            expanded={true}
            testUuid={testUuid}
          />
      </div>
    </div>
  );
};

export default RoutedResultsViewer;