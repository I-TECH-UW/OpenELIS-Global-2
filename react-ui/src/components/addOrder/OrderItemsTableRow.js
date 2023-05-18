import {Link, RadioButton} from "@carbon/react";
import React, {useContext, useEffect, useState} from "react";
import {NotificationKinds} from "../common/CustomNotification";
import {NotificationContext} from "../layout/Layout";
import {FormattedMessage} from "react-intl";
import CustomTextInput from "../common/CustomTextInput";
import CustomDatePicker from "../common/CustomDatePicker";
import CustomSelect from "../common/CustomSelect";
import CustomTimePicker from "../common/CustomTimePicker";
import CustomCheckBox from "../common/CustomCheckBox";

const Sample_Type = (index, name) => {
    return (<span id={`typeId_${index}`}>{name}</span>);
}

const SampleSelect = (index) => {
    return (<RadioButton checked={true} id={`select_${index}`} value={index} labelText=""/>);
}

const RemoveSample = (index, handleRemoveSampleTest) => {

    const handleRemoveSample = (e) => {
        e.preventDefault();
        handleRemoveSampleTest(index);
    }
    return (<Link href="#" id={`removeButton_${index}`} onClick={(e) => handleRemoveSample(e)}>Remove Sample</Link>);
}

export const TableSampleTableRows = (index, selectedSampleType, rejectSampleReasons, selectedTests, updateSampleXml, handleRemoveSampleTest) => {
    const {setNotificationVisible, setNotificationBody} = useContext(NotificationContext);
    const {id, name} = selectedSampleType;
    const [rejectionReasonsDisabled, setRejectionReasonsDisabled] = useState(true);
    const defaultSelect = {id: "", value: ""};
    const [sampleXml, setSampleXml] = useState({
        collectionDate: "",
        collector: "",
        rejected: false,
        rejectionReason: "",
        collectionTime: ""
    });

    function handleCollectionDate(date) {
        setSampleXml({
            ...sampleXml,
            collectionDate: date
        });
    }

    function handleCollector(value) {
        setSampleXml({
            ...sampleXml,
            collector: value
        });
    }

    function handleReasons(value) {
        setSampleXml({
            ...sampleXml,
            rejectionReason: value
        });
    }

    function handleCollectionTime(time) {
        setSampleXml({
            ...sampleXml,
            collectionTime: time
        });
    }

    useEffect(() => {
        updateSampleXml(sampleXml, index);
    }, [sampleXml]);

    function handleRejection(checked) {
        if (checked) {
            setNotificationBody({
                kind: NotificationKinds.warning,
                title: "Notification Message",
                message: <FormattedMessage id="reject.order.sample.notification"/>
            });
            setNotificationVisible(true);
        }
        setSampleXml({
            ...sampleXml,
            rejected: checked
        });
        setRejectionReasonsDisabled(!rejectionReasonsDisabled);
    }

    return [{
        select_radioButton: SampleSelect(index),
        sampleType: <CustomTextInput id={"sequence_" + index} defaultValue={index + 1}
                                     onChange={null}/>,
        name: Sample_Type(index, name),
        collectionDate: <CustomDatePicker id={"collectionDate_" + index}
                                          onChange={(date) => handleCollectionDate(date)} labelText={""}/>,
        collectionTime: <CustomTimePicker id={"collectionDate_" + index}
                                          onChange={(time) => handleCollectionTime(time)}/>,
        collector: <CustomTextInput id={"collector_" + id} onChange={(value) => handleCollector(value)}
                                    defaultValue={""}/>,
        reject: <CustomCheckBox id={"reject_" + index} onChange={(value) => handleRejection(value)} label=""/>,
        reason: <CustomSelect id={"rejectedReasonId_" + index} options={rejectSampleReasons}
                              disabled={rejectionReasonsDisabled}
                              defaultSelect={defaultSelect}
                              onChange={(e) => handleReasons(e)}/>,
        removeSample: RemoveSample(index, handleRemoveSampleTest)
    }];
}
