import React, {useEffect, useRef, useState} from 'react'
import {Button, Column, Grid, Row} from '@carbon/react';
import SampleTypes from './SampleTypes';
import {getFromOpenElisServer} from "../utils/Utils";

const AddSample = (props) => {
    const componentMounted = useRef(true);
    const [sampleElementsList, setSampleElementsList] = useState([{index: "", tests: [],referralItems:[]}]);
    const [elementsCounter, setElementsCounter] = useState(0);
    const [rejectSampleReasons, setRejectSampleReasons] = useState([]);

    const handleAddNewSample = () => {
        setElementsCounter(elementsCounter + 1);
        const updates = [...sampleElementsList];
        updates.push({
            index: elementsCounter, tests: [], referralItems: []
        });
        setSampleElementsList(updates);
    }

    function sampleTypeObject(object) {
        const {sampleTypeId, selectedTests, sampleXML, referralItems, index} = object;
        let newState = [...sampleElementsList];
        if (selectedTests && selectedTests.length > 0) {
            newState[index].sampleTypeId = sampleTypeId;
            newState[index].tests = selectedTests;
        } else if (referralItems && referralItems.length > 0) {
            newState[index].referralItems = referralItems;
        } else if (sampleXML != null) {
            newState[index].sampleXML = sampleXML;
        }
        setSampleElementsList(newState);
        props.setSamples(sampleElementsList);
    }

    const removeSample = (index) => {
        let newList = sampleElementsList.splice(index, 1);
        setSampleElementsList(newList);
        props.setSamples(sampleElementsList);
    }

    const fetchRejectSampleReasons = (res) => {
        if (componentMounted.current) {
            setRejectSampleReasons(res);
        }
    }

    function initialiseSampleElements() {
        let newState = [...sampleElementsList];
        newState[0].index = elementsCounter;
        newState[0].sampleTypeId = "";
        newState[0].sampleXML = null;
        newState[0].tests = [];
        newState[0].referralItems = [];
        setSampleElementsList(newState);
        setElementsCounter(elementsCounter + 1);
    }


    useEffect(() => {
        initialiseSampleElements();
        getFromOpenElisServer("/rest/test-rejection-reasons", fetchRejectSampleReasons);
        return () => {
            componentMounted.current = false
        }
    }, []);

    return (<>
        <Grid fullWidth={true}>
            <Column lg={16}>
                {sampleElementsList && sampleElementsList.map((element, index) => {
                    return (<SampleTypes index={index} key={index} rejectSampleReasons={rejectSampleReasons}
                                         removeSample={removeSample} sampleTypeObject={sampleTypeObject}/>)
                })}
                <Row>
                    <div className="inlineDiv">
                        <Button onClick={handleAddNewSample}>Add New Sample</Button>
                    </div>
                </Row>
            </Column>
        </Grid>

    </>)
}

export default AddSample;
