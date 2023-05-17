import React, {useEffect, useRef, useState} from 'react'
import {getFromOpenElisServer} from "../utils/Utils";
import {TimePicker} from "@carbon/react";

const CustomTimePicker = (props) => {
    const componentMounted = useRef(true);
    const [configurationProperties, setConfigurationProperties] = useState([]);
    const [currentTime, setCurrentTime] = useState(null);

    function handleTimePicker(e) {
        let time = e.target.value
        setCurrentTime(time);
        props.onChange(time);
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
        const time = findConfigurationProperty("currentTimeAsText");
        setCurrentTime(time);
    }, [configurationProperties]);

    useEffect(() => {
        getFromOpenElisServer("/rest/configuration-properties", fetchConfigurationProperties);
        return () => {
            componentMounted.current = false
        }
    }, []);

    useEffect(() => {
        props.onChange(currentTime);
    }, [currentTime]);

    return (
        <>
            <TimePicker id={props.id} value={currentTime == null ? '' : currentTime}
                        onChange={(e) => handleTimePicker(e)}/>
        </>
    )
}

export default CustomTimePicker;
