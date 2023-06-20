import React, {useContext, useEffect, useState} from 'react';
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@carbon/react";
import {UserInformationContext} from "../layout/Layout";
import CustomTextInput from "../common/CustomTextInput";
import CustomSelect from "../common/CustomSelect";
import CustomDatePicker from "../common/CustomDatePicker";

function requiredSymbol(value) {
    return (
        <> {value} <span style={{color: "red"}}>*</span></>
    )
}

const header = [{key: 'reason', header: requiredSymbol("Reason for Referral")}, {key: 'referrer', header: 'Referrer'}, {
    key: 'institute', header: requiredSymbol("Institute")
}, {key: '', header: 'Sent Date\n' + '(dd/mm/yyyy)'}, {key: 'name', header: requiredSymbol("Test Name")},];


const OrderReferralRequest = ({
                                  index,
                                  selectedTests,
                                  referralReasons,
                                  referralOrganizations,
                                  referralRequest,
                                  setReferralRequest
                              }) => {
    const {user} = useContext(UserInformationContext);
    const [referralRows, setReferralRows] = useState([]);

    function handleReferrer(referrer, index) {
        const update = [...referralRequest];
        update[index].referrer = referrer;
        setReferralRequest(update);
    }

    function handleReasonForReferral(reasonId, index) {
        const update = [...referralRequest];
        update[index].reasonForReferral = reasonId;
        setReferralRequest(update);
    }

    function handleInstituteSelect(instituteId, index) {
        const update = [...referralRequest];
        update[index].institute = instituteId;
        setReferralRequest(update);
    }

    function handleSentDatePicker(date, index) {
        if (date != null) {
            const update = [...referralRequest];
            update[index].sentDate = date;
            setReferralRequest(update);
        }
    }

    const updateUIRender = () => {
        const rows = [];
        let obj = {};
        const updateReferralRequest = [...referralRequest];
        let testValue = {};
        let defaultSelect = {};
        selectedTests.map((test, array_index) => {
            let id = index + "_" + test.id;
            testValue = {
                id: test.id, value: test.name
            };
            defaultSelect = {
                id: "", value: ""
            }


            obj = {
                referralRequestObject: referralReasons[0].id,
                referrer: user.firstName + " " + user.lastName,
                institute: null,
                sentDate: "",
                testId: test.id
            };
            let row = {
                reason: <CustomSelect id={"referralReasonId_" + id} options={referralReasons}
                                      onChange={(e) => handleReasonForReferral(e, array_index)}/>,
                referrer: <CustomTextInput id={"referrer_" + id} defaultValue={obj.referrer}
                                           onChange={(value) => handleReferrer(value, array_index)}/>,
                institute: <CustomSelect id={"referredInstituteId_" + id} options={referralOrganizations}
                                         onChange={(e) => handleInstituteSelect(e, array_index)}
                                         defaultSelect={defaultSelect}/>,
                sentDate: <CustomDatePicker id={"sendDate_" + id}
                                            onChange={(date) => handleSentDatePicker(date, array_index)} labelText={""}/>,
                testName: <CustomSelect id={"shadowReferredTest_" + id} defaultSelect={testValue}/>,
            };
            rows.push(row);
        });
        setReferralRows(rows);
        updateReferralRequest.push(obj);
        setReferralRequest(updateReferralRequest);
    }

    useEffect(() => {
        updateUIRender();
    }, [selectedTests]);

    return (<>
        <>
            <Table useZebraStyles={false} id={`referralRequestTable_` + index}>
                    <TableHead>
                        <TableRow>
                            {header.map((header, header_index) => (<TableHeader id={header.key} key={header_index}>
                                {header.header}
                            </TableHeader>))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {referralRows.length > 0 && referralRows.map((row, td_index) => (<TableRow key={td_index}>
                            {Object.keys(row)
                                .filter((key) => key !== 'id')
                                .map((key) => {
                                    return <TableCell key={key}>{row[key]}
                                    </TableCell>;
                                })}
                        </TableRow>))}
                    </TableBody>
                </Table>
        </>
    </>)
}

export default OrderReferralRequest;
