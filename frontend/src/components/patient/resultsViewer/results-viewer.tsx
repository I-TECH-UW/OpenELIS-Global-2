import React, { useContext, useState, useEffect, useRef } from 'react';
import { Heading, Grid, Column, Section, Loading, Breadcrumb, BreadcrumbItem, Tag} from '@carbon/react';
import { useTranslation } from 'react-i18next';
import { EmptyState, ErrorState } from './commons';
import { FilterContext, FilterProvider } from './filter';
import { useGetManyObstreeData } from './grouped-timeline';
import './results-viewer.styles.scss';
import { useParams } from 'react-router-dom';
import TreeViewWrapper from './tree-view';
import { FormattedMessage, injectIntl } from 'react-intl'
import config from '../../../config.json';
import { getFromOpenElisServer } from "../../utils/Utils";


interface ResultsViewerProps {
  basePath: string;
  patientId?: string;
  loading?: boolean;
}

interface Patient {
  firstName: string;
  lastName: string;
  gender: string;
  birthDateForDisplay: string;
  subjectNumber: string;
  nationalId: string;
}
const RoutedResultsViewer: React.FC<ResultsViewerProps> = () => {
  const patientObj: Patient = {
    firstName: "",
    lastName: "",
    gender: "",
    birthDateForDisplay: "",
    subjectNumber: "",
    nationalId: ""
  }

  const { patientId } = useParams();
  const [patient, setPatient] = useState(patientObj);

  const componentMounted = useRef(false);

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer('/rest/patient-details?patientID=' + patientId, loadPatient);
    return () => {
      componentMounted.current = false;
    }
  }, [patientId]);

  const loadPatient = (patient) => {
    if (componentMounted.current) {
      setPatient(patient);
    }
  }


  const { roots, loading, error } = useGetManyObstreeData(patientId);

  const { t } = useTranslation();

  if (error) {
    return <ErrorState error={error} headerTitle={t('dataLoadError', 'Data Load Error')} />;
  }

  if (loading) {
    return (<>
      <Loading></Loading>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <EmptyState
            headerTitle={t('testResults', 'Test Results')}
            displayText={t('testResultsData', 'Test results data')}
          />
        </Column>
      </Grid>)
    </>
    );
  }

  return (
    <>

      <Breadcrumb>
        <BreadcrumbItem href="/">Home</BreadcrumbItem>
        <BreadcrumbItem href="/PatientHistory">Search Patient</BreadcrumbItem>
      </Breadcrumb>

      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              {patient ? (<div className="patient-header">
                <div className="patient-name"><Tag type="blue">Name :</Tag>{patient.lastName} {patient.firstName}</div>
                <div className="patient-dob"> <Tag type="blue">Gender :</Tag>{patient.gender === 'M'?"Male" : "Female" } <Tag type="blue">DOB :</Tag> {patient.birthDateForDisplay}</div>
                 <div className="patient-id"><Tag type="blue">Unique Health ID number :</Tag>{patient.subjectNumber}  </div>
                 <div className="patient-id"><Tag type="blue">National ID :</Tag>{patient.nationalId}</div>
                </div>) : (<div className="patient-header">
                <div className="patient-name">Patient Id Doest Exist</div>
              </div>)}
            </Section>
          </Section>
        </Column>
      </Grid>
      {roots?.length ? (
        <Grid fullWidth={true}>
          <Column lg={16}>
            <div className="orderLegendBody">
            <FilterProvider roots={loading ? roots : []}>
              <ResultsViewer patientId={patientId} basePath={config.serverBaseUrl} loading={loading} />
            </FilterProvider>
            </div>
          </Column>
        </Grid>
      ) : (<Grid fullWidth={true}>
        <Column lg={16}>
          <EmptyState
            headerTitle={t('testResults', 'Test Results')}
            displayText={t('testResultsData', 'Test results data')}
          />
        </Column>
      </Grid>)}
    </>

  );
};

const ResultsViewer: React.FC<ResultsViewerProps> = ({ patientId, basePath, loading }) => {
  const { t } = useTranslation();
  const { totalResultsCount } = useContext(FilterContext);
  const { type, testUuid } = useParams();
  return (
    <div className="resultsContainer">
      <div className="resultsHeader">
        <div className="leftSection leftHeaderSection">
          <h4 style={{ flexGrow: 1 }}>{`${t('Results', 'Results')} ${totalResultsCount ? `(${totalResultsCount})` : ''
            }`}</h4>
        </div>
      </div>

      <div className="flex">
        <TreeViewWrapper
          patientUuid={patientId}
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