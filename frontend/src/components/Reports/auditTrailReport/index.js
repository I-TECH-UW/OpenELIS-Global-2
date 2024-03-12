import React, { useState, useEffect } from "react";
import { useIntl } from 'react-intl';
import PageBreadCrumb from "../../common/PageBreadCrumb";
import AuditTrail from "./AuditTrail";

const AuditTrailReport = () => {
    const [breadcrumbs, setBreadCrumbs] = useState([]);
    const intl = useIntl();

    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        const paramType = params.get("type");

        const middleBreadcrumb = {
            label:  intl.formatMessage({id: paramType === "study" ? "label.study.Reports" : "label.routine.Reports"}),
            link: paramType === "study" ? "/StudyReports" : "/RoutineReports"
        }

        const breadCrumbArr = [
            { label: intl.formatMessage({ id: "home.label" }), link: "/" },
            middleBreadcrumb,
            {label: intl.formatMessage({id: "label.audittrail.Reports"}), link: `/AuditTrailReport?type=${paramType}`}
        ]

        setBreadCrumbs(breadCrumbArr);
    }, [])

    return(
        <>
            <br />
            <PageBreadCrumb breadcrumbs={breadcrumbs} />
            <AuditTrail />
        </>
    )
}

export default AuditTrailReport;