import React, {useEffect, useRef, useState} from 'react'
import {getFromOpenElisServer} from "../utils/Utils";
import {Button, Select, SelectItem, Stack} from "@carbon/react";
import "./../admin/reflexTests/ReflexRule"
import SearchPatientForm from "../common/SearchPatientForm";
import CreatePatientForm from "../common/CreatePatientForm";

const PatientInfo = (props) => {
    const {orderFormValues, setOrderFormValues} = props;
    const componentMounted = useRef(true);
    const [programs, setPrograms] = useState([]);
    const [searchPatientTab, setSearchPatientTab] = useState({kind: "primary", active: true});
    const [newPatientTab, setNewPatientTab] = useState({kind: "tertiary", active: false});
    const [selectedPatient, setSelectedPatient] = useState({
        id: "", healthRegion: []
    });

    const getSelectedPatient = (patient) => {
        setSelectedPatient(patient);
        handleNewPatientTab();
    }

    function handleProgramOptions(e) {
        setOrderFormValues({
            ...orderFormValues, sampleOrderItems: {
                ...orderFormValues.sampleOrderItems, program: e.target.value
            }
        });
    }

    const handleSearchPatientTab = () => {
        setSearchPatientTab({kind: "primary", active: true});
        setNewPatientTab({kind: "tertiary", active: false});
    }
    const handleNewPatientTab = () => {
        setNewPatientTab({kind: "primary", active: true});
        setSearchPatientTab({kind: "tertiary", active: false});
    }

    const getSampleEntryPreform = (response) => {
        if (componentMounted.current) {
            setPrograms(response.sampleOrderItems.programList);
        }
    }

    useEffect(() => {
        getFromOpenElisServer("/rest/SamplePatientEntry", getSampleEntryPreform);
        if (orderFormValues.patientProperties.firstName !== "" || orderFormValues.patientProperties.guid !== "") {
            handleNewPatientTab();
        }
        return () => {
            componentMounted.current = false
        }
    }, []);

    return (<>
        <div className="rules">
            <div className="inlineDiv">
                <Select
                    id="programId"
                    name="program"
                    labelText="program:"
                    value={orderFormValues.sampleOrderItems.program}
                    onChange={handleProgramOptions}
                    required>
                    {programs.map(program => {
                        return (<SelectItem key={program.id}
                                            value={program.id}
                                            text={program.value}/>)
                    })}
                </Select>
            </div>
        </div>

        <Stack gap={10}>
            <div className="orderLegendBody">
                <h3>PATIENT</h3>
                <div className="tabsLayout">
                    <Button kind={searchPatientTab.kind} onClick={handleSearchPatientTab}>Search for
                        Patient</Button>
                    <Button kind={newPatientTab.kind} onClick={handleNewPatientTab}>New Patient</Button>
                </div>
                <div className="container">
                    {searchPatientTab.active && <SearchPatientForm getSelectedPatient={getSelectedPatient}/>}
                    {newPatientTab.active &&
                        <CreatePatientForm showActionsButton={false} selectedPatient={selectedPatient}
                                           orderFormValues={orderFormValues} setOrderFormValues={setOrderFormValues}
                                           getSelectedPatient={getSelectedPatient}/>}
                </div>
            </div>
        </Stack>
    </>)
}

export default PatientInfo;
