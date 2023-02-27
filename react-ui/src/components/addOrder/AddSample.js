import React, {useEffect, useState} from 'react'
import {Button, Column, Grid, Row} from '@carbon/react';
import SampleTypes from './SampleTypes';

export let sampleObj = {index: 0};

const AddSample = () => {
    const [sampleElementsList, setSampleElementsList] = useState([]);

    const handleAddNewSample = () => {
        setSampleElementsList([...sampleElementsList, sampleObj = {index: sampleObj.index + 1}]);
    }

    const removeSample = (index) => {
        let newList = sampleElementsList.splice(index, 1);
        setSampleElementsList(newList);
    }

    useEffect(() => {

    }, [sampleElementsList]);

    return (<>
        <Grid fullWidth={true}>
            <Column lg={16}>
                {
                    sampleElementsList.map((element, index) => {
                        return (
                            <SampleTypes index={index} key={index} removeSample={removeSample}/>
                        );
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
