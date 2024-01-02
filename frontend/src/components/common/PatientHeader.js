import React from "react";
import { Grid, Column, Section, Tag } from "@carbon/react";
import { FormattedMessage } from "react-intl";
import Avatar from "react-avatar";
import GeoPattern from "geopattern";

const PatientHeader = (props) => {
  const {
    id,
    lastName,
    firstName,
    gender,
    dob,
    patientName = null,
    subjectNumber = null,
    nationalId,
    accesionNumber = null,
    isOrderPage = false,
  } = props;
  const patternUrl = GeoPattern.generate(id).toDataUri();
  return (
    <Grid fullWidth={true}>
      <Column lg={16}>
        <Section>
          <Section>
            {id ? (
              <div className="patient-header">
                <Grid>
                  <Column lg={5}>
                    <div className="patientAvatar" role="img">
                      <Avatar
                        alt={"Patient avatar"}
                        color="rgba(0,0,0,0)"
                        name={
                          patientName ? patientName : lastName + " " + firstName
                        }
                        src={""}
                        size={"120"}
                        textSizeRatio={2}
                        style={{
                          backgroundImage: `url(${patternUrl})`,
                          backgroundRepeat: "round",
                        }}
                      />
                    </div>
                  </Column>
                  <Column lg={10}>
                    <div className="patient-name">
                      {patientName ? patientName : lastName + " " + firstName}
                    </div>
                    <div className="patient-dob">
                      {" "}
                      <Tag type="blue">
                        <FormattedMessage id="patient.label.sex" /> :
                      </Tag>
                      {gender === "M" ? (
                        <FormattedMessage id="patient.male" />
                      ) : (
                        <FormattedMessage id="patient.female" />
                      )}{" "}
                      <Tag type="blue">
                        <FormattedMessage id="patient.dob" /> :
                      </Tag>{" "}
                      {dob}
                    </div>
                    <div className="patient-id">
                      <Tag type="blue">
                        <FormattedMessage id="patient.natioanalid" /> :
                      </Tag>
                      {nationalId}
                    </div>
                    {subjectNumber && (
                      <div className="patient-id">
                        <Tag type="blue">
                          <FormattedMessage id="patient.subject.number" /> :
                        </Tag>
                        {subjectNumber}{" "}
                      </div>
                    )}
                    {accesionNumber && (
                      <div className="patient-id">
                        <Tag type="blue">
                          <FormattedMessage id="quick.entry.accession.number" />{" "}
                          :
                        </Tag>
                        {accesionNumber}{" "}
                      </div>
                    )}
                  </Column>
                </Grid>
              </div>
            ) : (
              <div className="patient-header">
                <div className="patient-name">
                  {" "}
                  {isOrderPage ? (
                    <FormattedMessage id="sample.label.noorder" />
                  ) : (
                    <FormattedMessage id="patient.label.nopatientid" />
                  )}
                </div>
              </div>
            )}
          </Section>
        </Section>
      </Column>
    </Grid>
  );
};

export default PatientHeader;
