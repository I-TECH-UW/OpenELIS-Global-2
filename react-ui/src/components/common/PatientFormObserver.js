import React, {useEffect} from 'react';
import {useFormikContext} from "formik";

const PatientFormObserver = (props) => {
    const {values} = useFormikContext();
    const {orderFormValues, setOrderFormValues} = props;

    useEffect(() => {
        setOrderFormValues({
            ...orderFormValues,
            patientProperties: {
                ...orderFormValues.patientProperties,
                patientUpdateStatus: "UPDATE",
                nationalId: values.nationalID,
                subjectNumber: values.subjectNumber,
                lastName: values.lastName,
                firstName: values.firstName,
                streetAddress: values.street,
                city: values.city,
                primaryPhone: values.phoneNumber,
                gender: values.gender,
                birthDateForDisplay: values.dob,
                commune: values.commune,
                education: values.education,
                maritialStatus: values.maritialStatus,
                nationality: values.nationality,
                healthDistrict: values.healthDistrict,
                healthRegion: values.healthRegion,
                patientContact: values.contactFirstName + " " + values.contactLastName,
                otherNationality: values.otherNationality,
            }
        });
    }, [values]);
    return null;
};

export default PatientFormObserver;
