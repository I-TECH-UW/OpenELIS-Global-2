import React, { useEffect, useRef, useState } from 'react'
import {
    Checkbox,
    Column,
    Grid,
    IconButton,
    Select,
    SelectItem,
    Table,
    TableBody, TableCell,
    TableHead,
    TableHeader,
    TableRow, TextArea
} from '@carbon/react';
import { Subtract } from '@carbon/react/icons';
import { getFromOpenElisServer } from "../utils/Utils";
import { tableRows } from "./orderItemsTableRow";
import { sampleTypeTestsStructure } from "../data/SampleEntryTestsForTypeProvider";
import { sampleTypesTableHeader } from '../data/SampleTypesTableHeaders';

const SampleTypes = (props) => {
    const index = props.index;
    const componentMounted = useRef(true);
    const [sampleList, setSampleList] = useState([]);
    const sampleTypesRef = useRef(null);
    const [selectedSampleType, setSelectedSampleType] = useState({ id: null, name: "", element_index: 0 });
    const [sampleTypeTests, setSampleTypeTests] = useState(sampleTypeTestsStructure);

    const handleRemoveSampleTest = (index) => {
        props.removeSample(index);
    }


    const handleFetchSampleTypeTests = (e, index) => {
        const { value } = e.target;
        const selectedSampleTypeOption = sampleTypesRef.current.options[sampleTypesRef.current.selectedIndex].text
        setSelectedSampleType({
            ...selectedSampleType,
            id: value,
            name: selectedSampleTypeOption,
            element_index: index
        });
    }
    const rows = tableRows(index, selectedSampleType, handleRemoveSampleTest);

    const fetchSamples = (sampleList) => {
        if (componentMounted.current) {
            setSampleList(sampleList);
        }
    }

    const fetchSamplesTests = (sampleTypeTestsResponse) => {
        setSampleTypeTests({ ...sampleTypeTestsStructure });
        if (componentMounted.current) {
            setSampleTypeTests(sampleTypeTestsResponse);
        }
    }
    useEffect(() => {
        getFromOpenElisServer("/rest/samples", fetchSamples);
        return () => {
            componentMounted.current = false;
        }
    }, []);

    useEffect(() => {
        componentMounted.current = true;
        if (selectedSampleType.id != null) {
            getFromOpenElisServer(`/rest/sample-type-tests?sampleType=${selectedSampleType.id}`, fetchSamplesTests);
        }
        return () => {
            componentMounted.current = false;
        }
    }, [selectedSampleType.id]);


    return (
        <>
            <Column lg={16}>
                <div className="inlineDiv">
                    <IconButton label="" onClick={() => handleRemoveSampleTest(index)} kind='tertiary'
                        size='sm'>
                        <Subtract size={16} />
                    </IconButton>
                    &nbsp;&nbsp;
                    <p>
                        Sample
                        <span className="requiredFieldIndicator"> *</span>
                    </p>
                </div>

                <Select
                    className="selectSampleType"
                    id={"sampleId_" + index}
                    ref={sampleTypesRef}
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
            </Column>
            {selectedSampleType.id != null && <div>
                <Grid>
                    <Column lg={16}>
                        <Table useZebraStyles={false} className='sampleTypesTable'>
                            <TableHead>
                                <TableRow>
                                    {sampleTypesTableHeader.map((header, header_index) => (
                                        <TableHeader id={header.key} key={header_index}>
                                            {header.header}
                                        </TableHeader>
                                    ))}
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {rows.map((row, table_index) => (
                                    <TableRow key={table_index}>
                                        {Object.keys(row)
                                            .filter((key) => key !== 'id')
                                            .map((key) => {
                                                return <TableCell key={key}>{row[key]}</TableCell>;
                                            })}
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>

                    </Column>
                </Grid>
                <div className="cds--grid">
                    <div className="cds--row">
                        <div className="cds--col">
                            <h2>Panels</h2>
                            <h3>Name</h3>
                            {
                                sampleTypeTests.panels != null && sampleTypeTests.panels.map(panel => {
                                    return <Checkbox labelText={panel.name} id={`panel_` + index + "_" + panel.panelId} key={index + panel.panelId} />
                                })
                            }
                        </div>
                        <div className="cds--col">
                            <h2>Available Tests</h2>
                            <TextArea rows={3} labelText="" placeholder="Search" />
                            <h3>Name</h3>
                            {
                                sampleTypeTests.tests != null && sampleTypeTests.tests.map(test => {
                                    return <Checkbox labelText={test.name} id={`test_` + index + "_" + test.id} key={index + test.id} />
                                })
                            }
                        </div>
                    </div>
                </div>
            </div>
            }
        </>
    )

}

export default SampleTypes;
