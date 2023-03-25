import React from 'react'
import { FormattedMessage, injectIntl } from 'react-intl'
import '../Style.css'
import { getFromOpenElisServer } from '../utils/Utils'
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
  Pagination
} from '@carbon/react'
import { resultSearchHeaderData } from '../data/ResultsTableHeaders'
import { Formik, Field } from 'formik'
import SearchResultFormValues from '../formModel/innitialValues/SearchResultFormValues'

class SearchResultForm extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      dob: '',
      resultForm: { testResult: [] },
      tableTitle: '',
      page: 1,
      pageSize: 10,
      doRange: true
    }
  }

  handleDoRangeChange = () => {
    console.log('handleDoRangeChange:' + this.state.doRange)
    this.state.doRange = !this.state.doRange
  }

  // handleSubmit = (values) => {
  //     values.dateOfBirth = this.state.dob
  //     console.log("handleSubmit:" + this.state.doRange)
  //     var searchEndPoint = "/rest/results?" + "&labNumber=" + values.labNumber
  //     getFromOpenElisServer(searchEndPoint, this.fetchResults);
  // };

  handleSubmit = (values) => {
    values.dateOfBirth = this.state.dob
    console.log('handleSubmit:' + this.state.doRange)
    const searchEndPoint = '/rest/results?' + '&labNumber=' + values.labNumber
    getFromOpenElisServer(searchEndPoint, this.fetchResults)
  }

  fetchResults = (resultForm) => {
    // console.log(JSON.stringify(result))
    let i = 0
    resultForm.testResult.forEach(item => item.id = i++)
    this.setState({ resultForm })
  }

  handleDatePickerChange = (...e) => {
    this.setState({
      dob: e[1]
    })
  }

  handlePageChange = (pageInfo) => {
    if (this.state.page != pageInfo.page) {
      this.setState({ page: pageInfo.page })
    }
    if (this.state.pageSize != pageInfo.pageSize) {
      this.setState({ pageSize: pageInfo.pageSize })
    }
  }

  handlePerPageChange = (newPerPage) => {
    this.setState({ perPage: newPerPage })
  }

  renderLabHeader = (param) => {
    if (param.resultForm.testResult[param.rowId].showSampleDetails == true) {
      return <div >
                Lab No.: &nbsp;&nbsp;{param.resultForm.testResult[param.rowId].sequenceAccessionNumber} Condition: {param.resultForm.testResult[param.rowId].initialSampleCondition}  Sample Type: {param.resultForm.testResult[param.rowId].sampleType} <br></br>
                Patient: &nbsp;&nbsp;{param.resultForm.lastName}, {param.resultForm.firstName} {param.resultForm.nationalId} {param.resultForm.subjectNumber} {param.resultForm.gender}, {param.resultForm.dob} <br></br>
                <br></br>
            </div>
    }
    return <div ></div>
  }

  renderResultRow = (param) => {
    return <div >
            {param.rowCells.map((cell) => (
                <TableCell key={cell.id}>{cell.value}</TableCell>
            ))}
            {/* {currentSample.testDate} {currentSample.testName} {currentSample.normalRange} {currentSample.resultValue} {currentSample.shadowResultValue} */}
        </div>
  }

  render () {
    const { page, pageSize } = this.state
    // const prefix = this.state.prefix;
    return (

            <>
                {/* <Grid  fullWidth={true} className="gridBoundary"> */}
                {/* <Column  lg={3}> */}
                <Formik
                    initialValues={SearchResultFormValues}
                    // validationSchema={}
                    onSubmit={this.handleSubmit}
                    onChange
                >
                    {({
                      values,
                      errors,
                      touched,
                      handleChange,
                      handleBlur,
                      handleSubmit
                    }) => (

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
                                                    <FormattedMessage id="label.button.search" />
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
                                        <Checkbox onChange={this.handleDoRangeChange} name={field.name} labelText="Do Range" id={field.name} />
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
                        <TableContainer title={'Title'} description="description">
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
                                                {/* {this.renderLabHeader
                                                    ({
                                                        rowId: row.id,
                                                        resultForm: this.state.resultForm,
                                                    })
                                                } */}

                                                {this.renderResultRow
                                                ({
                                                  showSampleDetails: this.state.resultForm.testResult[row.id].showSampleDetails,
                                                  rowCells: row.cells,
                                                  row
                                                })
                                                }

                                                {/* <TableCell > <RadioButton name="radio-group" onClick={this.patientSelected} labelText="" id={row.id} /></TableCell> */}
                                                {/* {row.cells.map((cell) => (
                                                    <TableCell key={cell.id}>{cell.value}</TableCell>
                                                ))} */}
                                            </TableRow>
                                        ))}
                                    </>
                                </TableBody>
                            </Table>
                        </TableContainer>
                    )}
                </DataTable>
                <Pagination onChange={this.handlePageChange} page={this.state.page} pageSize={this.state.pageSize}
                    pageSizes={[5, 10, 20, 30]} totalItems={this.state.resultForm.testResult.length}></Pagination>

                {/* </Column> */}
                {/* </Grid> */}
            </>

    )
  }
}

export default injectIntl(SearchResultForm)
