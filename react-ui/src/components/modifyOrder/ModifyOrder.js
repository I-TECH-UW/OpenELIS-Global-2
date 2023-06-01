import React, {useState,useEffect,useContext} from 'react'
import { Column, Form, Grid} from '@carbon/react';
import ModifyOrderForm from "./ModifyOrderForm";


const ModifyOrder = () => {

    const [selectedPatient, setSelectedPatient] = useState({
        id: "", healthRegion: []
    });
    const getSelectedPatient = (patient) => {
        setSelectedPatient(patient);
    }
    return (<>
        <ModifyOrderForm getSelectedPatient={getSelectedPatient}></ModifyOrderForm>
    </>)
}
export default ModifyOrder;