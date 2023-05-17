import React, {useEffect, useRef, useState} from 'react'
import {DatePicker, DatePickerInput} from "@carbon/react";
import {getFromOpenElisServer} from "../utils/Utils";
import {format} from 'date-fns';

const CustomDatePicker = (props) => {
    const componentMounted = useRef(true);
    const [configurationProperties, setConfigurationProperties] = useState([]);
    const [currentDate, setCurrentDate] = useState(null);

    function handleDatePickerChange(e) {
        let date = new Date(e[0]);
        const formatDate = format(new Date(date), 'dd/MM/yyyy')
        setCurrentDate(formatDate);
        props.onChange(currentDate);
    }

    function findConfigurationProperty(property) {
        if (configurationProperties.length > 0) {
            const filterProperty = configurationProperties.find((config) => config.id === property);
            if (filterProperty !== undefined) {
                return filterProperty.value
            }
        }
    }

    const fetchConfigurationProperties = (res) => {
        if (componentMounted.current) {
            setConfigurationProperties(res);
        }
    }
    useEffect(() => {
        const date = findConfigurationProperty("currentDateAsText");
        setCurrentDate(date);
    }, [configurationProperties]);

    useEffect(() => {
        getFromOpenElisServer("/rest/configuration-properties", fetchConfigurationProperties);
        return () => {
            componentMounted.current = false
        }
    }, []);

    useEffect(() => {
        props.onChange(currentDate);
    }, [currentDate]);

    return (
        <>
            <DatePicker id={props.id} dateFormat="d/m/Y" datePickerType="single" value={currentDate}
                        onChange={(e) => handleDatePickerChange(e)}>
                <DatePickerInput id={props.id} placeholder="dd/mm/yyyy" type="text" labelText={props.labelText}/>
            </DatePicker>
        </>
    )
}

export default CustomDatePicker;
