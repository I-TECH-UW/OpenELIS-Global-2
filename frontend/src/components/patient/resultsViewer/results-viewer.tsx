import React, { useContext, useState, useEffect, useRef } from "react";
import {
  Heading,
  Grid,
  Column,
  Section,
  Loading,
  Breadcrumb,
  BreadcrumbItem,
  Tag,
} from "@carbon/react";
import { useTranslation } from "react-i18next";
import { EmptyState, ErrorState } from "./commons";
import { FilterContext, FilterProvider } from "./filter";
import { useGetManyObstreeData } from "./grouped-timeline";
import "./results-viewer.styles.scss";
import { useParams } from "react-router-dom";
import TreeViewWrapper from "./tree-view";
import { FormattedMessage, injectIntl, useIntl } from "react-intl";
import config from "../../../config.json";
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
    nationalId: "",
  };

  const { patientId } = useParams();
  const [patient, setPatient] = useState(patientObj);

  const componentMounted = useRef(false);

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer(
      "/rest/patient-details?patientID=" + patientId,
      loadPatient,
    );
    return () => {
      componentMounted.current = false;
    };
  }, [patientId]);

  const loadPatient = (patient) => {
    if (componentMounted.current) {
      setPatient(patient);
    }
  };
  const intl = useIntl();

  const { roots, loading, error } = useGetManyObstreeData(patientId);

  const { t } = useTranslation();

  if (error) {
    return (
      <ErrorState
        error={error}
        headerTitle={t("dataLoadError", "Data Load Error")}
      />
    );
  }

  if (loading) {
    return (
      <>
        <Loading></Loading>
        <Grid fullWidth={true}>
          <Column lg={16}>
            <EmptyState
              headerTitle={t("testResults", "Test Results")}
              displayText={t("testResultsData", "Test results data")}
            />
          </Column>
        </Grid>
      </>
    );
  }

  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Breadcrumb>
            <BreadcrumbItem href="/">
              {intl.formatMessage({ id: "home.label" })}
            </BreadcrumbItem>
            <BreadcrumbItem href="/PatientHistory">
              {intl.formatMessage({ id: "label.search.patient" })}
            </BreadcrumbItem>
          </Breadcrumb>
        </Column>
      </Grid>

      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              {patient ? (
                <div className="patient-header">
                  <div className="patient-name">
                    <Tag type="blue">
                      <FormattedMessage id="patient.label.name" /> :
                    </Tag>
                    {patient.lastName} {patient.firstName}
                  </div>
                  <div className="patient-dob">
                    {" "}
                    <Tag type="blue">
                      <FormattedMessage id="patient.label.sex" /> :
                    </Tag>
                    {patient.gender === "M" ? (
                      <FormattedMessage id="patient.male" />
                    ) : (
                      <FormattedMessage id="patient.female" />
                    )}{" "}
                    <Tag type="blue">
                      <FormattedMessage id="patient.dob" /> :
                    </Tag>{" "}
                    {patient.birthDateForDisplay}
                  </div>
                  <div className="patient-id">
                    <Tag type="blue">
                      <FormattedMessage id="patient.subject.number" /> :
                    </Tag>
                    {patient.subjectNumber}{" "}
                  </div>
                  <div className="patient-id">
                    <Tag type="blue">
                      <FormattedMessage id="patient.natioanalid" /> :
                    </Tag>
                    {patient.nationalId}
                  </div>
                </div>
              ) : (
                <div className="patient-header">
                  <div className="patient-name">
                    {" "}
                    <FormattedMessage id="patient.label.nopatientid" />{" "}
                  </div>
                </div>
              )}
            </Section>
          </Section>
        </Column>
      </Grid>
      {roots?.length ? (
        <Grid fullWidth={true}>
          <Column lg={16}>
            <div className="orderLegendBody">
              <FilterProvider roots={loading ? roots : []}>
                <ResultsViewer
                  patientId={patientId}
                  basePath={config.serverBaseUrl}
                  loading={loading}
                />
              </FilterProvider>
            </div>
          </Column>
        </Grid>
      ) : (
        <Grid fullWidth={true}>
          <Column lg={16}>
            <div className="orderLegendBody">
              <EmptyState
                headerTitle={t("testResults", "Test Results")}
                displayText={t("testResultsData", "Test results data")}
              />
            </div>
          </Column>
        </Grid>
      )}
    </>
  );
};

const ResultsViewer: React.FC<ResultsViewerProps> = ({
  patientId,
  basePath,
}) => {
  const { t } = useTranslation();
  const { totalResultsCount } = useContext(FilterContext);
  const { type, testUuid } = useParams();
  return (
    <div className="resultsContainer">
      <div className="resultsHeader">
        <div className="leftSection leftHeaderSection">
          <h4 style={{ flexGrow: 1 }}>{`${t("Results", "Results")} ${
            totalResultsCount ? `(${totalResultsCount})` : ""
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
