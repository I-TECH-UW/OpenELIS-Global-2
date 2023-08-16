import { useContext, useState, useEffect, useRef } from "react";
import { useParams } from "react-router-dom";
import config from "../../config.json";
import {
  IconButton, Heading, TextInput, Select, FilterableMultiSelect, SelectItem, Button, Grid, Column,
  Checkbox, FileUploader, Tag, TextArea ,Section ,Breadcrumb ,BreadcrumbItem ,Stack
} from '@carbon/react';
import { Launch, Subtract } from '@carbon/react/icons';
import { getFromOpenElisServer, postToOpenElisServerFullResponse, hasRole } from "../utils/Utils";
import UserSessionDetailsContext from "../../UserSessionDetailsContext"
import { NotificationContext } from "../layout/Layout";
import { AlertDialog ,NotificationKinds} from "../common/CustomNotification";
import { SearchResults } from "../common/SearchResultForm"
import "./../pathology/PathologyDashboard.css"


export const QuestionnaireResponse = ({ questionnaireResponse }) => {


  const renderQuestionResponse = (item) => {
    console.log(JSON.stringify(item))
    return <>
      <div className="questionnaireResponseItem">
        {item.text}:
        {item.answer && item.answer.map((answer, index) => {
          return <span key={index}>{renderAnswer(answer)}</span>
        })}
      </div>
    </>
  }

  const renderAnswer = (answer) => {
    console.log(JSON.stringify(answer))

    var display = '';
    if ('valueString' in answer) {
      display = answer.valueString
    } else if ('valueBoolean' in answer) {
      display = answer.valueBoolean
    } else if ('valueCoding' in answer) {
      display = answer.valueCoding.display
    } else if ('valueDate' in answer) {
      display = answer.valueDate
    } else if ('valueDecimal' in answer) {
      display = answer.valueDecimal
    } else if ('valueInteger' in answer) {
      display = answer.valueInteger
    } else if ('valueQuantity' in answer) {
      display = answer.valueQuantity.value + answer.valueQuantity.unit
    } else if ('valueTime' in answer) {
      display = answer.valueTime
    }
    return <>
      <span className="questionnaireResponseAnswer">
        {display}</span>
    </>
  }

  return <>
    {questionnaireResponse && questionnaireResponse.item.map((item, index) => {
      return <span key={index}>{renderQuestionResponse(item)}</span>
    })}

  </>

}


function ImmunohistochemistryCaseView() {

  const componentMounted = useRef(false);

  const { immunohistochemistrySampleId } = useParams()

  const { notificationVisible, setNotificationVisible, setNotificationBody } = useContext(NotificationContext);
  const { userSessionDetails, setUserSessionDetails } = useContext(UserSessionDetailsContext);
  const [immunohistochemistrySampleInfo, setImmunohistochemistrySampleInfo] = useState({});

  const [initialMount, setInitialMount] = useState(false);

  const [statuses, setStatuses] = useState([]);
  const [reportTypes, setReportTypes] = useState([]);
  const [techniques, setTechniques] = useState([]);
  const [requests, setRequests] = useState([]);
  const [conclusions, setConclusions] = useState([]);
  const [technicianUsers, setTechnicianUsers] = useState([]);
  const [pathologistUsers, setPathologistUsers] = useState([]);

  async function displayStatus(response) {
    var body = await response.json();
    console.log(body)
    var status = response.status;
    setNotificationVisible(true);
    if (status == "200") {
      const save1 = document.getElementById("pathology_save");
      const save2 = document.getElementById("pathology_save2");
      save1.disabled = true;
      save2.disabled = true;
      setNotificationBody({ kind: NotificationKinds.success, title: "Notification Message", message: "Succesfuly saved" });
    } else {
      setNotificationBody({ kind: NotificationKinds.error, title: "Notification Message", message: "Error while saving" });
    }
  }

  const toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = reject;
  });

  const save = (e) => {
    let submitValues = {
      "assignedTechnicianId": immunohistochemistrySampleInfo.assignedTechnicianId,
      "assignedPathologistId": immunohistochemistrySampleInfo.assignedPathologistId,
      "status": immunohistochemistrySampleInfo.status,
      "reports": immunohistochemistrySampleInfo.reports,
      "release": immunohistochemistrySampleInfo.release != undefined ? immunohistochemistrySampleInfo.release : false,
    }

    postToOpenElisServerFullResponse("/rest/immunohistochemistry/caseView/" + immunohistochemistrySampleId, JSON.stringify(submitValues), displayStatus);
  }

  const setReportTypeList = (reportTypes) => {
    if (componentMounted.current) {
      setReportTypes(reportTypes);
    }
  }

  const setInitialImmunohistochemistrySampleInfo = (e) => {
    setImmunohistochemistrySampleInfo(e);
    setInitialMount(true);
  }

  useEffect(() => {
    componentMounted.current = true;
    getFromOpenElisServer("/rest/displayList/IMMUNOHISTOCHEMISTRY_STATUS", setStatuses);
    getFromOpenElisServer("/rest/displayList/IMMUNOHISTOCHEMISTRY_REPORT_TYPES", setReportTypeList);
    //TODO make conclusions list instead of reusing pathrequest
    getFromOpenElisServer("/rest/users/", setTechnicianUsers);
    getFromOpenElisServer("/rest/users/Pathologist", setPathologistUsers);
    getFromOpenElisServer("/rest/immunohistochemistry/caseView/" + immunohistochemistrySampleId, setInitialImmunohistochemistrySampleInfo);

    return () => {
      componentMounted.current = false
    }
  }, []);

  return (
    <>
      <Breadcrumb>
        <BreadcrumbItem href="/">Home</BreadcrumbItem>
        <BreadcrumbItem href="/ImmunohistochemistryDashboard">Immunohistochemistry DashBoard</BreadcrumbItem>
      </Breadcrumb>

      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section >
              <Heading >
                Immunohistochemistry
                {/* <FormattedMessage id="label.page.patientHistory" /> */}
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Section>
              {immunohistochemistrySampleInfo ? (<div className="patient-header">
                <div className="patient-name"><Tag type="blue">Name :</Tag>{immunohistochemistrySampleInfo.lastName}  {immunohistochemistrySampleInfo.firstName}</div>
                <div className="patient-dob"> <Tag type="blue">Sex :</Tag>{immunohistochemistrySampleInfo.sex === 'M' ? "Male" : "Female"}<Tag type="blue">Age :</Tag>{immunohistochemistrySampleInfo.age} </div>
                <div className="patient-id"><Tag type="blue">Order Date :</Tag>{immunohistochemistrySampleInfo.requestDate}</div>
                <div className="patient-id"><Tag type="blue">Lab Number :</Tag>{immunohistochemistrySampleInfo.labNumber}</div>
                <div className="patient-id"><Tag type="blue">Referring Facility:</Tag> <Tag type="blue">Ward/Dept/Unit: :</Tag></div>
                <div className="patient-id"><Tag type="blue">Requester: :</Tag>{immunohistochemistrySampleInfo.assignedTechnician}</div>
              </div>) : (<div className="patient-header">
                <div className="patient-name">Patient Id Doest Exist</div>
              </div>)}
            </Section>
          </Section>
          <Section>
            <Section>
              <div className="patient-header">
                <QuestionnaireResponse questionnaireResponse={immunohistochemistrySampleInfo.programQuestionnaireResponse} />
              </div>
            </Section>
          </Section>
        </Column>
      </Grid>
      <Stack gap={4}>
      <Grid fullWidth={true} className="gridBoundary">
        {notificationVisible === true ? <AlertDialog /> : ""}

        {/* <Column lg={16} md={8} sm={4}>
        <Heading>
            Immunohistochemistry - {immunohistochemistrySampleInfo.labNumber}
        </Heading>
        </Column>

        <Column lg={16} md={8} sm={4}>
        Order Date {immunohistochemistrySampleInfo.requestDate}
        Name: {immunohistochemistrySampleInfo.lastName}, {immunohistochemistrySampleInfo.firstName}
        Age: {immunohistochemistrySampleInfo.age} Sex: {immunohistochemistrySampleInfo.sex}
        </Column> */}
        {/* <Column lg={16} md={8} sm={4}>
          <QuestionnaireResponse questionnaireResponse={immunohistochemistrySampleInfo.programQuestionnaireResponse} />
        </Column> */}
        <Column lg={16} md={8} sm={4}>
          {/* <SearchResults results={this.state.resultForm}/> */}
        </Column>
        <Column lg={16} md={8} sm={4}>
          <Button id="pathology_save" onClick={(e) => { e.preventDefault(); save(e) }}>Save</Button>
        </Column>
        <Column lg={4} md={2} sm={2} >
          <Select id="report"
            name="report"
            labelText="report"
            //value={immunohistochemistrySampleInfo.report}
            onChange={(event) => {
              setImmunohistochemistrySampleInfo({...immunohistochemistrySampleInfo, reports: [...(immunohistochemistrySampleInfo.reports || []), {id: '', reportType: event.target.value}]});
            }}>
            <SelectItem disabled value="ADD" text="Add Report" />
            {reportTypes.map((report, index) => {
              return (<SelectItem key={index}
                text={report.value}
                value={report.id}
              />);
            })}
          </Select>
          {immunohistochemistrySampleInfo.reports && immunohistochemistrySampleInfo.reports.map((report, index) => {
          return (
            <>
           <div className="file-container">
              <Column lg={2} md={1} sm={2}>
                
                <FileUploader
                  buttonLabel={"Upload File For Report " + report.reportType}
                  iconDescription="file upload"
                  multiple={false}
                  accept={['image/jpeg', 'image/png', 'application/pdf']}
                  disabled={false}
                  name=""
                  buttonKind="primary"
                  size="lg"
                  filenameStatus="edit"
                  onChange={async (e) => {
                    e.preventDefault();
                    let file = e.target.files[0];
                    var newReports = [...immunohistochemistrySampleInfo.reports];
                    let encodedFile = await toBase64(file);
                    newReports[index].base64Image = encodedFile;
                    setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, reports: newReports });
                  }}
                  onClick={function noRefCheck() { }}
                  onDelete={(e) => {
                    e.preventDefault();
                    var newReports = [...immunohistochemistrySampleInfo.reports];
                    newReports[index].base64Image = '';
                    setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, slides: newReports });
                  }}
                />
                {immunohistochemistrySampleInfo.reports[index].image &&
                  <>
                    <Button onClick={() => {
                      var win = window.open();
                      win.document.write('<iframe src="' + report.fileType + ";base64," + report.image + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
                    }}>
                      <Launch /> View
                    </Button>
                  </>
                }
              </Column>
              {/* <Column lg={4} md={5} sm={3} /> */}
              </div>
            </>
          )
        })}
        </Column>
        <Column lg={4} md={2} sm={2} >
          <Select id="status"
            name="status"
            labelText="Status"
            value={immunohistochemistrySampleInfo.status}
            onChange={(event) => {
              setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, status: event.target.value });
            }}>
            <SelectItem disabled value="placeholder" text="Status" />

            {statuses.map((status, index) => {
              return (<SelectItem key={index}
                text={status.value}
                value={status.id}
              />);
            })}
          </Select>
        </Column>
        <Column lg={2} md={1} sm={2} style={{ "marginBottom": "1rem", "marginTop": "1rem" }}>
        </Column>
        <Column lg={2} md={1} sm={2} style={{ "marginBottom": "1rem", "marginTop": "1rem" }}>

          <Select id="assignedTechnician"
            name="assignedTechnician"
            labelText="Technician Assigned"
            value={immunohistochemistrySampleInfo.assignedTechnicianId}
            onChange={(event) => {
              setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, assignedTechnicianId: event.target.value });
            }}>
            <SelectItem />
            {technicianUsers.map((user, index) => {
              return (<SelectItem key={index}
                text={user.value}
                value={user.id}
              />);
            })}
          </Select>
        </Column>
        <Column lg={8} md={4} sm={2} />
        <Column lg={16} md={8} sm={4}>
        </Column >
        {immunohistochemistrySampleInfo.blocks && immunohistochemistrySampleInfo.blocks.map((block, index) => {
          return (
            <>

              <Column lg={16} md={8} sm={4}>
                <IconButton label="remove block" onClick={() => {
                  var newBlocks = [...immunohistochemistrySampleInfo.blocks];
                  newBlocks = newBlocks.splice(index, 1);
                  setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, blocks: newBlocks });
                }} kind='tertiary' size='sm'>
                  <Subtract size={18} />
                </IconButton>
                Block
              </Column>
              <Column lg={4} md={2} sm={1} key={index}>
                <TextInput
                  id="blockNumber"
                  labelText="block number"
                  hideLabel={true}
                  placeholder="Block Number"
                  value={block.blockNumber}
                  onChange={e => {
                    var newBlocks = [...immunohistochemistrySampleInfo.blocks];
                    newBlocks[index].blockNumber = e.target.value;
                    setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, blocks: newBlocks });
                  }} />
              </Column>
              <Column lg={4} md={2} sm={1}>
                <TextInput
                  id="location"
                  labelText="location"
                  hideLabel={true}
                  placeholder="Location"
                  value={block.location}
                  onChange={e => {
                    var newBlocks = [...immunohistochemistrySampleInfo.blocks];
                    newBlocks[index].location = e.target.value;
                    setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, blocks: newBlocks });
                  }}
                />
              </Column>
              <Column lg={4} md={2} sm={2}>
                <Button onClick={(e) => {
                  window.open(config.serverBaseUrl + '/LabelMakerServlet?labelType=block&code=' + block.blockNumber, '_blank')
                }}>Print Label</Button>
              </Column>
              <Column lg={4} md={2} sm={0} />
            </>
          )
        })}
        {/* <Column lg={16} md={8} sm={4}>
                  <Button onClick={() => {
          setImmunohistochemistrySampleInfo({...immunohistochemistrySampleInfo, blocks: [...(immunohistochemistrySampleInfo.blocks || []), {id: '', blockNumber: ''}]});
        }}>
          Add Block
          </Button>
          </Column> */}
        <Column lg={16} md={8} sm={4}>
        </Column>
        {immunohistochemistrySampleInfo.slides && immunohistochemistrySampleInfo.slides.map((slide, index) => {
          return (
            <>
              <Column lg={16} md={8} sm={4}>
                <IconButton label="remove slide" onClick={() => {
                  var newSlides = [...immunohistochemistrySampleInfo.slides];
                  setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, slides: newSlides.splice(index, 1) });
                }} kind='tertiary' size='sm'>
                  <Subtract size={18} />
                </IconButton>
                Slide
              </Column>
              <Column lg={4} md={2} sm={1} key={index}>
                <TextInput
                  id="slideNumber"
                  labelText="slide number"
                  hideLabel={true}
                  placeholder="Slide Number"
                  value={slide.slideNumber}
                  onChange={e => {
                    var newSlides = [...immunohistochemistrySampleInfo.slides];
                    newSlides[index].slideNumber = e.target.value;
                    setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, slides: newSlides });
                  }}
                />
              </Column>
              <Column lg={4} md={2} sm={1}>
                <TextInput
                  id="location"
                  labelText="location"
                  hideLabel={true}
                  placeholder="Location"
                  value={slide.location}
                  onChange={e => {
                    var newSlides = [...immunohistochemistrySampleInfo.slides];
                    newSlides[index].location = e.target.value;
                    setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, slides: newSlides });
                  }}
                />
              </Column>
              <Column lg={4} md={1} sm={2}>
                <FileUploader
                  buttonLabel="Upload Image"
                  iconDescription="file upload"
                  multiple={false}
                  accept={['image/jpeg', 'image/png', 'application/pdf']}
                  disabled={false}
                  name=""
                  buttonKind="primary"
                  size="lg"
                  filenameStatus="edit"
                  onChange={async (e) => {
                    e.preventDefault();
                    let file = e.target.files[0];
                    var newSlides = [...immunohistochemistrySampleInfo.slides];
                    let encodedFile = await toBase64(file);
                    newSlides[index].base64Image = encodedFile;
                    setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, slides: newSlides });
                  }}
                  onClick={function noRefCheck() { }}
                  onDelete={(e) => {
                    e.preventDefault();
                    var newSlides = [...immunohistochemistrySampleInfo.slides];
                    newSlides[index].base64Image = '';
                    setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, slides: newSlides });
                  }}
                />
                {immunohistochemistrySampleInfo.slides[index].image &&
                  <>
                    <Button onClick={() => {
                      var win = window.open();
                      win.document.write('<iframe src="' + slide.fileType + ";base64," + slide.image + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
                    }}>
                      <Launch /> View
                    </Button>
                  </>
                }
                <Button onClick={(e) => {
                  window.open(config.serverBaseUrl + '/LabelMakerServlet?labelType=slide&code=' + slide.slideNumber, '_blank')
                }}>Print Label</Button>
              </Column>
              <Column lg={4} md={5} sm={3} />
            </>
          )
        })}

        {/* <Column lg={16} md={8} sm={4}>
        <Button onClick={() => {
            setImmunohistochemistrySampleInfo({...immunohistochemistrySampleInfo, slides: [...(immunohistochemistrySampleInfo.slides || []), {id: '', slideNumber: ''}]});
        }}>
          Add Slide
          </Button>
        </Column> */}
        <Column lg={4} md={2} sm={2}>
          <Select id="assignedPathologist"
            name="assignedPathologist"
            labelText="Pathologist Assigned"
            value={immunohistochemistrySampleInfo.assignedPathologistId}
            onChange={e => {
              setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, assignedPathologistId: e.target.value });
            }}>
            <SelectItem />
            {pathologistUsers.map((user, index) => {
              return (<SelectItem key={index}
                text={user.value}
                value={user.id}
              />);
            })}
          </Select>
        </Column>
        <Column lg={12} md={6} sm={0}>
        </Column>
        <Column lg={16} md={8} sm={4}></Column>
        {hasRole(userSessionDetails ,"Pathologist") &&
          <>
            <Column lg={8} md={4} sm={2}>
              {initialMount && <FilterableMultiSelect
                id="techniques"
                titleText="Techniques Used"
                items={techniques}
                itemToString={(item) => (item ? item.value : '')}
                initialSelectedItems={immunohistochemistrySampleInfo.techniques}
                onChange={(changes) => {
                  setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, techniques: changes.selectedItems });
                }}
                selectionFeedback="top-after-reopen"
              />
              }
            </Column>
            <Column lg={8} md={4} sm={2}>
              {immunohistochemistrySampleInfo.techniques && immunohistochemistrySampleInfo.techniques.map((technique, index) => (
                <Tag
                  key={index}
                  onClose={() => { }}
                >
                  {technique.value}
                </Tag>
              ))}
            </Column>
            <Column lg={16} md={8} sm={4}></Column>
            <Column lg={8} md={4} sm={2}>
              {initialMount && <FilterableMultiSelect
                id="requests"
                titleText="Add Request"
                items={requests}
                itemToString={(item) => (item ? item.value : '')}
                initialSelectedItems={immunohistochemistrySampleInfo.requests}
                onChange={(changes) => {
                  setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, "requests": changes.selectedItems });
                }}
                selectionFeedback="top-after-reopen"
              />
              }
            </Column>
            <Column lg={8} md={4} sm={2}>
              {immunohistochemistrySampleInfo.requests && immunohistochemistrySampleInfo.requests.map((technique, index) => (
                <Tag
                  key={index}
                  onClose={() => { }}
                >
                  {technique.value}
                </Tag>
              ))}
            </Column>
            <Column lg={16} md={8} sm={4}>
              <TextArea labelText="Gross Exam" value={immunohistochemistrySampleInfo.grossExam} onChange={e => {
                setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, grossExam: e.target.value });
              }} />
            </Column>
            <Column lg={16} md={8} sm={4}>
              <TextArea labelText="Microscopy Exam" value={immunohistochemistrySampleInfo.microscopyExam} onChange={e => {
                setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, microscopyExam: e.target.value });
              }} />
            </Column>
            <Column lg={8} md={4} sm={2}>
              {initialMount && <FilterableMultiSelect
                id="conclusion"
                titleText="Conclusion"
                items={conclusions}
                itemToString={(item) => (item ? item.value : '')}
                initialSelectedItems={immunohistochemistrySampleInfo.conclusions}
                onChange={(changes) => {
                  setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, "conclusions": changes.selectedItems });
                }}
                selectionFeedback="top-after-reopen"
              />
              }
            </Column>
            <Column lg={8} md={4} sm={2}>
              {immunohistochemistrySampleInfo.conclusions && immunohistochemistrySampleInfo.conclusions.map((conclusion, index) => (
                <Tag
                  key={index}
                >
                  {conclusion.value}
                </Tag>
              ))}
            </Column>
            <Column lg={16} md={8} sm={4}>
              <TextArea labelText="Conclusion" value={immunohistochemistrySampleInfo.conclusionText} onChange={e => {
                setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, conclusionText: e.target.value });
              }} />

            </Column>
          </>}
        <Column lg={16}>
          <Checkbox labelText="Ready for Release" id="release"
            onChange={() => {
              setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, release: !immunohistochemistrySampleInfo.release });
            }} />
        </Column>
        <Column lg={16}>
          <Checkbox labelText="Refer to ImmunoHistoChemistry" id="referToImmunoHistoChemistry"
            onChange={() => {
              setImmunohistochemistrySampleInfo({ ...immunohistochemistrySampleInfo, referToImmunoHistoChemistry: !immunohistochemistrySampleInfo.referToImmunoHistoChemistry });
            }} />
        </Column>
        <Column><Button id="pathology_save2" onClick={(e) => { e.preventDefault(); save(e) }}>Save</Button></Column>

      </Grid>
      </Stack>


    </>
  )
}

export default ImmunohistochemistryCaseView;