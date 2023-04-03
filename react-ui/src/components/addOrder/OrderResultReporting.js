import React from 'react'
import {Checkbox, Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@carbon/react";

const PatientEmailAndSMS = (testId) => {
    return (
        <>
            <Checkbox id={`patientEmail_` + testId} labelText={"Email"}/>
            <Checkbox id={`patientSMS_` + testId} labelText={"SMS"}/>
        </>
    )
}

const RequesterEmailAndSMS = (testId) => {
    return (
        <>
            <Checkbox id={`providerEmail_` + testId} labelText="Email"/>
            <Checkbox id={`providerSMS_` + testId} labelText="SMS"/>
        </>
    )
}

const OrderResultReporting = (props) => {
    const headers = ['', 'Patient', 'Requester'];
    let rows = [];
    props.selectedTests.map((test, index) => {
        const testId = test.id;
        rows.push({
            id: test.id,
            testName: <p key={index}>{test.name}</p>,
            patientEmailAndSMS: PatientEmailAndSMS(testId),
            requesterEmailAndSMS: RequesterEmailAndSMS(testId)
        });
    });

    return (
        <>

            <Table size="sm" useZebraStyles={false}>
                <TableHead>
                    <TableRow>
                        {headers.map((header) => (
                            <TableHeader id={header.key} key={header}>
                                {header}
                            </TableHeader>
                        ))}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row) => (
                        <TableRow key={row.id}>
                            {Object.keys(row)
                                .filter((key) => key !== 'id')
                                .map((key) => {
                                    return <TableCell key={key}>{row[key]}</TableCell>;
                                })}
                        </TableRow>
                    ))}
                </TableBody>
            </Table>

        </>
    )
}

export default OrderResultReporting;
