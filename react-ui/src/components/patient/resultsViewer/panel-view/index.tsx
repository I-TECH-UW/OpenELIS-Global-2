import React, { useCallback, useEffect, useMemo, useState } from 'react';
import LabSetPanel from './panel.component';
import usePanelData from './usePanelData';
import { DataTableSkeleton, Button, Search, Form } from '@carbon/react';
import { Search as SearchIcon, Close } from '@carbon/react/icons';
//import styles from './panel-view.scss';
import  './panel-view.scss';
import { navigate, useLayoutType } from '../commons';
import PanelTimelineComponent from '../panel-timeline';
import { ObsRecord } from './types';
import { useTranslation } from 'react-i18next';
import { EmptyState } from '../commons';
import Trendline from '../trendline/trendline.component';
import Overlay from '../tablet-overlay/tablet-overlay.component';
import { testResultsBasePath } from '../helpers';
import { FilterEmptyState } from '../ui-elements/resetFiltersEmptyState';

interface PanelViewProps {
  expanded: boolean;
  testUuid: string;
  type: string;
  basePath: string;
  patientUuid: string;
}

const PanelView: React.FC<PanelViewProps> = ({ expanded, testUuid, basePath, type, patientUuid }) => {
  const layout = useLayoutType();
  const isTablet = layout === 'tablet';
  const { panels, isLoading, groupedObservations } = usePanelData();
  const [activePanel, setActivePanel] = useState<ObsRecord>(null);
  const { t } = useTranslation();
  const trendlineView = testUuid && type === 'trendline';
  const [searchTerm, setSearchTerm] = useState('');

  const filteredPanels = useMemo(() => {
    if (!searchTerm) {
      return panels;
    }
    return panels?.filter(
      (panel) =>
        panel.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        panel.relatedObs.some((ob) => ob.name.toLowerCase().includes(searchTerm.toLowerCase())),
    );
  }, [panels, searchTerm]);

  useEffect(() => {
    // Selecting the active panel should not occur in small-desktop
    if (layout !== 'tablet' && filteredPanels) {
      setActivePanel(filteredPanels?.[0]);
    }
  }, [filteredPanels, layout]);

  const navigateBackFromTrendlineView = useCallback(() => {
    navigate({
      to: testResultsBasePath(`/patient/${patientUuid}/chart`),
    });
  }, [patientUuid]);

  if (isTablet) {
    return (
      <>
        <div>
          <PanelViewHeader
            isTablet={isTablet}
            setSearchTerm={setSearchTerm}
            searchTerm={searchTerm}
            totalSearchResults={filteredPanels?.length ?? 0}
          />
          {!isLoading ? (
            panels.length > 0 ? (
              filteredPanels.length ? (
                filteredPanels.map((panel) => (
                  <LabSetPanel
                    panel={panel}
                    observations={[panel, ...panel.relatedObs]}
                    setActivePanel={setActivePanel}
                    activePanel={activePanel}
                  />
                ))
              ) : (
                <FilterEmptyState clearFilter={() => setSearchTerm('')} />
              )
            ) : (
              <EmptyState displayText={t('Panels', 'panels')} headerTitle={t('noPanelsFound', 'No panels found')} />
            )
          ) : (
            <DataTableSkeleton columns={3} />
          )}
        </div>
        {activePanel ? (
          <Overlay close={() => setActivePanel(null)} headerText={activePanel?.name}>
            <PanelTimelineComponent groupedObservations={groupedObservations} activePanel={activePanel} />
          </Overlay>
        ) : null}
        {trendlineView ? (
          <Overlay close={navigateBackFromTrendlineView} headerText={activePanel?.name}>
            <Trendline patientUuid={patientUuid} conceptUuid={testUuid} basePath={basePath} />
          </Overlay>
        ) : null}
      </>
    );
  }

  return (
    <>
      <div className="leftSection"  >
        <>
          <PanelViewHeader
            isTablet={isTablet}
            setSearchTerm={setSearchTerm}
            searchTerm={searchTerm}
            totalSearchResults={filteredPanels?.length ?? 0}
          />
          {!isLoading ? (
            panels.length > 0 ? (
              filteredPanels.length ? (
                filteredPanels.map((panel ,index) => (
                  <LabSetPanel
                   key={index}
                    panel={panel}
                    observations={[panel, ...panel.relatedObs]}
                    setActivePanel={setActivePanel}
                    activePanel={activePanel}
                  />
                ))
              ) : (
                <FilterEmptyState clearFilter={() => setSearchTerm('')} />
              )
            ) : (
              <EmptyState displayText={t('panels', 'panels')} headerTitle={t('noPanelsFound', 'No panels found')} />
            )
          ) : (
            <DataTableSkeleton columns={3} />
          )}
        </>
      </div>
      <div className="headerMargin rightSection" >
        <div className="stickySection">
          {isLoading ? (
            <DataTableSkeleton columns={3} />
          ) : window.location.href.endsWith('#trendline') ? (
            <Trendline patientUuid={patientUuid} conceptUuid={testUuid} basePath={basePath} showBackToTimelineButton />
          ) : activePanel ? (
            <PanelTimelineComponent groupedObservations={groupedObservations} activePanel={activePanel} />
          ) : null}
        </div>
      </div>
    </>
  );
};

interface PanelViewHeaderProps {
  isTablet: boolean;
  searchTerm: string;
  setSearchTerm: React.Dispatch<React.SetStateAction<string>>;
  totalSearchResults: number;
}

const PanelViewHeader: React.FC<PanelViewHeaderProps> = ({
  isTablet,
  searchTerm,
  setSearchTerm,
  totalSearchResults,
}) => {
  const { t } = useTranslation();
  const [showSearchFields, setShowSearchFields] = useState(false);
  const [localSearchTerm, setLocalSearchTerm] = useState(searchTerm);

  const handleToggleSearchFields = useCallback(() => {
    setShowSearchFields((prev) => !prev);
  }, [setShowSearchFields]);

  const handleSearchTerm = () => {
    setSearchTerm(localSearchTerm);
    handleToggleSearchFields();
  };

  const handleClear = useCallback(() => {
    setSearchTerm('');
    setLocalSearchTerm('');
  }, [setSearchTerm, setLocalSearchTerm]);

  return (
    <div className="panelViewHeader">
      {!showSearchFields ? (
        <>
          <div className="flex">
            <h4 className="productiveHeading02">
              {!searchTerm
                ? t('Panel', 'Panel')
                : `${totalSearchResults} ${t('searchResultsTextFor', 'search results for')} "${searchTerm}"`}
            </h4>
            {searchTerm ? (
              <Button kind="ghost" size={isTablet ? 'md' : 'sm'} onClick={handleClear}>
                {t('clear', 'Clear')}
              </Button>
            ) : null}
          </div>
          <Button kind="ghost" size={isTablet ? 'md' : 'sm'} renderIcon={SearchIcon} onClick={handleToggleSearchFields}>
            {t('search', 'Search')}
          </Button>
        </>
      ) : !isTablet ? (
        <>
          <Form onSubmit={handleSearchTerm} className="flex">
            <Search
              size="sm"
              value={localSearchTerm}
              onChange={(e) => setLocalSearchTerm(e.target.value)}
              placeholder={t('searchByTestName', 'Search by test name')}
              autoFocus={true}
            />
            <Button kind="secondary" size="sm" onClick={handleSearchTerm}>
              {t('search', 'Search')}
            </Button>
          </Form>
          <Button
            hasIconOnly
            renderIcon={Close}
            iconDescription={t('closeSearchBar', 'Close search')}
            onClick={handleToggleSearchFields}
            size="sm"
            kind="ghost"
          />
        </>
      ) : (
        <>
          <Overlay close={handleToggleSearchFields} headerText={t('search', 'Search')}>
            <Form onSubmit={handleSearchTerm} className="flex tabletSearch">
              <Search
                value={localSearchTerm}
                onChange={(e) => setLocalSearchTerm(e.target.value)}
                placeholder={t('searchByTestName', 'Search by test name')}
                autoFocus={true}
                size="lg"
              />
              <Button kind="secondary" onClick={handleSearchTerm}>
                {t('search', 'Search')}
              </Button>
            </Form>
          </Overlay>
        </>
      )}
    </div>
  );
};

export default PanelView;
