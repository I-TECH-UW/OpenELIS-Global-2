import React from 'react';
import { FormattedMessage, injectIntl } from 'react-intl';
import "../Style.css";
import { getFromOpenElisServer } from '../utils/Utils';
import {
    Heading,
    Form,  
    FormLabel,
    TextInput,
    Checkbox,
    Button,
    Stack,
    DataTable, TableContainer, Table, TableHead, TableRow, TableHeader, TableBody, TableCell,
    Section,
    Pagination,
    Select,
    SelectItem,

} from '@carbon/react';

import { resultSearchHeaderData } from '../data/ResultsTableHeaders';
import { Formik, Field } from "formik";
import SearchResultFormValues from '../formModel/innitialValues/SearchResultFormValues';

class SearchResultRangeForm extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            dob: "",
            resultForm: { testResult: [] },
            tableTitle: "",
            page: 1,
            pageSize: 100,
            doRange: true,
            // methods: [],
            // analysisMethodChange: [],
            // forceTechApprovalChange: [],
        }
    }

    handleDoRangeChange = () => {
        console.log("handleDoRangeChange:" + this.state.doRange)
        this.state.doRange = !this.state.doRange;
    }

    handleAnalysisMethodChange = () => {
        console.log("handleAnalysisMethodChange:")
        this.state.analysisMethodChange = !this.state.analysisMethodChange;
    }

    handleForceTechApprovalChange = () => {
        console.log("handleForceTechApprovalChange:")
        this.state.forceTechApprovalChange = !this.state.forceTechApprovalChange;
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
        if (param.resultForm.testResult[param.rowId] === undefined)
            return
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

    handleResultValueChange = () => {
        console.log("handleResultValueChange:")
        // this.state.resultForm.testResult[param.rowId].methods = param.resultForm.testResult[param.rowId].methods;
        // console.log("handleMethodsChange1:" + this.state.resultForm.testResult[param.rowId].methods)
    }

    renderShadowResultValue = (param) => {
        if (param.resultForm.testResult[param.rowId] === undefined)
            return
        switch (param.resultForm.testResult[param.rowId].resultType) {
            case "D":
                return <Select 
                    id={"results_" + param.rowId}
                    name={"testResult[" + param.rowId + "].resultValue"}
                    noLabel={true}
                    labelText=""
                    onChange={this.handleResultValueChange()} >

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

    handleMethodsChange = (param) => {
        console.log("handleMethodsChange:")
        // this.state.resultForm.testResult[param.rowId].methods = param.resultForm.testResult[param.rowId].methods;
        // console.log("handleMethodsChange1:" + this.state.resultForm.testResult[param.rowId].methods)
    }

    renderMethods = (param) => {
        // todo
        if (param.resultForm.testResult[param.rowId] === undefined)
            return
        return <Select
            id={"methods" + param.rowId}
            name={"testResult[" + param.rowId + "].methods"}
            noLabel={true}
            labelText=""
            onChange={() => {
                this.handleMethodsChange(param);
            }} >

            <SelectItem
                text=""
                value=""
            />
            {param.resultForm.methods.map((methods, methods_index) => (
                <SelectItem
                    text={methods.value}
                    value={methods.id}
                    key={methods_index}
                />
            ))}
        </Select>
    }

    renderAnalysisMethod = (param) => {
        // todo
        if (param.resultForm.testResult[param.rowId] === undefined)
            return
        return <Checkbox
            defaultChecked={this.state.analysisMethod}
            onChange={this.handleAnalysisMethodChange}
            labelText=""
            id={"analysisMethod" + param.rowId}
            name={"testResult[" + param.rowId + "].analysisMethod"} />
    }

    renderForceTechApproval = (param) => {
        // todo
        if (param.resultForm.testResult[param.rowId] === undefined)
            return
        return <Checkbox
            defaultChecked={this.state.forceTechApproval}
            onChange={this.handleForceTechApprovalChange}
            labelText=""
            id={"forceTechApproval" + param.rowId}
            name={"testResult[" + param.rowId + "].forceTechApproval"} />
    }

    renderCell = (param) => {
        switch (param.cell.info.header) {

            case "shadowResultValue":
                return this.renderShadowResultValue(param);
            case "resultValue":
                return this.renderResultValue(param)
            case "methods":
                return this.renderMethods(param)
            case "analysisMethod":
                return this.renderAnalysisMethod(param)
            case "forceTechApproval":
                return this.renderForceTechApproval(param)
        }
        return param.cell.value;
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


                <DataTable rows={this.state.resultForm.testResult} headers={resultSearchHeaderData} isSortable >
                    {({ rows, headers, getHeaderProps, getTableProps }) => (
                        <TableContainer title={"Title"} description="description">
                            <Table {...getTableProps()}>
                                <TableHead>
                                    <TableRow>
                                        {headers.map((header) => (
                                            <TableHeader {...getHeaderProps({ header })}>
                                                {header.header}
                                            </TableHeader>
                                        ))}
                                    </TableRow>

                                </TableHead>
                                <TableBody>
                                    <>
                                        {rows.slice((page - 1) * pageSize).slice(0, pageSize).map((row) => (
                                            <TableRow key={row.id}>
                                                {this.sampleDetails
                                                    ({
                                                        rowId: row.id,
                                                        row: row,
                                                        resultForm: this.state.resultForm,
                                                    })
                                                }

                                                {/* <TableCell > <RadioButton name="radio-group" onClick={this.patientSelected} labelText="" id={row.id} /></TableCell> */}

                                                {row.cells.map((cell) => (
                                                    <TableCell key={cell.id}>
                                                        {this.renderCell
                                                            ({
                                                                rowId: row.id,
                                                                row: row,
                                                                cell: cell,
                                                                resultForm: this.state.resultForm,
                                                            })
                                                        }
                                                    </TableCell>
                                                ))}

                                                {/* <TableCell > <RadioButton name="radio-group" onClick={this.patientSelected} labelText="" id={row.id} /></TableCell>  */}

                                            </TableRow>
                                        ))}
                                    </>
                                </TableBody>
                            </Table>
                        </TableContainer>
                    )}
                </DataTable>
                <Pagination onChange={this.handlePageChange} page={this.state.page} pageSize={this.state.pageSize}
                    pageSizes={[100]} totalItems={this.state.resultForm.testResult.length}></Pagination>

                {/* </Column> */}
                {/* </Grid> */}
            </>

        );

    }
}

export default injectIntl(SearchResultRangeForm)