import React, {useEffect, useRef, useState} from 'react'
import {
    Checkbox,
    Column,
    FilterableMultiSelect,
    Grid,
    IconButton,
    Select,
    SelectItem,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow
} from '@carbon/react';
import {Subtract} from '@carbon/react/icons';
import {getFromOpenElisServer} from "../utils/Utils";
import {TableSampleTableRows} from "./OrderItemsTableRow";
import {sampleTypeTestsStructure} from "../data/SampleEntryTestsForTypeProvider";
import {sampleTypesTableHeader} from '../data/SampleTypesTableHeaders';
import OrderReferralRequest from "./OrderReferralRequest";


const SampleTypes = (props) => {
    const {index, rejectSampleReasons, removeSample} = props;
    const componentMounted = useRef(true);
    const [sampleTypes, setSampleTypes] = useState([]);
    const sampleTypesRef = useRef(null);
    const [selectedSampleType, setSelectedSampleType] = useState({id: null, name: "", element_index: 0});
    const [sampleTypeTests, setSampleTypeTests] = useState(sampleTypeTestsStructure);
    const [selectedTests, setSelectedTests] = useState([]);
    const [searchBoxTests, setSearchBoxTests] = useState([{id: null, label: ''}]);
    const [requestTestReferral, setRequestTestReferral] = useState(false);
    const [referralReasons, setReferralReasons] = useState([]);
    const [referralOrganizations, setReferralOrganizations] = useState([]);


    const handleRemoveSampleTest = (index) => {
        removeSample(index);
    }

    const handleReferralRequest = () => {
        setRequestTestReferral(!requestTestReferral);
    }

    const handleSearchTestsFilter = (selectedItems) => {
        let filtered = [];
        setSelectedTests([]);
        selectedItems.map(item => {
            filtered = [
                ...filtered, {
                    id: item.id,
                    name: item.label
                }]
        });
        setSelectedTests(filtered);
    }

    const handleTestCheckbox = (e, test) => {
        let testIndex = findTestIndex(test.id);
        let tests = [...sampleTypeTests.tests];
        if (e.currentTarget.checked) {
            tests[testIndex].userBenchChoice = true;
            addTestToSelectedTests(test)
        } else {
            tests[testIndex].userBenchChoice = false;
            removedTestFromSelectedTests(test);
        }
        setSampleTypeTests({...sampleTypeTests, tests: tests});
    }

    function removedTestFromSelectedTests(test) {
        const removeUnSelectedTests = selectedTests.filter((oldTest) => oldTest.id !== test.id);
        setSelectedTests(removeUnSelectedTests);
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

    const triggerPanelCheckBoxChange = (userBenchChoiceChecked, testMaps) => {
        const testIds = testMaps.split(',').map(id => id.trim());

        testIds.map(testId => {
            let testIndex = findTestIndex(testId);
            let test = findTestById(testId);
            let tests = [...sampleTypeTests.tests];
            if (testIndex !== -1) {
                tests[testIndex].userBenchChoice = userBenchChoiceChecked;
                setSampleTypeTests({...sampleTypeTests, tests: tests});

                if (userBenchChoiceChecked === true) {
                    setSelectedTests(prevState => {
                        return [...prevState, {id: test.id, name: test.name}]
                    });
                } else {
                    setSelectedTests(prevState => {
                        return [selectedTests.filter((oldTest) => oldTest.id !== test.id)]
                    });
                }
            }

        });


    }

    const handleFetchSampleTypeTests = (e, index) => {
        setSelectedTests([]);
        const {value} = e.target;
        const selectedSampleTypeOption = sampleTypesRef.current.options[sampleTypesRef.current.selectedIndex].text
        setSelectedSampleType({
            ...selectedSampleType,
            id: value,
            name: selectedSampleTypeOption,
            element_index: index
        });
    }

    const rows = TableSampleTableRows(index, selectedSampleType, rejectSampleReasons, selectedTests, handleRemoveSampleTest);

    const fetchSamplesTypes = (res) => {
        if (componentMounted.current) {
            setSampleTypes(res);
        }
    }

    const fetchSampleTypeTests = (res) => {
        if (componentMounted.current) {
            setSampleTypeTests(res);
            let tests = [];
            setSearchBoxTests([]);
            res.tests.map(test => {
                tests = [...tests, {
                    id: test.id,
                    label: test.name
                }];
            });
            setSearchBoxTests(tests);
        }
    }
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

    return (
        <>
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
                                    return (
                                        <Checkbox labelText={panel.name} id={`panel_` + index + "_" + panel.panelId}
                                                  key={index + panel.panelId}
                                                  onChange={(e) => {
                                                      handlePanelChecked(e, panel.testMaps)
                                                  }}/>
                                    );
                                })
                            }
                        </div>
                        <div className="cds--col">
                            <h2>Available Tests</h2>
                            <FilterableMultiSelect
                                ariaLabel="Filterable MultiSelect"
                                id={`tests_search_` + index}
                                items={searchBoxTests}
                                label=""
                                onChange={({selectedItems}) => {
                                    handleSearchTestsFilter(selectedItems);
                                }}
                                titleText="Search Available Test"
                            />
                            <h3>Name</h3>
                            {
                                sampleTypeTests.tests != null && sampleTypeTests.tests.map(test => {
                                    return (
                                        <Checkbox onChange={(e) => handleTestCheckbox(e, test)}
                                                  labelText={test.name}
                                                  id={`test_` + index + "_" + test.id} key={index + test.id}
                                                  checked={test.userBenchChoice}/>
                                    );
                                })
                            }
                        </div>
                    </div>
                </div>
                <div>
                    <Checkbox id={`useReferral_` + index} labelText="Refer test to a reference lab"
                              onChange={handleReferralRequest}/>
                    {requestTestReferral === true &&
                        <OrderReferralRequest index={index} selectedTests={selectedTests}
                                              referralReasons={referralReasons}
                                              referralOrganizations={referralOrganizations}/>}
                </div>
            </div>
            }
        </>
    )

}

export default SampleTypes;
