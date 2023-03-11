import React, {useEffect, useRef, useState} from 'react'
import {Button, Column, Grid, Row} from '@carbon/react';
import SampleTypes from './SampleTypes';
import {getFromOpenElisServer} from "../utils/Utils";

export let sampleObj = {index: 0};

const AddSample = () => {
    const componentMounted = useRef(true);
    const [sampleElementsList, setSampleElementsList] = useState([]);
    const [rejectSampleReasons, setRejectSampleReasons] = useState([]);

    const handleAddNewSample = () => {
        setSampleElementsList([...sampleElementsList, sampleObj = {index: sampleObj.index + 1}]);
    }

    const removeSample = (index) => {
        let newList = sampleElementsList.splice(index, 1);
        setSampleElementsList(newList);
    }

    const fetchRejectSampleReasons=(res) => {
        if (componentMounted.current) {
            setRejectSampleReasons(res);
        }
    }

    useEffect(() => {
        setSampleElementsList([...sampleElementsList, sampleObj = {index: sampleObj.index = 1}]);
        getFromOpenElisServer("/rest/test-rejection-reasons", fetchRejectSampleReasons);
        return () => {
            componentMounted.current = false
        }
    }, []);

    return (<>
        <Grid fullWidth={true}>
            <Column lg={16}>
                {
                    sampleElementsList.map((element, index) => {
                        return (<SampleTypes index={index} key={index} rejectSampleReasons={rejectSampleReasons}
                                             removeSample={removeSample}/>)
                    })
                }
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
