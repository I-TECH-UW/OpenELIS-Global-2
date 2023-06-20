import React, {useEffect, useRef, useState} from 'react'
import {Button, Link, Row, Stack} from "@carbon/react";
import {Add} from '@carbon/react/icons';
import {getFromOpenElisServer} from "../utils/Utils";
import SampleType from "./SampleType";

const AddSample = (props) => {
    const {samples, setSamples} = props;
    const componentMounted = useRef(true);
    const [elementsCounter, setElementsCounter] = useState(0);

    const [rejectSampleReasons, setRejectSampleReasons] = useState([]);

    const handleAddNewSample = () => {
        let updateSamples = [...samples];
        let count = elementsCounter + 1
        updateSamples.push({
            index: count,
            referralItems: [],
            sampleTypeId: "",
            sampleXML: null,
            tests: []
        })
        setSamples(updateSamples);
        setElementsCounter(count);
    }

    const sampleTypeObject = (object) => {

        const {sampleTypeId, selectedTests, sampleXML, referralItems, sampleObjectIndex} = object;
        let newState = [...samples];
        if (sampleTypeId) {
            console.log(samples)
            newState[sampleObjectIndex].sampleTypeId = sampleTypeId;
        } else if (selectedTests && selectedTests.length > 0) {
            newState[sampleObjectIndex].tests = selectedTests;
        } else if (referralItems && referralItems.length > 0) {
            newState[sampleObjectIndex].referralItems = referralItems;
        } else if (sampleXML != null) {
            newState[sampleObjectIndex].sampleXML = sampleXML;
        }
        props.setSamples(newState);
    }

    const removeSample = (index) => {
        let updateSamples = samples.splice(index, 1);
        setSamples(updateSamples);
    }

    const fetchRejectSampleReasons = (res) => {
        if (componentMounted.current) {
            setRejectSampleReasons(res);
        }
    }


    const handleRemoveSample = (e, sample) => {
        e.preventDefault();
        let filtered = samples.filter(function (element) {
            return element !== sample
        });
        setSamples(filtered);
    }

    useEffect(() => {
        getFromOpenElisServer("/rest/test-rejection-reasons", fetchRejectSampleReasons);
        return () => {
            componentMounted.current = false
        }
    }, []);


    return (
        <>
            <h3>SAMPLE</h3>
            <Stack gap={10}>
                <div className="orderLegendBody">
                    {samples.map((sample, i) => {
                        return (
                            <div className="sampleType" key={i}>
                                <h4>Sample {i + 1}</h4>
                                <Link href="#" onClick={(e) => handleRemoveSample(e, sample)}>Remove
                                    Sample</Link>
                                <SampleType index={i} rejectSampleReasons={rejectSampleReasons}
                                            removeSample={removeSample} sample={sample}
                                            sampleTypeObject={sampleTypeObject}/>
                            </div>
                        )
                    })}
                    <Row>
                        <div className="inlineDiv">
                            <Button onClick={handleAddNewSample}>Add Sample
                                &nbsp;  &nbsp;
                                <Add size={16}/>
                            </Button>
                        </div>
                    </Row>
                </div>
            </Stack>
        </>
    )
}

export default AddSample;
