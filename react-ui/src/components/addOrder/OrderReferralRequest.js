import React, {useContext, useEffect, useState} from 'react';
import {
    Column,
    DatePicker,
    DatePickerInput,
    Grid,
    Select,
    SelectItem,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
    TextInput
} from "@carbon/react";
import {UserInformationContext} from "../layout/Layout";


const header = [{key: 'reason', header: 'Reason for Referral*'}, {key: 'referrer', header: 'Referrer'}, {
    key: 'institute', header: 'Institute'
}, {key: '', header: 'Sent Date\n' + '(dd/mm/yyyy)'}, {key: 'name', header: 'Test Name'},];

const OrderReferralRequest = ({index, selectedTests, referralReasons, referralOrganizations}) => {
    const {user} = useContext(UserInformationContext);
    const [referralRows, setReferralRows] = useState([]);

    useEffect(() => {
        const rows = [];
        selectedTests.map(test => {
            let elementId = index + "_" + test.id;
            let row = {
                reason: <ReferralReason index={elementId} reasons={referralReasons}/>,
                referrer: <Referrer index={elementId} user={user}/>,
                institute: <Institute index={elementId} organizations={referralOrganizations}/>,
                sentDate: <SentDate index={elementId}/>,
                testName: <TestName index={elementId} test={test}/>
            }
            rows.push(row);
        });
        setReferralRows(rows);

    }, [selectedTests]);


    const ReferralReason = ({index, reasons}) => {
        return (<>
            <Select
                labelText=""
                id={`referralReasonId_` + index}>
                {
                    reasons.map(reason => {
                        return (
                            <SelectItem
                                key={reason.id}
                                text={reason.value}
                                value={reason.id}
                            />
                        );
                    })
                }
            </Select>
        </>);
    };
    const Referrer = ({index, user}) => {
        return (<>
            <TextInput id={`referrer_${index}`} labelText="" value={user.firstName + " " + user.lastName}/>
        </>);
    };

    const Institute = ({index, organizations}) => {
        return (<>
            <Select
                labelText="" id={`referredInstituteId_` + index}>
                {
                    organizations.map(org => {
                        return (
                            <SelectItem
                                key={org.id}
                                text={org.value}
                                value={org.id}
                            />
                        );
                    })
                }
            </Select>
        </>);
    };

    const SentDate = ({index}) => {
        return (<>
            <DatePicker dateFormat="m/d/Y" datePickerType="single">
                <DatePickerInput id={`sendDate_` + index} placeholder="dd/mm/yyyy" type="text" labelText=""/>
            </DatePicker>
        </>);
    }

    const TestName = ({index, test}) => {
        return (<>
            <Select
                labelText="" id={`shadowReferredTest_` + index}>
                <SelectItem
                    text={test.name}
                    value={test.id}
                />
            </Select>
        </>);
    };


    return (<>
        <Grid>
            <Column lg={16}>
                <Table useZebraStyles={false} id={`referralRequestTable_` + index}>
                    <TableHead>
                        <TableRow>
                            {header.map((header, header_index) => (
                                <TableHeader id={header.key} key={header_index}>
                                    {header.header}
                                </TableHeader>))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {referralRows.length > 0 && referralRows.map((row, td_index) => (
                            <TableRow key={td_index}>
                                {
                                    Object.keys(row)
                                        .filter((key) => key !== 'id')
                                        .map((key) => {
                                            return <TableCell key={key}>{row[key]}
                                            </TableCell>;
                                        })
                                }
                            </TableRow>))}
                    </TableBody>
                </Table>
            </Column>
        </Grid>
    </>)
}

export default OrderReferralRequest;
