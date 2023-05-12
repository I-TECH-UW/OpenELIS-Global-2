import React, { useCallback, useContext, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { ContentSwitcher, Switch } from '@carbon/react';
import { EmptyState, ErrorState } from './commons';
import { navigate, useConfig, useLayoutType } from './commons';
import { testResultsBasePath } from './helpers';
import { FilterContext, FilterProvider } from './filter';
import { useGetManyObstreeData } from './grouped-timeline';
import TabletOverlay from './tablet-overlay';
import Trendline from './trendline/trendline.component';
//import styles from './results-viewer.styles.scss';
import  './results-viewer.styles.scss';
import { useParams } from 'react-router-dom';
import PanelView from './panel-view';
import TreeViewWrapper from './tree-view';

type viewOpts = 'split' | 'full';
type panelOpts = 'tree' | 'panel';

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
        <ResultsViewer patientUuid={patientUuid} basePath={basePath} loading={loading} />
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
  const tablet = useLayoutType() === 'tablet';
  const [view, setView] = useState<viewOpts>('split');
  const [showTreeOverlay, setShowTreeOverlay] = useState<boolean>(false);
  const [selectedSection, setSelectedSection] = useState<panelOpts>('tree');
  const { totalResultsCount } = useContext(FilterContext);
  const expanded = view === 'full';
  const { type, testUuid } = useParams();
  const trendlineView = testUuid && type === 'trendline';
  const navigateBackFromTrendlineView = useCallback(() => {
    navigate({
      to: testResultsBasePath(`/patient/${patientUuid}/chart`),
    });
  }, [patientUuid]);

  if (tablet) {
    return (
      <div className="resultsContainer">
        <div className="resultsHeader">
          <h4 style={{ flexGrow: 1 }}>{`${t('Results', 'Results')} ${
            totalResultsCount ? `(${totalResultsCount})` : ''
          }`}</h4>
          <div className="leftHeaderActions">
            <ContentSwitcher
              selectedIndex={['panel', 'tree'].indexOf(selectedSection)}
              onChange={(e) => setSelectedSection(e.name as panelOpts)}
            >
              <Switch name="panel" text={t('panel', 'Panel')} />
              <Switch name="tree" text={t('tree', 'Tree')} />
            </ContentSwitcher>
          </div>
        </div>
        {selectedSection === 'tree' ? (
          <TreeViewWrapper
            patientUuid={patientUuid}
            basePath={basePath}
            type={type}
            expanded={expanded}
            testUuid={testUuid}
          />
        ) : selectedSection === 'panel' ? (
          <PanelView
            expanded={expanded}
            patientUuid={patientUuid}
            basePath={basePath}
            type={type}
            testUuid={testUuid}
          />
        ) : null}
        {trendlineView && (
          <TabletOverlay
            headerText={t('trendline', 'Trendline')}
            close={navigateBackFromTrendlineView}
            buttonsGroup={<></>}
          >
            <Trendline patientUuid={patientUuid} conceptUuid={testUuid} basePath={basePath} />
          </TabletOverlay>
        )}
      </div>
    );
  }

  return (
    <div className="resultsContainer">
      <div className="resultsHeader">
        <div className="leftSection leftHeaderSection">
          <h4 style={{ flexGrow: 1 }}>{`${t('Results', 'Results')} ${
            totalResultsCount ? `(${totalResultsCount})` : ''
          }`}</h4>
          <div className="leftHeaderActions">
            {!expanded && (
              <ContentSwitcher
                size={tablet ? 'lg' : 'md'}
                selectedIndex={['panel', 'tree'].indexOf(selectedSection)}
                onChange={(e) => setSelectedSection(e.name as panelOpts)}
              >
                <Switch name="panel" text={t('Panel', 'Panel')} />
                <Switch name="tree" text={t('Tree', 'Tree')} />
              </ContentSwitcher>
            )}
          </div>
        </div>
        <div className="rightSectionHeader">
          <div className="viewOptsContentSwitcherContainer">
            <ContentSwitcher
              size={tablet ? 'lg' : 'md'}
              style={{ maxWidth: '10rem' }}
              onChange={(e) => setView(e.name as viewOpts)}
              selectedIndex={expanded ? 1 : 0}
            >
              <Switch name="split" text={t('split', 'Split')} disabled={loading} />
              <Switch name="full" text={t('full', 'Full')} disabled={loading} />
            </ContentSwitcher>
          </div>
        </div>
      </div>

      <div className="flex">
        {selectedSection === 'tree' ? (
          <TreeViewWrapper
            patientUuid={patientUuid}
            basePath={basePath}
            type={type}
            expanded={expanded}
            testUuid={testUuid}
          />
        ) : selectedSection === 'panel' ? (
          <PanelView
            expanded={expanded}
            patientUuid={patientUuid}
            basePath={basePath}
            type={type}
            testUuid={testUuid}
          />
        ) : null}
      </div>
    </div>
  );
};

export default RoutedResultsViewer;