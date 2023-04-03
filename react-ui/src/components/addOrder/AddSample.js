import React, {useEffect, useRef, useState} from 'react'
import {Button, Column, Grid, Row} from '@carbon/react';
import SampleTypes from './SampleTypes';
import {getFromOpenElisServer} from "../utils/Utils";

const AddSample = (props) => {
    const componentMounted = useRef(true);
    const [sampleElementsList, setSampleElementsList] = useState([{index: "", tests: []}]);
    const [elementsCounter, setElementsCounter] = useState(0);
    const [rejectSampleReasons, setRejectSampleReasons] = useState([]);
    const [configurationProperties, setConfigurationProperties] = useState([{id: "", value: ""}]);

    const handleAddNewSample = () => {
        setElementsCounter(elementsCounter + 1);
        const updates = [...sampleElementsList];
        updates.push({
            index: elementsCounter,
            tests: []
        });
        setSampleElementsList(updates);
    }

    function sampleTypeObject(callback, index) {
        let newState = [...sampleElementsList];
        newState[index].tests = callback;
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
        setSampleElementsList(newState);
        setElementsCounter(elementsCounter + 1);
    }

    function findConfigurationProperty(property) {
        if (configurationProperties.length > 0) {
            const filterProperty = configurationProperties.find((config) => config.id === property);
            if (filterProperty !== undefined) {
                return filterProperty.value
            }
        }
    }
    const fetchConfigurationProperties = (res) => {
        if (componentMounted.current) {
            setConfigurationProperties(res);
        }
    }

    useEffect(() => {
        initialiseSampleElements();
        getFromOpenElisServer("/rest/configuration-properties", fetchConfigurationProperties);
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
                                         removeSample={removeSample} sampleTypeObject={sampleTypeObject}
                                         findConfigurationProperty ={findConfigurationProperty}/>)
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
