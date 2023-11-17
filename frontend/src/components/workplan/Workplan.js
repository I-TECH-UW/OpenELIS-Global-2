import {
  Button,
  Column,
  Grid,
  Heading,
  Link,
  Section,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@carbon/react";
import React, { useState, useContext } from "react";
import "../Style.css";
import "./wpStyle.css";
import { FormattedMessage } from "react-intl";
import WorkplanSearchForm from "./WorkplanSearchForm";
import {
  postToOpenElisServerForPDF,
  convertAlphaNumLabNumForDisplay,
} from "../utils/Utils";
import { ConfigurationContext } from "../layout/Layout";

export default function Workplan(props) {
  const { configurationProperties } = useContext(ConfigurationContext);

  const [testsList, setTestsList] = useState([]);
  const [subjectOnWorkplan, setSubjectOnWorkplan] = useState(false);
  const [nextVisitOnWorkplan, setNextVisitOnWorkplan] = useState(false);
  const [configurationName, setConfigurationName] = useState("");
  const [selectedValue, setSelectedValue] = useState("");
  const [selectedLabel, setSelectedLabel] = useState("");

  const type = props.type;
  let title = "";
  switch (type) {
    case "test":
      title = <FormattedMessage id="workplan.test.title" />;
      break;
    case "panel":
      title = <FormattedMessage id="workplan.panel.title" />;
      break;
    case "unit":
      title = <FormattedMessage id="workplan.unit.title" />;
      break;
    case "priority":
      title = <FormattedMessage id="workplan.priority.title" />;
      break;
    default:
      title = "";
  }

  const handleTestsList = (tests) => {
    setTestsList(tests.tests);
    setSubjectOnWorkplan(tests.SUBJECT_ON_WORKPLAN);
    setNextVisitOnWorkplan(tests.NEXT_VISIT_DATE_ON_WORKPLAN);
    setConfigurationName(tests.configurationName);
  };
  const handleSelectedValue = (val) => {
    setSelectedValue(val);
  };
  const handleSelectedLabel = (val) => {
    setSelectedLabel(val);
  };

  const printWorkplan = () => {
    let form = {
      type: type,
      testTypeID: "",
      testSectionId: selectedValue,
      testName: selectedLabel,
      workplanTests: buildWorlplanTestFromTestList(testsList),
    };
    if (type === "priority") {
      delete form.testSectionId;
    }
    postToOpenElisServerForPDF(
      "/rest/printWorkplanReport",
      JSON.stringify(form),
    );
  };

  const buildWorlplanTestFromTestList = (rawData) => {
    let output = [];
    const propertiesToKeep = [
      "accessionNumber",
      "patientInfo",
      "receivedDate",
      "testName",
      "notIncludedInWorkplan",
    ];
    rawData.forEach((item) => {
      let obj = {};
      Object.keys(item).filter((key) => {
        if (propertiesToKeep.indexOf(key) > -1) {
          Object.assign(obj, {
            [key]: item[key],
          });
        }
        return true;
      });
      output.push(obj);
    });
    return output;
  };

  const disableEnableTest = (checkbox, index) => {
    if (checkbox.checked) {
      document.querySelector("#row_" + index).style.backgroundColor = "#cccccc";
      testsList[index].notIncludedInWorkplan = true;
    } else {
      checkbox.checked = "";
      document.querySelector("#row_" + index).style.backgroundColor = "";
      testsList[index].notIncludedInWorkplan = false;
    }
  };

  let rowColorIndex = 2;
  let showAccessionNumber = false;
  let currentAccessionNumber = "";
  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Section>
            <Heading className="contentHeader1">{title}</Heading>
          </Section>
        </Column>
        <br />
        <br />
      </Grid>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <WorkplanSearchForm
            type={type}
            createTestsList={handleTestsList}
            selectedValue={handleSelectedValue}
            selectedLabel={handleSelectedLabel}
          />
        </Column>
      </Grid>
      {testsList.length !== 0 && (
        <>
          <hr />
          <br />
          <Grid fullWidth={true}>
            <Column lg={16}>
              <Button
                size="md"
                type="button"
                name="print"
                id="print"
                onClick={printWorkplan}
              >
                <FormattedMessage id="workplan.print" />
              </Button>
            </Column>
          </Grid>
          <br />
          <Grid fullWidth={true}>
            <Column lg={16}>
              <FormattedMessage id="label.total.tests" /> = {testsList.length}
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <img
                src={`images/nonconforming.gif`}
                alt="nonconforming"
              /> = <FormattedMessage id="result.nonconforming.item" />
              <br />
              <br />
            </Column>
          </Grid>
          <Grid fullWidth={true}>
            <Column lg={16} md={2}>
              <>
                <Table size={"sm"}>
                  <TableHead>
                    <TableRow>
                      <TableHeader>
                        <FormattedMessage id="label.button.remove" />
                      </TableHeader>
                      {type === "test" && <TableHeader>&nbsp;</TableHeader>}
                      <TableHeader>
                        <FormattedMessage id="quick.entry.accession.number" />
                      </TableHeader>
                      {subjectOnWorkplan.toLowerCase() === "true" && (
                        <TableHeader>
                          <FormattedMessage id="patient.subject.number" />
                        </TableHeader>
                      )}
                      {nextVisitOnWorkplan.toLowerCase() === "true" && (
                        <TableHeader>
                          <FormattedMessage id="sample.entry.nextVisit.date" />
                        </TableHeader>
                      )}
                      {type !== "test" && <TableHeader>&nbsp;</TableHeader>}
                      {type !== "test" && (
                        <TableHeader>
                          {configurationName === "Haiti LNSP" ? (
                            <FormattedMessage
                              id="sample.entry.project.patient.and.testName"
                              values={{ br: <br /> }}
                            />
                          ) : (
                            <FormattedMessage id="sample.entry.project.testName" />
                          )}
                        </TableHeader>
                      )}
                      <TableHeader>
                        <FormattedMessage id="sample.receivedDate" />
                      </TableHeader>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {testsList.map((row, index) => {
                      if (!(row.accessionNumber === currentAccessionNumber)) {
                        showAccessionNumber = true;
                        currentAccessionNumber = row.accessionNumber;
                        rowColorIndex++;
                      } else {
                        showAccessionNumber = false;
                      }
                      return (
                        <TableRow
                          key={index}
                          id={"row_" + index}
                          className={
                            rowColorIndex % 2 === 0 ? "evenRow" : "oddRow"
                          }
                        >
                          {!row.servingAsTestGroupIdentifier && (
                            <TableCell>
                              <input
                                type="checkbox"
                                value={row.notIncludedInWorkplan}
                                id={"includedCheck_" + index}
                                className="includedCheck"
                                onClick={(e) =>
                                  disableEnableTest(e.target, index)
                                }
                              />
                            </TableCell>
                          )}
                          {type === "test" && (
                            <TableCell>
                              {row.nonconforming && (
                                <img
                                  src={`images/nonconforming.gif`}
                                  alt="nonconforming"
                                />
                              )}
                            </TableCell>
                          )}
                          <TableCell>
                            {showAccessionNumber && (
                              <Link style={{ color: "blue" }} href="#">
                                <u>
                                  {configurationProperties.AccessionFormat ===
                                  "ALPHANUM"
                                    ? convertAlphaNumLabNumForDisplay(
                                        row.accessionNumber,
                                      )
                                    : row.accessionNumber}
                                </u>
                              </Link>
                            )}
                          </TableCell>
                          {subjectOnWorkplan.toLowerCase() === "true" && (
                            <TableCell>
                              {showAccessionNumber && row.patientInfo}
                            </TableCell>
                          )}
                          {nextVisitOnWorkplan.toLowerCase() === "true" && (
                            <TableCell>
                              {showAccessionNumber && row.nextVisitDate}
                            </TableCell>
                          )}
                          {type !== "test" && (
                            <TableCell>
                              {row.nonconforming && (
                                <img
                                  src={`images/nonconforming.gif`}
                                  alt="nonconforming"
                                />
                              )}
                            </TableCell>
                          )}
                          {type !== "test" && (
                            <TableCell>{row.testName}</TableCell>
                          )}
                          <TableCell>{row.receivedDate}</TableCell>
                        </TableRow>
                      );
                    })}
                  </TableBody>
                </Table>
              </>
            </Column>
            <hr />
          </Grid>
          <br />
          <Grid fullWidth={true}>
            <Column lg={16}>
              <Button
                size="md"
                type="button"
                name="print"
                id="print"
                onClick={printWorkplan}
              >
                <FormattedMessage id="workplan.print" />
              </Button>
            </Column>
          </Grid>
        </>
      )}
      {selectedValue && testsList.length === 0 && (
        <h2>
          <FormattedMessage id="result.noTestsFound" />
        </h2>
      )}
    </>
  );
}
