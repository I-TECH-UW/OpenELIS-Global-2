import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import '../Style.css'

import { getFromOpenElisServer } from '../utils/Utils';
import { Add, Subtract } from '@carbon/react/icons';
import ContainedList from '@carbon/react/lib/components/ContainedList';
import ContainedListItem from '@carbon/react/lib/components/ContainedList';
import OverflowMenu from '@carbon/react/lib/components/ContainedList';
import OverflowMenuItem from '@carbon/react/lib/components/ContainedList';
import {
    // usePrefix,
    Heading,
    Form,
    FormLabel,
    TextInput,
    TextArea,
    Checkbox,
    Button,
    Grid,
    Column,
    DatePicker,
    DatePickerInput,
    RadioButton,
    RadioButtonGroup,
    Stack,
    // DataTable, 
    TableContainer, Table, TableHead, TableRow, TableHeader, TableBody, TableCell,
    Section,
    Pagination,
    Header,
    Select,
    SelectItem,
} from '@carbon/react';

import DataTable
    from 'react-data-table-component';

// import { resultSearchHeaderData } from '../data/ResultsTableHeaders';
// import { columns } from '../data/TestTableHeaders';
import { Formik, Field, FieldArray, ErrorMessage } from "formik";
import SearchResultFormValues from '../formModel/innitialValues/SearchResultFormValues';
import { faDatabase } from '@fortawesome/free-solid-svg-icons';

const columns = [
    {
        name: 'Sample Info',
        cell: (row, index, column, id) => {
            return renderCell(row, index, column, id);
        },
        sortable: true,
        width: "19rem"
    },
    {
        name: 'Test Date',
        selector: row => row.testDate,
        sortable: true,
        width: "7rem"
    },
    {
        name: 'Methods',
        cell: (row, index, column, id) => {
            return renderCell(row, index, column, id);
        },
        width: "12rem",
        sortable: true,
    },
    {
        name: "Analyzer Result",
        selector: row => row.analysisMethod,
        sortable: true,
        width: "7rem",
    },
    {
        name: 'Test Name',
        selector: row => row.testName,
        sortable: true,
        width: "10rem",
    },
    {
        name: 'Normal Range',
        selector: row => row.normalRange,
        sortable: true,
        width: "7rem",
    },
    {
        name: 'Accept as is',
        cell: (row, index, column, id, resultForm) => {
            return renderCell(row, index, column, id);
        },
        width: "7rem",

    },
    {
        name: 'Shadow Result',
        cell: (row, index, column, id, resultForm) => {
            return renderCell(row, index, column, id);
        },
        width: "10rem",
    },
    {
        name: 'Current Result',
        cell: (row, index, column, id, resultForm) => {
            return renderCell(row, index, column, id);
        },
        width: "10rem",
    },
];

function renderCell(row, index, column, id) {
    switch (column.name) {
        case "Sample Info":
            // return <input id={"results_" + id} type="text" size="6"></input>  
            return (
                <>
                    <div className='sampleInfo'>
                        <TextArea value={row.sequenceAccessionNumber + "\n" + row.patientName + "\n" + row.patientInfo}
                            disabled={true} type="text" labelText="" rows={3} >
                        </TextArea>
                    </div>
                </>
            );

        case "Accept as is":
            return (
                <>
                    <Field name="acceptAsIs" >
                        {({ field }) =>
                            <Checkbox
                                id={field.name}
                                name={field.name}
                                labelText=""
                            // defaultChecked={this.state.acceptAsIs}
                            // onChange={this.handleAcceptAsIs}
                            />
                        }
                    </Field>
                </>
            );

        case "Shadow Result":
            switch (row.resultType) {
                case "D":
                    return <Select
                        id={"results_" + id}
                        name={"testResult[" + id + "].resultValue"}
                        noLabel={true} >
                        {/* onChange={(e) => markUpdated(e, param.rowId, false, '')}{...updateShadowResult(e, this, param.rowId)} */}
                        <SelectItem
                            text=""
                            value=""
                        />
                        {row.dictionaryResults.map((dictionaryResult, dictionaryResult_index) => (
                            <SelectItem
                                text={dictionaryResult.value}
                                value={dictionaryResult.id}
                                key={dictionaryResult_index}
                            />
                        ))}
                    </Select>

                case "N":
                    return <input id={"results_" + id} name="testResult[0].resultValue" />
                // <input id={"results_" + param.rowId} type="text" size="6"></input>

                //         <input id="results_0" name="testResult[0].resultValue" class="resultValue" 
                // style="background: rgb(255, 255, 255);" onchange="validateResults( this, 0, 7.0, 40.0, 7.0, 350.0, 0, 'XXXX' );
                // 		   			 markUpdated(0);
                // 		   			 updateShadowResult(this, 0);" type="text" value="" size="6" title=""></input>
                default:
                    return row.resultValue
            }
        case "Current Result":
            return <input id={"results_" + id} type="text" size="6"></input>
        case "Methods":
            return <Select
                id={"methods"}
                name={"methods"}
                noLabel={true} >
                {/* onChange={(e) => markUpdated(e, param.rowId, false, '')}{...updateShadowResult(e, this, param.rowId)} */}
                <SelectItem
                    text=""
                    value=""
                />
                {row.methods.map((method, method_index) => (
                    <SelectItem
                        text={method.value}
                        value={method.id}
                        key={method_index}
                    />
                ))}
            </Select>
    }
    return row.resultValue;
}

const ExpandedComponent = ({ data }) => <pre>
    <div className='expandedComponent'>
        <Grid >
            <Column lg={3}>
                <div >
                    <Select className='referralReason'
                        id={"referralReason" + data.id}
                        name={"referralReason" + data.id}
                        // noLabel={true} 
                        labelText={"Referral Reason"}>
                        {/* onChange={(e) => markUpdated(e, param.rowId, false, '')}{...updateShadowResult(e, this, param.rowId)} */}
                        <SelectItem
                            text=""
                            value=""
                        />
                        {data.referralReasons.map((method, method_index) => (
                            <SelectItem
                                text={method.value}
                                value={method.id}
                                key={method_index}
                            />
                        ))}
                    </Select>
                </div>
            </Column>
            <Column lg={3}>
                <div className='institute'>
                    <Select
                        id={"institute" + data.id}
                        name={"institute" + data.id}
                        // noLabel={true} 
                        labelText={"Institute"}>
                        {/* onChange={(e) => markUpdated(e, param.rowId, false, '')}{...updateShadowResult(e, this, param.rowId)} */}

                        <SelectItem
                            text=""
                            value=""
                        />
                        {data.referralOrganizations.map((method, method_index) => (
                            <SelectItem
                                text={method.value}
                                value={method.id}
                                key={method_index}
                            />
                        ))}
                    </Select>
                </div>
            </Column>
            <Column lg={3}>
                <div className='testToPerform'>
                    <Select
                        id={"testToPerform" + data.id}
                        name={"testToPerform" + data.id}
                        // noLabel={true} 
                        labelText={"Test to Perform"}>
                        {/* onChange={(e) => markUpdated(e, param.rowId, false, '')}{...updateShadowResult(e, this, param.rowId)} */}

                        <SelectItem
                            text={data.testName}
                            value={data.id}
                        />
                    </Select>
                </div>
            </Column>
            <Column lg={3}>
                <DatePicker datePickerType="single"
                    id={"sentDate_" + data.id}
                    name={"sentDate_" + data.id} >
                    <DatePickerInput
                        placeholder="mm/dd/yyyy"
                        labelText="Sent Date"
                        id="date-picker-single"
                        size="md"
                    />
                </DatePicker>
            </Column>

        </Grid>
    </div >





    {/* {JSON.stringify(data, null, 2)}  */}
</pre >;



class CodeTestForm extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            dob: "",
            resultForm: { testResult: [] },
            tableTitle: "",
            page: 1,
            pageSize: 100,
            doRange: true,
            acceptAsIs: true,
        }
    }

    handleDoRangeChange = () => {
        this.state.doRange = !this.state.doRange;
    }

    handleAcceptAsIsChange = () => {
        this.state.acceptAsIs = !this.state.acceptAsIs;
    }

    handleSubmit = (values) => {
        values.dateOfBirth = this.state.dob
        // console.log("handleSubmit:" + this.state.doRange)
        this.setState({ resultForm: { testResult: [] }, });

        var searchEndPoint = "/rest/ReactLogbookResultsByRange?" +
            "&labNumber=" + values.labNumber +
            "&doRange=" + this.state.doRange
        getFromOpenElisServer(searchEndPoint, this.setResults);
    };

    setResults = (resultForm) => {
        //console.log(JSON.stringify(result))
        var i = 0;
        resultForm.testResult.forEach(item => item.id = "" + i++);
        this.setState({ resultForm: resultForm })
    }

    handleDatePickerChange = (...e) => {
        this.setState({
            dob: e[1],
        });
    }

    handlePageChange = (pageInfo) => {
        if (this.state.page != pageInfo.page) {
            this.setState({ page: pageInfo.page });
        }
        if (this.state.pageSize != pageInfo.pageSize) {
            this.setState({ pageSize: pageInfo.pageSize });
        }
    };

    handlePerPageChange = (newPerPage) => {
        this.setState({ perPage: newPerPage });
    };

    sampleDetails = (param) => {
        //console.log("sampleDetails:param.rowId:" + param.rowId);
        // todo
        // if (param.resultForm.testResult[param.rowId] === undefined)
        //     return
        if (param.resultForm.testResult[param.rowId].showSampleDetails == true)
            param.row.cells[0].value =
                param.resultForm.testResult[param.rowId].sequenceAccessionNumber + " " +
                param.resultForm.testResult[param.rowId].patientName + " " +
                param.resultForm.testResult[param.rowId].patientInfo
        return
    };

    renderResultValue = (param) => {
        // todo
        // if (param.resultForm.testResult[param.rowId] === undefined)
        //     return
        return <input id={"results_" + param.rowId} type="text" size="6"></input>
    }

    renderShadowResultValue = (param) => {
        // if (param.resultForm.testResult[param.rowId] === undefined)
        //     return
        switch (param.resultForm.testResult[param.rowId].resultType) {
            case "D":
                return <Select >
                    id={"results_" + param.rowId}
                    name={"testResult[" + param.rowId + "].resultValue"}
                    noLabel={false}
                    labelText="labelText"
                    {/* onChange={(e) => markUpdated(e, param.rowId, false, '')}{...updateShadowResult(e, this, param.rowId)} */}

                    <SelectItem
                        text=""
                        value=""
                    />
                    {param.resultForm.testResult[param.rowId].dictionaryResults.map((dictionaryResult, dictionaryResult_index) => (
                        <SelectItem
                            text={dictionaryResult.value}
                            value={dictionaryResult.id}
                            key={dictionaryResult_index}
                        />
                    ))}
                </Select>

            case "N":
                return <input id={"results_" + param.rowId} name="testResult[0].resultValue" />
            // <input id={"results_" + param.rowId} type="text" size="6"></input>

            //         <input id="results_0" name="testResult[0].resultValue" class="resultValue"
            // style="background: rgb(255, 255, 255);" onchange="validateResults( this, 0, 7.0, 40.0, 7.0, 350.0, 0, 'XXXX' );
            // 		   			 markUpdated(0);
            // 		   			 updateShadowResult(this, 0);" type="text" value="" size="6" title=""></input>
            default:
                return param.cell.value
        }
    }


    myComponent() {
        return (
            <>
                <Formik
                    initialValues={SearchResultFormValues}
                    //validationSchema={}
                    onSubmit={this.handleSubmit}
                    onChange
                >
                    {({ values,
                        errors,
                        touched,
                        handleChange,
                        handleBlur,
                        handleSubmit }) => (

                        <Form
                            onSubmit={handleSubmit}
                            onChange={handleChange}
                            onBlur={handleBlur}>

                            <DataTable
                                data={this.state.resultForm.testResult}
                                columns={columns} isSortable
                                expandableRows
                                expandableRowsComponent={ExpandedComponent}>
                            </DataTable><Pagination onChange={this.handlePageChange} page={this.state.page} pageSize={this.state.pageSize}
                                pageSizes={[100]} totalItems={this.state.resultForm.testResult.length}></Pagination>

                            <Button type="save" id="save">
                                <FormattedMessage id="label.button.save" />
                            </Button>
                        </Form>)}
                </Formik>
            </>
        );
    };

    render() {
        const { page, pageSize } = this.state;
        // const prefix = this.state.prefix;
        return (
            <>
                {/* <Grid  fullWidth={true} className="gridBoundary"> */}
                {/* <Column  lg={3}> */}
                <Formik
                    initialValues={SearchResultFormValues}
                    //validationSchema={}
                    onSubmit={this.handleSubmit}
                    onChange
                >
                    {({ values,
                        errors,
                        touched,
                        handleChange,
                        handleBlur,
                        handleSubmit }) => (

                        <Form
                            onSubmit={handleSubmit}
                            onChange={handleChange}
                            onBlur={handleBlur}>

                            <Stack gap={2}>
                                <FormLabel>
                                    <Section>
                                        <Section>
                                            <Section>
                                                <Heading>
                                                    <FormattedMessage id="label.button.range" />
                                                </Heading>
                                            </Section>
                                        </Section>
                                    </Section>
                                </FormLabel>
                                <Field name="labNumber"
                                >
                                    {({ field }) =>
                                        <TextInput name={field.name} labelText="Lab Number" id={field.name} className="inputText" />
                                    }
                                </Field>
                                <Field name="doRange"
                                >
                                    {({ field }) =>
                                        <Checkbox
                                            defaultChecked={this.state.doRange}
                                            onChange={this.handleDoRangeChange}
                                            name={field.name}
                                            labelText="Do Range"
                                            id={field.name} />
                                    }
                                </Field>
                                <Button type="submit" id="submit">
                                    <FormattedMessage id="label.button.search" />
                                </Button>
                            </Stack>
                        </Form>
                    )}
                </Formik>
                {/* </Column> */}
                {/* <Column></Column> */}
                {/* <Column  lg={12} > */}

                {this.myComponent()}

                {/* </Column> */}
                {/* </Grid> */}
            </>

        );

    }
}

export default injectIntl(CodeTestForm)