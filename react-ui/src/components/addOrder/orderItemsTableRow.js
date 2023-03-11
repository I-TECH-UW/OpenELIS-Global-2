import {
    Select,
    SelectItem,
    TextArea,
    RadioButton,
    DatePicker,
    DatePickerInput,
    TimePicker,
    Link,
    Checkbox,
    TextInput
} from "@carbon/react";
import { useEffect, useState } from "react";
import { getFromOpenElisServer } from "../utils/Utils";

const sequence = (index) => {
    return (
        <TextInput id={`sequence_${index}`} value={index + 1} readOnly={true} style={{ width: "50px" }}
            labelText="" />
    );
}

const sample_Type = (index, name) => {
    return (
        <span id={`typeId_${index}`}>{name}</span>
    );
}


const collectionDate = (index) => {
    return (
        <DatePicker dateFormat="m/d/Y" datePickerType="single">
            <DatePickerInput id={`collectionDate_` + index} placeholder="dd/mm/yyyy" type="text" labelText=""
                style={{ width: "140px" }} />
        </DatePicker>
    );
}

const sampleSelect = (index) => {
    return (
        <RadioButton checked={true} read id={`select_${index}`} value={index} labelText="" read={"false"} />
    );
}


const collectionTime = (index) => {
    return (
        <TimePicker id={`collectionTime_${index}`} />
    );
}

const collector = (index) => {
    return (
        <TextInput id={`collector${index}`} style={{ width: "120px" }} labelText="" />
    );
}


const tests = (index) => {
    return (
        <TextArea rows={3} labelText="" id={`tests_${index}`} />
    );
}
const rejectReasons = (index, rejectionReasonsDisabled) => {
    const [rejectReasons, setRejectionReasons] = useState([]);

    function fetchRejectReasons(res) {
        setRejectionReasons(res);
    }

    useEffect(() => {
        getFromOpenElisServer("/rest/test-rejection-reasons", fetchRejectReasons);
    }, [index]);


    return (
        <Select
            disabled={rejectionReasonsDisabled}
            id={`rejectedReasonId_${index}`}
            labelText="">
            <SelectItem
                text=""
                value=""
            />
            {
                rejectReasons.map(reason => {
                    return (
                        <SelectItem
                            key={reason.id}
                            text={reason.value}
                            value={reason.id}
                        />
                    )
                })
            }

        </Select>
    );
}

const removeSample = (index, handleRemoveSampleTest) => {

    const handleRemoveSample = (e) => {
        e.preventDefault();
        handleRemoveSampleTest(index);
    }
    return (
        <Link href="#" id={`removeButton_${index}`} onClick={(e) => handleRemoveSample(e)}>Remove Sample</Link>
    );
}


const reject = (index, rejectionReasonsDisabled, setRejectionReasonsDisabled) => {
    function handleRejection() {
        setRejectionReasonsDisabled(!rejectionReasonsDisabled);
    }

    return (
        <Checkbox labelText="" id={`reject_${index}`} onChange={handleRejection} />
    );
}
export const tableRows = (index, selectedSampleType, handleRemoveSampleTest) => {
    const { id, name } = selectedSampleType;
    const [rejectionReasonsDisabled, setRejectionReasonsDisabled] = useState(true);

    return [
        {
            select_radioButton: sampleSelect(index),
            sampleType: sequence(index),
            name: sample_Type(index, name),
            collectionDate: collectionDate(index),
            collectionTime: collectionTime(index),
            collector: collector(index),
            tests: tests(index),
            reject: reject(index, rejectionReasonsDisabled, setRejectionReasonsDisabled),
            reason: rejectReasons(index, rejectionReasonsDisabled),
            removeSample: removeSample(index, handleRemoveSampleTest)
        }
    ];
}
