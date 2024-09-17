import React from "react";
import { Tag } from "@carbon/react";
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
    age = null,
    patientName = null,
    subjectNumber = null,
    nationalId = null,
    accesionNumber = null,
    orderDate = null,
    referringFacility = null,
    department = null,
    requester = null,
    isOrderPage = false,
    className = "patient-header",
  } = props;
  const patternUrl = GeoPattern.generate(id).toDataUri();

  return (
    <div className={className}>
      <div className="avatar-container">
        <Avatar
          alt={"Patient avatar"}
          color="rgba(0,0,0,0)"
          name={patientName ? patientName : `${lastName} ${firstName}`}
          src={""}
          size={referringFacility ? "150" : "120"}
          textSizeRatio={2}
          style={{
            backgroundImage: `url(${patternUrl})`,
            backgroundRepeat: "round",
          }}
          className={referringFacility ? "patientAvatar2" : "patientAvatar"}
        />
      </div>
      <div className="details-container">
        <div className="patient-name">
          {patientName ? patientName : `${lastName} ${firstName}`}
        </div>
        <div className="patient-dob">
          <Tag className="tag" type="blue">
            <FormattedMessage id="patient.label.sex" /> :
          </Tag>
          {gender === "M" ? (
            <FormattedMessage id="patient.male" />
          ) : (
            <FormattedMessage id="patient.female" />
          )}
          <Tag className="tag" type="blue">
            {age ? (
              <FormattedMessage id="patient.label.age" />
            ) : (
              <FormattedMessage id="patient.dob" />
            )}{" "}
            :
          </Tag>
          {age ? age : dob}
        </div>
        {nationalId && (
          <div className="patient-id">
            <Tag className="tag" type="blue">
              <FormattedMessage id="patient.natioanalid" /> :
            </Tag>
            {nationalId}
          </div>
        )}
        {subjectNumber && (
          <div className="patient-id">
            <Tag className="tag" type="blue">
              <FormattedMessage id="patient.subject.number" /> :
            </Tag>
            {subjectNumber}
          </div>
        )}
        {accesionNumber && (
          <div className="patient-id">
            <Tag className="tag" type="blue">
              <FormattedMessage id="quick.entry.accession.number" /> :
            </Tag>
            {accesionNumber}
          </div>
        )}
        {orderDate && (
          <div className="patient-id">
            <Tag className="tag" type="blue">
              <FormattedMessage id="sample.label.orderdate" /> :
            </Tag>
            {orderDate}
            <Tag className="tag" type="blue">
              <FormattedMessage id="sample.label.requester" /> :
            </Tag>
            {requester}
          </div>
        )}
        {referringFacility && (
          <div className="patient-id">
            <Tag className="tag" type="blue">
              <FormattedMessage id="sample.label.facility" /> :
            </Tag>
            {referringFacility}
            <Tag className="tag" type="blue">
              <FormattedMessage id="sample.label.dept" /> :
            </Tag>
            {department}
          </div>
        )}
      </div>
    </div>
  );
};

export default PatientHeader;
