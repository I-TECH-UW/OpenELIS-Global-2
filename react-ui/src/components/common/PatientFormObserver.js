import React, {useEffect} from 'react';
import {useFormikContext} from "formik";

const PatientFormObserver = (props) => {
    const {values} = useFormikContext();
    const {orderFormValues, setOrderFormValues,formAction} = props;
    useEffect(() => {
        setOrderFormValues({
            ...orderFormValues,
            patientUpdateStatus: formAction,
            patientProperties: {
                ...orderFormValues.patientProperties,
                patientUpdateStatus: formAction,
                nationalId: values.nationalID,
                subjectNumber: values.subjectNumber,
                patientPK: values.id,
                guid:values.guid,
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
                /**To..do */
                // patientContact: values.contactFirstName + " " + values.contactLastName,
                otherNationality: values.otherNationality,
            }
        });
    }, [values]);
    return null;
};

export default PatientFormObserver;
