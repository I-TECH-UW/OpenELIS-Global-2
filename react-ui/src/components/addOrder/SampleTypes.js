import React, {useEffect, useRef, useState} from 'react'
import {
    Checkbox,
    Column,
    FormGroup,
    Grid,
    IconButton,
    Layer,
    Search,
    Select,
    SelectItem,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
    Tag,
    Tile
} from '@carbon/react';
import "./add-order.scss";
import {Subtract} from '@carbon/react/icons';
import {getFromOpenElisServer} from "../utils/Utils";
import {TableSampleTableRows} from "./OrderItemsTableRow";
import {sampleTypeTestsStructure} from "../data/SampleEntryTestsForTypeProvider";
import {sampleTypesTableHeader} from '../data/SampleTypesTableHeaders';
import OrderReferralRequest from "./OrderReferralRequest";


const SampleTypes = (props) => {
    const {index, rejectSampleReasons, removeSample, findConfigurationProperty} = props;
    const componentMounted = useRef(true);
    const [sampleTypes, setSampleTypes] = useState([]);
    const sampleTypesRef = useRef(null);
    const [selectedSampleType, setSelectedSampleType] = useState({id: null, name: "", element_index: 0});
    const [sampleTypeTests, setSampleTypeTests] = useState(sampleTypeTestsStructure);
    const [selectedTests, setSelectedTests] = useState([]);
    const [searchBoxTests, setSearchBoxTests] = useState([]);
    const [requestTestReferral, setRequestTestReferral] = useState(false);
    const [referralReasons, setReferralReasons] = useState([]);
    const [referralOrganizations, setReferralOrganizations] = useState([]);
    const [testSearchTerm, setTestSearchTerm] = useState("");

    const handleRemoveSampleTest = (index) => {
        removeSample(index);
    }

    const handleReferralRequest = () => {
        setRequestTestReferral(!requestTestReferral);
    }

    const handleTestSearchChange = (event) => {
        const query = event.target.value;
        setTestSearchTerm(query);
        const results = sampleTypeTests.tests.filter(test => {
            return test.name.toLowerCase().includes(query.toLowerCase());
        });
        setSearchBoxTests(results);
    }
    const handleRemoveSelectedTest = (test) => {
        removedTestFromSelectedTests(test);
        updateSampleTypeTests(test, false);
    }
    const handleFilterSelectTest = (test) => {
        setTestSearchTerm("");
        addTestToSelectedTests(test);
        updateSampleTypeTests(test, true);
    }

    function updateSampleTypeTests(test, userBenchChoice = false) {
        let tests = [...sampleTypeTests.tests];
        let testIndex = findTestIndex(test.id);
        tests[testIndex].userBenchChoice = userBenchChoice;
        setSampleTypeTests({...sampleTypeTests, tests: tests});
    }

    const handleTestCheckbox = (e, test) => {
        if (e.currentTarget.checked) {
            updateSampleTypeTests(test, true);
            addTestToSelectedTests(test)
        } else {
            updateSampleTypeTests(test, false);
            removedTestFromSelectedTests(test);
        }
    }

    function addTestToSelectedTests(test) {
        setSelectedTests([...selectedTests, {id: test.id, name: test.name}]);
    }

    function handlePanelChecked(e, testMaps) {
        if (e.currentTarget.checked) {
            triggerPanelCheckBoxChange(true, testMaps);
        } else {
            triggerPanelCheckBoxChange(false, testMaps);
        }
    }

    function findTestById(testId) {
        return sampleTypeTests.tests.find((test) => test.id === testId);
    }

    function findTestIndex(testId) {
        return sampleTypeTests.tests.findIndex((test) => test.id === testId);
    }

    const triggerPanelCheckBoxChange = (isChecked, testMaps) => {
        const testIds = testMaps.split(',').map(id => id.trim());
        testIds.map(testId => {
            let testIndex = findTestIndex(testId);
            let test = findTestById(testId);
            if (testIndex !== -1) {
                updateSampleTypeTests(test, isChecked);
                if (isChecked) {
                    setSelectedTests(prevState => {
                        return [...prevState, {id: test.id, name: test.name}];
                    });
                } else {
                    removedTestFromSelectedTests(test);
                }
            }

        });


    }

    const removedTestFromSelectedTests = (test) => {
        let index = 0;
        for (let i in selectedTests) {
            if (selectedTests[i].id === test.id) {
                const newTests = selectedTests;
                newTests.splice(index, 1);
                setSelectedTests([...newTests]);
                break;
            }
            index++;
        }
    }

    const handleFetchSampleTypeTests = (e, index) => {
        setSelectedTests([]);
        const {value} = e.target;
        const selectedSampleTypeOption = sampleTypesRef.current.options[sampleTypesRef.current.selectedIndex].text
        setSelectedSampleType({
            ...selectedSampleType, id: value, name: selectedSampleTypeOption, element_index: index
        });
    }

    const rows = TableSampleTableRows(index, selectedSampleType, rejectSampleReasons, selectedTests, findConfigurationProperty, handleRemoveSampleTest);

    const fetchSamplesTypes = (res) => {
        if (componentMounted.current) {
            setSampleTypes(res);
        }
    }

    const fetchSampleTypeTests = (res) => {
        if (componentMounted.current) {
            setSampleTypeTests(res);
        }
    }
    useEffect(() => {
        props.sampleTypeObject(selectedTests, index);
    }, [selectedTests]);

    useEffect(() => {
        getFromOpenElisServer("/rest/samples", fetchSamplesTypes);
        return () => {
            componentMounted.current = false;
        }
    }, []);

    useEffect(() => {
        componentMounted.current = true;
        if (selectedSampleType.id != null) {
            getFromOpenElisServer(`/rest/sample-type-tests?sampleType=${selectedSampleType.id}`, fetchSampleTypeTests);
        }
        return () => {
            componentMounted.current = false;
        }
    }, [selectedSampleType.id]);

    const displayReferralReasonsOptions = (res) => {
        if (componentMounted.current) {
            setReferralReasons(res);
        }
    }
    const displayReferralOrgOptions = (res) => {
        if (componentMounted.current) {
            setReferralOrganizations(res);
        }
    }

    useEffect(() => {
        getFromOpenElisServer("/rest/referral-reasons", displayReferralReasonsOptions);
        getFromOpenElisServer("/rest/referral-organizations", displayReferralOrgOptions);
        return () => {
            componentMounted.current = false
        }
    }, []);

    return (<>
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
                {sampleTypes.map((sample, sample_index) => (<SelectItem
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
                                    </TableHeader>))}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {rows.map((row, table_index) => (<TableRow key={table_index}>
                                {Object.keys(row)
                                    .filter((key) => key !== 'id')
                                    .map((key) => {
                                        return <TableCell key={key}>{row[key]}</TableCell>;
                                    })}
                            </TableRow>))}
                        </TableBody>
                    </Table>

                </Column>
            </Grid>
            <div className="cds--grid">
                <div className="cds--row">
                    <div className="cds--col">
                        <h2>Panels</h2>
                        <h3>Name</h3>
                        {sampleTypeTests.panels != null && sampleTypeTests.panels.map(panel => {
                            return (<Checkbox labelText={panel.name} id={`panel_` + index + "_" + panel.panelId}
                                              key={index + panel.panelId}
                                              onChange={(e) => {
                                                  handlePanelChecked(e, panel.testMaps)
                                              }}/>);
                        })}
                    </div>
                    <div className="cds--col">
                        {selectedTests && !selectedTests.length ? "" : <h3>Ordered Tests</h3>}
                        <div className={"searchTestText"} style={{marginBottom: '1.188rem'}}>
                            {selectedTests && selectedTests.length ? (
                                <>
                                    {selectedTests.map((test, index) => (
                                        <Tag
                                            filter
                                            key={index}
                                            onClose={() => handleRemoveSelectedTest(test)}
                                            style={{marginRight: '0.5rem'}}
                                            type={'red'}
                                        >
                                            {test.name}
                                        </Tag>
                                    ))}
                                </>
                            ) : (<></>)}
                        </div>
                        <FormGroup legendText={'Search through the available tests'}>
                            <Search
                                size="lg"
                                id={`tests_search_` + index}
                                labelText={'Search Available Test'}
                                placeholder={'Choose Available test'}
                                onChange={handleTestSearchChange}
                                value={(() => {
                                    if (testSearchTerm) {
                                        return testSearchTerm;
                                    }
                                    return '';
                                })()}
                            />
                            <div>
                                {(() => {
                                    if (!testSearchTerm) return null;
                                    if (searchBoxTests && searchBoxTests.length) {
                                        return (
                                            <ul className={"searchTestsList"}>
                                                {searchBoxTests.map((test, index) => (
                                                    <li
                                                        role="menuitem"
                                                        className={"singleTest"}
                                                        key={index}
                                                        onClick={() => handleFilterSelectTest(test)}
                                                    >
                                                        {test.name}
                                                    </li>
                                                ))}
                                            </ul>
                                        );
                                    }
                                    return (
                                        <>
                                            <Layer>
                                                <Tile className={"emptyFilterTests"}>
                                             <span>No test found matching
                                                 <strong> "{testSearchTerm}"</strong>  </span>
                                                </Tile>
                                            </Layer>
                                        </>
                                    );
                                })()}
                            </div>
                        </FormGroup>
                        <h3>Available Tests</h3>
                        {sampleTypeTests.tests != null && sampleTypeTests.tests.map(test => {
                            return (<Checkbox onChange={(e) => handleTestCheckbox(e, test)}
                                              labelText={test.name}
                                              id={`test_` + index + "_" + test.id} key={index + test.id}
                                              checked={test.userBenchChoice}/>);
                        })}
                    </div>
                </div>
            </div>
            <div>
                <Checkbox id={`useReferral_` + index} labelText="Refer test to a reference lab"
                          onChange={handleReferralRequest}/>
                {
                    requestTestReferral === true && <OrderReferralRequest index={index} selectedTests={selectedTests}
                                                                          referralReasons={referralReasons}
                                                                          referralOrganizations={referralOrganizations}/>
                }
            </div>
        </div>}
    </>)

}

export default SampleTypes;
