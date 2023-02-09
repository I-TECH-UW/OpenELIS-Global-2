import React, { useEffect, useRef, useState } from 'react'
import {
    Grid,
    Column,
    Form,
    TextInput,
    Checkbox,
    Link,
    Select,
    SelectItem,
    DatePicker,
    DatePickerInput,
    TimePicker
} from '@carbon/react';
import { getFromOpenElisServer, postToOpenElisServer } from '../utils/Utils';

import config from '../../config.json';
import { priorities } from "../data/orderOptions";
import { orderValues } from "../formModel/innitialValues/CreateOrderFormValues";

const CreateOrder = () => {
    const componentMounted = useRef(true);
    const [formValues, setFormValues] = useState(orderValues);
    const [programs, setPrograms] = useState([]);


    const handleDatePickerChange = (date, value) => {
        setFormValues({
            ...formValues,
            date: value
        });
    }

    const fetchPrograms = (programsList) => {
        if (componentMounted.current) {
            setPrograms(programsList);
        }
    }

    useEffect(() => {
        getFromOpenElisServer("/rest/dictionary?category=programs", fetchPrograms)

        return () => {
            componentMounted.current = false;
        }
    }, [programs]);


    return (<>
        <Grid fullWidth={true} className="gridBoundary">
            <Column lg={12}>
                <Form>
                    <div className="formInlineDiv">
                        <Checkbox labelText="Remember site and requester" id="rememberSiteAndRequester" />
                    </div>

                    <div className="formInlineDiv">
                        <Grid>
                            <Column lg={12}>
                                <input type="button" value="-" />
                                Order <span className="requiredFieldIndicator"> *</span>
                            </Column>
                        </Grid>

                    </div>

                    <div className="formInlineDiv">
                        <Grid>
                            <Column md={6}>
                                <TextInput name="labNo" labelText="Lab No: "
                                    id="labNo" className="inputText" />
                            </Column>
                            <Column md={6}>
                                Scan Or Enter Manually Or
                                <Link href="#">Generate</Link>
                            </Column>
                        </Grid>

                    </div>

                    <div className="formInlineDiv">
                        <Select
                            id="priorityId"
                            name="priority"
                            labelText="priority"
                            required>
                            {priorities.map((priority, index) => {
                                return (<SelectItem key={index}
                                    text={priority.label}
                                    value={priority.value}
                                />);
                            })}
                        </Select>
                    </div>
                    <div className="formInlineDiv">
                        <DatePicker value={formValues.requestDate}
                            name="requestDate" onChange={(e) => handleDatePickerChange("requestDate", e)}
                            dateFormat="d/m/Y" datePickerType="single" light={true} className="">
                            <DatePickerInput
                                id="date-picker-default-id"
                                placeholder="dd/mm/yyyy"
                                labelText="Request Date"
                                type="text"
                                name="requestDate"
                            />
                        </DatePicker>

                        <DatePicker value={formValues.receivedDate}
                            name="requestDate" onChange={(e) => handleDatePickerChange("receivedDate", e)}
                            dateFormat="d/m/Y" datePickerType="single" light={true} className="">
                            <DatePickerInput
                                id="date-picker-default-id"
                                placeholder="dd/mm/yyyy"
                                labelText="Received Date"
                                type="text"
                                name="receivedDate"
                            />
                        </DatePicker>
                    </div>

                    <div className="formInlineDiv">
                        <TimePicker id="time-picker" labelText="Reception Time (hh:mm):" />

                        <DatePicker value={formValues.nextVisitDate}
                            name="nextVisitDate" onChange={(e) => handleDatePickerChange("nextVisitDate", e)}
                            dateFormat="d/m/Y" datePickerType="single" light={true} className="">
                            <DatePickerInput
                                id="date-picker-default-id"
                                placeholder="dd/mm/yyyy"
                                labelText="Date Of Next Visit"
                                type="text"
                                name="nextVisitDate"
                            />
                        </DatePicker>
                    </div>
                    <div className="formInlineDiv">
                        <TextInput name="siteName" labelText="Site Name: "
                            id="siteName" className="inputText" />
                        <Select
                            id="requesterDepartmentId"
                            name="requesterDepartmentId"
                            labelText="ward/dept/unit:"
                            required>
                            <SelectItem
                                value=""
                                text=""
                            />
                        </Select>
                    </div>

                    <div className="formInlineDiv">

                        <Select
                            id="programId"
                            name="program"
                            labelText="program:"
                            required>
                            {
                                programs.map(program => {
                                    return (
                                        <SelectItem key={program.id}
                                            value={program.id}
                                            text={program.value} />
                                    )
                                })
                            }
                        </Select>
                    </div>

                    <div className="formInlineDiv">

                        <Select
                            id="requesterId"
                            name="requester"
                            labelText="Requester"
                            required>
                            <SelectItem
                                value=""
                                text=""
                            />
                        </Select>

                        <TextInput name="requesterLastName" labelText="Requester's LastName: "
                            id="requesterLastName" className="inputText" />

                    </div>
                    <div className='formInlineDiv'>

                        <TextInput name="requesterFirstName" labelText="Requester's FirstName: "
                            id="requesterFirstName" className="inputText" />
                       
                        <TextInput name="providerWorkPhoneID" labelText="Requester Phone: +225-xx-xx-xx-xx: "
                            id="providerWorkPhone" className="inputText" />
                    </div>
                </Form>
            </Column>
        </Grid>
    </>)
}

export default CreateOrder;
