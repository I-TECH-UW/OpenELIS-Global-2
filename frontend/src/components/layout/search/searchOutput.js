import React from "react";
import { Grid, Column, Section, Tag, Tile } from "@carbon/react";
import { FormattedMessage } from "react-intl";
import Avatar from "react-avatar";
import GeoPattern from "geopattern";
import { openPatientResults } from "./searchService";

const SearchOutput = ({ patientData, className = "patientHead" }) => {
  return (
    <div>
      {patientData.map((patient) => {
        const patternUrl = GeoPattern.generate(patient.id).toDataUri();
        return (
          <Column lg={16} md={8} sm={4} key={patient.id}>
            <Section>
              <div>
                <Grid
                  className="patientHead"
                  onClick={() => openPatientResults(patient.patientID)}
                >
                  <Column lg={4} md={2} sm={2}>
                    <div role="img">
                      <Avatar
                        alt="Patient avatar"
                        color="rgba(0,0,0,0)"
                        name={`${patient.lastName} ${patient.firstName}`}
                        src={""}
                        size={patient.referringFacility ? "70" : "60"}
                        textSizeRatio={2}
                        style={{
                          backgroundImage: `url(${patternUrl})`,

                          marginTop: "5px",
                        }}
                      />
                    </div>
                  </Column>
                  <Column lg={10} md={5} sm={2}>
                    <div>{`${patient.lastName} ${patient.firstName}`}</div>
                    <div>
                      <Tag size="sm" type="blue">
                        <FormattedMessage id="patient.label.sex" /> :
                      </Tag>
                      {patient.gender === "M" ? (
                        <FormattedMessage id="patient.male" />
                      ) : (
                        <FormattedMessage id="patient.female" />
                      )}
                      <Tag size="sm" type="blue">
                        {patient.age ? (
                          <FormattedMessage id="patient.label.age" />
                        ) : (
                          <FormattedMessage id="patient.dob" />
                        )}{" "}
                        :
                      </Tag>
                      {patient.age || patient.dob}
                    </div>
                    {patient.nationalId && (
                      <div>
                        <Tag size="sm" type="blue">
                          <FormattedMessage id="patient.natioanalid" /> :
                        </Tag>
                        {patient.nationalId}
                      </div>
                    )}
                  </Column>
                </Grid>
              </div>
            </Section>
          </Column>
        );
      })}
    </div>
  );
};

export default SearchOutput;
