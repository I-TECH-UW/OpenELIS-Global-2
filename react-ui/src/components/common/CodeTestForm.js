import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import "../Style.css";
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
        grow: true,
        maxWidth: "20",
    },
    {
        name: 'Test Date',
        selector: row => row.testDate,
        sortable: true,
        maxWidth: "20",
    },
    {
        name: 'Methods',
        cell: (row, index, column, id) => {
            return renderCell(row, index, column, id);
        },
        minWidth: "20",
        maxWidth: "20",
        flex_grow: true,
        sortable: true,
    },
    {
        name: 'Result from analyzer',
        selector: row => row.analysisMethod,
        sortable: true,
        maxWidth: "6",
    },
    {
        name: 'Test Name',
        selector: row => row.testName,
        sortable: true,
        maxWidth: "20",
    },
    {
        name: 'Normal Range',
        selector: row => row.normalRange,
        sortable: true,
        maxWidth: "20",
    },
    {
        name: 'Accept as is',
        selector: row => row.forceTechApproval,
        sortable: true,
        maxWidth: "6",
    },
    {
        name: 'Shadow Result',
        cell: (row, index, column, id, resultForm) => {
            return renderCell(row, index, column, id);
        },
    },
    {
        name: 'Current Result',
        cell: (row, index, column, id, resultForm) => {
            return renderCell(row, index, column, id);
        },
    },
];

function renderCell(row, index, column, id) {
    switch (column.name) {
        case "Sample Info":
            // return <input id={"results_" + id} type="text" size="6"></input>  
            return <TextArea value={row.sequenceAccessionNumber + " " + row.patientName + " " + row.patientInfo}
                disabled={true} size="md" type="text" labelText="" >
            </TextArea>

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
    <Select
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
    </Select><Select
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
    {/* <Select
        id={"testToPerform" + data.id}
        name={"testToPerform" + data.id}
        // noLabel={true} 
        labelText={"Test to Perform"}>
        {/* onChange={(e) => markUpdated(e, param.rowId, false, '')}{...updateShadowResult(e, this, param.rowId)} 

        <SelectItem
            text=""
            value=""
        />
        {data.dictionaryResults.map((method, method_index) => (
            <SelectItem
                text={method.value}
                value={method.id}
                key={method_index}
            />
        ))}
    </Select> */}
    Test to Perform:
    <input
        id={"sentDate_" + data.id}
        name={"sentDate_" + data.id} 
        value={data.testName} >
    </input>
    Sent Date:
    <input
        id={"sentDate_" + data.id}
        name={"sentDate_" + data.id} >
    </input>

    {/* {JSON.stringify(data, null, 2)}  */}
</pre>;



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
        }
    }

    handleDoRangeChange = () => {
        this.state.doRange = !this.state.doRange;
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
                <DataTable
                    data={this.state.resultForm.testResult}
                    columns={columns} isSortable
                    expandableRows
                    expandableRowsComponent={ExpandedComponent}>

                </DataTable><Pagination onChange={this.handlePageChange} page={this.state.page} pageSize={this.state.pageSize}
                    pageSizes={[100]} totalItems={this.state.resultForm.testResult.length}></Pagination>
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
                                        <Checkbox defaultChecked={this.state.doRange} onChange={this.handleDoRangeChange} name={field.name} labelText="Do Range" id={field.name} />
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