import React, {useState,useEffect,useContext} from 'react'
import { Column, Form, Grid} from '@carbon/react';
import SearchPatientForm from '../common/SearchPatientForm';


class  ModifyOrder extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            selectedPatient: {}
        }
    }


    getSelectedPatient = (patient) => {
        this.setState({ selectedPatient: patient })
    }

    render() {
        return (
            <>
                <SearchPatientForm getSelectedPatient={this.getSelectedPatient}></SearchPatientForm>          
            </>
        );
    }
}
export default ModifyOrder;