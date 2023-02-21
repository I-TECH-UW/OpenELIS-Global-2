import React, {useEffect, useRef, useState} from 'react'
import {
    TextInput,
    DatePicker,
    DatePickerInput,
    TimePicker,
    TextArea,
    Checkbox,
    Link,
    Select,
    SelectItem,
    Table,
    TableHead,
    TableRow,
    TableHeader,
    TableBody,
    TableCell,
    Column,
    Grid,
    RadioButton,
} from '@carbon/react';
import {sampleTypeTestsStructure} from '../data/SampleEntryTestsForTypeProvider';
import {sampleTypesTableHeader} from '../data/SampleTypesTableHeaders';
import {getFromOpenElisServer} from "../utils/Utils";

const SampleTypes = ({data}) => {
    const {index, sampleType} = data;
    const componentMounted = useRef(true);

    const [sampleTypeTests, setSampleTypeTests] = useState(sampleTypeTestsStructure);

    const fetchSamplesTests = (sampleTypeTestsResponse) => {
        setSampleTypeTests({sampleTypeTestsStructure});
        if (componentMounted.current) {
            setSampleTypeTests(sampleTypeTestsResponse);
            console.log(sampleTypeTests);
        }
    }

    useEffect(() => {
        componentMounted.current = true;
        getFromOpenElisServer(`/rest/sample-type-tests?sampleType=${sampleType.id}`, fetchSamplesTests);
        return () => {
            componentMounted.current = false;
        }
    }, [sampleType.id]);


    const datePicker = (
        <DatePicker dateFormat="m/d/Y" datePickerType="single">
            <DatePickerInput id={`collectionDate_` + index} placeholder="dd/mm/yyyy" type="text" labelText=""
                             style={{width: "140px"}}/>
        </DatePicker>
    );

    const rejectReasons = (
        <Select
            defaultValue="placeholder-item"
            id="select-1"
            labelText="">
            <SelectItem
                text="Option 1"
                value="option-1"
            />
            <SelectItem
                text="Option 2"
                value="option-2"
            />
        </Select>

    );


    const rows = [
        {
            select_radioButton: <RadioButton read id={`select_${index}`} value={index} labelText="" read={"false"}/>,
            sampleType: <TextInput id={`sequence_${index}`} value={index} readOnly={true} style={{width: "90px"}}
                                   labelText=""/>,
            name: <span id={`typeId_${index}`}>{sampleType.name}</span>,
            collectionDate: datePicker,
            collectionTime: <TimePicker id={`collectionTime_${index}`}/>,
            collector: <TextInput id={`collector${index}`} style={{width: "120px"}} labelText=""/>,
            tests: <TextArea rows={3} labelText="" id={`tests_${index}`}/>,
            reject: <Checkbox labelText="" id={`reject_${index}`}/>,
            reason: rejectReasons,
            removeSample: <Link href="#">Remove Sample</Link>
        }
    ];


    return (
        <>
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
                                return <Checkbox labelText={panel.name} id={`panel_` + panel.panelId} key={panel.panelId}/>
                            })
                        }
                    </div>
                    <div className="cds--col">
                        <h2>Available Tests</h2>
                        <TextArea rows={3} labelText="" placeholder="Search"/>
                        <h3>Name</h3>
                        {
                            sampleTypeTests.tests != null && sampleTypeTests.tests.map(test => {
                                return <Checkbox labelText={test.name} id={`test_` + test.id} key={test.id}/>
                            })
                        }
                    </div>
                </div>
            </div>
        </>
    )

}

export default SampleTypes;
