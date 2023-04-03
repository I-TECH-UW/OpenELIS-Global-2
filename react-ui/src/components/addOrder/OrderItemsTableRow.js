import {
    Checkbox,
    DatePicker,
    DatePickerInput,
    Link,
    RadioButton,
    Select,
    SelectItem,
    TextArea,
    TextInput,
    TimePicker
} from "@carbon/react";
import React, {useContext, useState} from "react";
import {NotificationKinds} from "../common/CustomNotification";
import {NotificationContext} from "../layout/Layout";
import {FormattedMessage} from "react-intl";

const Sequence = (index) => {
    return (<TextInput id={`sequence_${index}`} value={index + 1} style={{width: "50px"}}
                       labelText=""/>);
}

const Sample_Type = (index, name) => {
    return (<span id={`typeId_${index}`}>{name}</span>);
}


const CollectionDate = (index,defaultDate) => {
    return (<DatePicker dateFormat="m/d/Y" datePickerType="single"  value={defaultDate}>
        <DatePickerInput id={`collectionDate_` + index} placeholder="dd/mm/yyyy" type="text" labelText=""
                         style={{width: "140px"}}/>
    </DatePicker>);
}

const SampleSelect = (index) => {
    return (<RadioButton checked={true} id={`select_${index}`} value={index} labelText=""/>);
}


const CollectionTime = (index,defaultTime) => {
    return (<TimePicker id={`collectionTime_${index}`} value={defaultTime}/>);
}

const Collector = (index) => {
    return (<TextInput id={`collector${index}`} style={{width: "120px"}} labelText=""/>);
}


const RejectReasons = (index, rejectSampleReasons, rejectionReasonsDisabled) => {
    return (<Select
        disabled={rejectionReasonsDisabled}
        id={`rejectedReasonId_${index}`}
        labelText="">
        <SelectItem
            text=""
            value=""
        />
        {rejectSampleReasons.map(reason => {
            return (<SelectItem
                key={reason.id}
                text={reason.value}
                value={reason.id}
            />)
        })}

    </Select>);
}

const RemoveSample = (index, handleRemoveSampleTest) => {

    const handleRemoveSample = (e) => {
        e.preventDefault();
        handleRemoveSampleTest(index);
    }
    return (<Link href="#" id={`removeButton_${index}`} onClick={(e) => handleRemoveSample(e)}>Remove Sample</Link>);
}


const Reject = (index, rejectionReasonsDisabled, setRejectionReasonsDisabled) => {
    const { setNotificationVisible, setNotificationBody } = useContext(NotificationContext);
    function handleRejection(e) {
        if (e.currentTarget.checked) {
            setNotificationBody({
                kind: NotificationKinds.warning,
                title: "Notification Message",
                message: <FormattedMessage id="reject.order.sample.notification"/>
            });
            setNotificationVisible(true);
        }
        setRejectionReasonsDisabled(!rejectionReasonsDisabled);
    }

    return (
        <Checkbox labelText="" id={`reject_${index}`} onChange={(e) => handleRejection(e)}/>
    );
}
export const TableSampleTableRows = (index, selectedSampleType, rejectSampleReasons, selectedTests,findConfigurationProperty, handleRemoveSampleTest) => {
    const {id, name} = selectedSampleType;
    const [rejectionReasonsDisabled, setRejectionReasonsDisabled] = useState(true);
    return [{
        select_radioButton: SampleSelect(index),
        sampleType: Sequence(index),
        name: Sample_Type(index, name),
        collectionDate: CollectionDate(index,findConfigurationProperty("currentDateAsText")),
        collectionTime: CollectionTime(index, findConfigurationProperty("currentTimeAsText")),
        collector: Collector(index),
        reject: Reject(index, rejectionReasonsDisabled, setRejectionReasonsDisabled),
        reason: RejectReasons(index, rejectSampleReasons, rejectionReasonsDisabled),
        removeSample: RemoveSample(index, handleRemoveSampleTest)
    }];
}
