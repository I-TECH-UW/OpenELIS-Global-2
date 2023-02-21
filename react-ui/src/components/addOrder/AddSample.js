import React, {useState, useRef, useEffect} from 'react'
import {
    Grid, Column, IconButton, Button, Select, SelectItem, Row
} from '@carbon/react';
import {Add, Subtract} from '@carbon/react/icons';
import {getFromOpenElisServer} from "../utils/Utils";
import SampleTypes from './SampleTypes';

export const sampleObj = [{
    id: null, action: ""
}];

const AddSample = () => {
    const componentMounted = useRef(true);
    const sampleTypesRef = useRef(null);
    const [selectedSampleType, setSelectedSampleType] = useState({id: "", name: "", element_index: 0});
    const [sampleList, setSampleList] = useState([]);


    const [sampleElementsList, setSampleElementsList] = useState([{
        sampleId: null
    }]);

    const handleAddNewSample = () => {
        setSampleElementsList([...sampleElementsList, sampleObj])
    }
    const handleRemoveSampleTest = (index) => {
        const list = [...sampleElementsList];
        list.splice(index, 1);
        setSampleElementsList(list);
    }

    const handleFetchSampleTypeTests = (e, index) => {
        const {value} = e.target;
        const selectedSampleTypeOption = sampleTypesRef.current.options[sampleTypesRef.current.selectedIndex].text
        setSelectedSampleType({
            ...selectedSampleType, id: value, name: selectedSampleTypeOption, element_index: index
        });
    }

    useEffect(() => {

    }, [sampleElementsList]);

    const fetchSamples = (sampleList) => {
        if (componentMounted.current) {
            setSampleList(sampleList);
        }
    }

    useEffect(() => {
        getFromOpenElisServer("/rest/samples", fetchSamples)
        return () => {
            componentMounted.current = false;
        }
    }, []);

    return (<>
            <Grid fullWidth={true}>
                <Column lg={16}>
                    {sampleElementsList.map((element, index) => (<Grid key={index}>
                            <Column lg={16}>
                                <div className="inlineDiv">

                                    <IconButton label="" onClick={() => handleRemoveSampleTest(index)} kind='tertiary'
                                                size='sm'>
                                        <Subtract size={16}/>
                                    </IconButton>
                                    &nbsp;&nbsp;
                                    <p>
                                        Sample
                                        <span className="requiredFieldIndicator"> *</span>
                                    </p>
                                </div>

                                <Select
                                    ref={sampleTypesRef}
                                    className="selectSampleType"
                                    id={"sampleId_" + index}
                                    name="sampleId"
                                    labelText=""
                                    onChange={(e) => {
                                        handleFetchSampleTypeTests(e, index);
                                    }}
                                    required
                                >
                                    <SelectItem
                                        text=""
                                        value=""
                                    />
                                    {sampleList.map((sample, sample_index) => (<SelectItem
                                            text={sample.value}
                                            value={sample.id}
                                            key={sample_index}
                                        />))}
                                </Select>

                                {selectedSampleType.id.length > 0 && <SampleTypes data={{
                                    index: index + 1, sampleType: selectedSampleType
                                }}/>}

                            </Column>
                        </Grid>))}

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
