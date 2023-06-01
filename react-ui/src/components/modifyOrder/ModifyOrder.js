import React, {useState,useEffect,useContext} from 'react'
import { Column, Form, Grid} from '@carbon/react';
import SearchPatientForm from '../common/SearchPatientForm';


const ModifyOrder = () => {

    const [selectedPatient, setSelectedPatient] = useState({
        id: "", healthRegion: []
    });
    const getSelectedPatient = (patient) => {
        setSelectedPatient(patient);
    }
    return (<>
        <SearchPatientForm getSelectedPatient={this.getSelectedPatient}></SearchPatientForm>

    </>)
}
export default ModifyOrder;