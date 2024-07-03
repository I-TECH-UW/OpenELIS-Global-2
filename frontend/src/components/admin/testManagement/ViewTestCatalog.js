import React, { useEffect, useState } from "react";
import {
  StructuredListWrapper,
  StructuredListHead,
  StructuredListRow,
  StructuredListCell,
  StructuredListBody,
  Grid,
  Column,
  Toggle,
  FilterableMultiSelect,
  Tabs,
  TabList,
  Tab,
  Tag,
  TabPanels,
  TabPanel,
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableHeader,
  TableBody,
  TableCell,
  Section,
  Heading,
} from "@carbon/react";
import { getFromOpenElisServer } from "../../utils/Utils.js";
import PageBreadCrumb from "../../common/PageBreadCrumb.js";
import { FormattedMessage } from "react-intl";

let breadcrumbs = [
  { label: "home.label", link: "/" },
  { label: "breadcrums.admin.managment", link: "/MasterListsPage" },
  {
    label: "sidenav.label.admin.testmgt.ViewtestCatalog",
    link: "/MasterListsPage#TestCatalog",
  },
];

const TestCatalog = () => {
  const [showGuide, setShowGuide] = useState(false);
  const [testSectionList, setTestSectionList] = useState([]);
  const [data, setData] = useState([]);

  useEffect(() => {
    getFromOpenElisServer(`/rest/TestCatalog`, handleCatalog);
  }, []);

  const handleCatalog = (res) => {
    setTestSectionList(res.testSectionList);
    setData(res.testCatalogList);
  };

  const handleToggle = () => {
    setShowGuide(!showGuide);
  };

  const [selectedSections, setSelectedSections] = useState([]);

  const handleSectionChange = (selectedItems) => {
    setSelectedSections(selectedItems.selectedItems);
  };

  const rows = [
    {
      id: "name",
      field: "Name",
      description:
        "The name of the test as it will appear within openELIS. Both English and French are shown",
    },
    {
      id: "reportName",
      field: "Report Name",
      description:
        "The name of the test as it will appear in reports. Both English and French are shown",
    },
    {
      id: "active",
      field: "Active/Not Active",
      description:
        "If the test is active it can be ordered on the order form or as part of a test algorithm. If it is not active it cannot be ordered or be part of a test algorithm",
    },
    {
      id: "orderable",
      field: "Orderable/Not orderable",
      description:
        "If a test is active and orderable then it can be ordered on an order form. If it is active but not orderable then it will only be done if it is reflexed from another test",
    },
    {
      id: "testUnit",
      field: "Test Unit",
      description:
        "Which section of the lab performs the test. This is also known as a test section.",
    },
    {
      id: "sampleType",
      field: "Sample Type",
      description:
        "The type of sample on which the test can be done. If the intake technician is able to select the type of sample after they have ordered the test it will be marked as user to indicate that the user will select the type",
    },
    {
      id: "panel",
      field: "Panel",
      description:
        "If this test is part of a test panel then the panel will be named here.",
    },
    {
      id: "resultType",
      field: "Result type",
      description: (
        <>
          <p>The kind of result for this test:</p>
          <ul>
            <li>
              <strong>N - Numeric:</strong> Accepts only numeric results in a
              text box. Results can be evaluated as to being in a normal or a
              valid range.
            </li>
            <li>
              <strong>A - Alphanumeric:</strong> Accepts either numeric or text
              in a text box. It will not be evaluated for being normal or valid.
            </li>
            <li>
              <strong>R - Free text:</strong> Accepts up to 200 characters in a
              text area. It will not be evaluated for being normal or valid.
            </li>
            <li>
              <strong>D - Select list:</strong> User will be able to select from
              a dropdown list. The normal value will be specified as the
              reference value.
            </li>
            <li>
              <strong>M - Multi-select list:</strong> The user will be able to
              select one or more values from a dropdown list. No reference value
              will be specified.
            </li>
            <li>
              <strong>C - Cascading multi-select list:</strong> Similar to
              multi-select but the user will be able to select multiple groups
              from the dropdown list.
            </li>
          </ul>
        </>
      ),
    },
    {
      id: "uom",
      field: "uom",
      description:
        "Unit of measure for the test. This usually only applies to numeric or alphanumeric result types.",
    },
    {
      id: "significantDigits",
      field: "Significant digits",
      description:
        "The number of significant digits for numeric results. Entered results will be rounded or padded to the correct number of digits. The normal range will also be displayed with the correct number of significant digits.",
    },
    {
      id: "selectValues",
      field: "Select values",
      description:
        'Only specified for select, multi-select or cascading multi-select results. These are the available selections shown to the user. If the selection is marked as "qualifiable" then when the user selects that value they will be able to enter additional information in a text box.',
    },
    {
      id: "referenceValue",
      field: "Reference value",
      description:
        "The value of a selection for a healthy person. Only given for select list results.",
    },
    {
      id: "resultLimits",
      field: "Result limits",
      description:
        "The limits of normal and valid results for numeric tests. The values can depend on both the age and sex of the patient.",
    },
    {
      id: "sex",
      field: "Sex",
      description:
        "If the sex of the patient matters for the given values it will be specified here.",
    },
    {
      id: "ageRange",
      field: "Age range",
      description:
        "If the age range (in months) matters for the given values it will be specified here.",
    },
    {
      id: "normalRange",
      field: "Normal range",
      description:
        "Any numeric result within this range is what is expected in a healthy person.",
    },
    {
      id: "validRange",
      field: "Valid range",
      description:
        "Any numeric result not in this range is an indication that the test may not have been done correctly.",
    },
    {
      id: "note",
      field: "Note",
      description: "n/a means not available. The value is not specified",
    },
  ];

  const DataTableComponent = ({ item }) => {
    const headers = [
      { key: "field", header: "Field" },
      { key: "value", header: "Value" },
    ];

    const rows = [
      {
        id: `${item.id}-name`,
        field: "Name",
        value: `en: ${item.localization.english},  fr: ${item.localization.french}`,
      },
      {
        id: `${item.id}-reportName`,
        field: "Report Name",
        value: `en: ${item.reportLocalization.english},  fr: ${item.reportLocalization.french}`,
      },
      {
        id: `${item.id}-testUnit`,
        field: "Test Unit",
        value: item.testUnit,
      },

      {
        id: `${item.id}-sampleType`,
        field: "Sample Type",
        value: item.sampleType,
      },
      {
        id: `${item.id}-panel`,
        field: "Panel",
        value: item.panel,
      },
      {
        id: `${item.id}-resultType`,
        field: "Result Type",
        value: item.resultType,
      },
      {
        id: `${item.id}-uom`,
        field: "UOM",
        value: item.uom,
      },
      {
        id: `${item.id}-significantDigits`,
        field: "Significant Digits",
        value: item.significantDigits,
      },
      item.loinc && {
        id: `${item.id}-loinc`,
        field: "Loinc",
        value: item.loinc,
      },
      {
        id: `${item.id}-active`,
        field: "Active/Not Active",
        value: item.active,
      },
      {
        id: `${item.id}-orderable`,
        field: "Orderable/ Not -Orderable",
        value: item.orderable,
      },

      item.hasDictionaryValues && {
        id: `${item.id}-dictionaryValues`,
        field: "Dictionary Values",
        value: item.dictionaryValues.join(", "),
      },
    ].filter(Boolean);

    const limitHeaders = [
      { key: "gender", header: "Gender" },
      { key: "ageRange", header: "Age Range" },
      { key: "normalRange", header: "Normal Range" },
      { key: "validRange", header: "Valid Range" },
      { key: "reportingRange", header: "Reporting Range" },
      { key: "criticalRange", header: "Critical Range" },
    ];

    return (
      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              {headers.map((header) => (
                <TableHeader key={header.key}>{header.header}</TableHeader>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <TableRow key={row.id}>
                <TableCell>{row.field}</TableCell>
                <TableCell>{row.value}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        {item.hasLimitValues && (
          <Table>
            <TableHead>
              <TableRow>
                {limitHeaders.map((header) => (
                  <TableHeader key={header.key}>{header.header}</TableHeader>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {item.resultLimits.map((limit, limitIndex) => (
                <TableRow key={limitIndex}>
                  <TableCell>{limit.gender}</TableCell>
                  <TableCell>{limit.ageRange}</TableCell>
                  <TableCell>{limit.normalRange}</TableCell>
                  <TableCell>{limit.validRange}</TableCell>
                  <TableCell>{limit.reportingRange}</TableCell>
                  <TableCell>{limit.criticalRange}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        )}
      </TableContainer>
    );
  };

  return (
    <div className="adminPageContent">
      <br />
      <PageBreadCrumb breadcrumbs={breadcrumbs} />
      <br />
      <div className="orderLegendBody">
        <Grid>
          <Column lg={12}>
            <h1>
              {" "}
              <FormattedMessage id="label.viewtestCatalog" />
            </h1>
          </Column>
          <Column lg={4} md={8} sm={12}>
            <Toggle id="toggle" labelText="Show Guide" onClick={handleToggle} />
          </Column>
        </Grid>
        <hr />
        <Grid>
          <Column lg={11}>
            <br />
            <h5>Select test section to view catalog for that section</h5>
          </Column>
          <Column lg={5}>
            <FilterableMultiSelect
              type="inline"
              id="carbon-multiselect-example-3"
              items={[...testSectionList, "All"]}
              itemToString={(item) => (typeof item === "string" ? item : "")}
              onChange={handleSectionChange}
              selectedItems={selectedSections}
              selectionFeedback="top-after-reopen"
              placeholder="Selected Tests"
            />
          </Column>
        </Grid>

        {selectedSections.length > 0 && (
          <>
            {selectedSections.map((section, index) => (
              <Tag key={index} type="cyan">
                {section}
              </Tag>
            ))}
          </>
        )}
        {showGuide && (
          <>
            <hr />
            <StructuredListWrapper ariaLabel="Structured list">
              <StructuredListHead>
                <StructuredListRow head>
                  <StructuredListCell head>Field</StructuredListCell>
                  <StructuredListCell head>Description</StructuredListCell>
                </StructuredListRow>
              </StructuredListHead>
              <StructuredListBody>
                {rows.map((row) => (
                  <StructuredListRow key={row.id}>
                    <StructuredListCell>{row.field}</StructuredListCell>
                    <StructuredListCell>{row.description}</StructuredListCell>
                  </StructuredListRow>
                ))}
              </StructuredListBody>
            </StructuredListWrapper>
            <hr />
            <br />
          </>
        )}

        {selectedSections.length > 0 && (
          <>
            <br />
            <br />
            <Tabs>
              <TabList aria-label="List of tabs">
                {selectedSections
                  .sort((a, b) => (a === "All" ? -1 : b === "All" ? 1 : 0))
                  .map((section) => (
                    <Tab key={section}>{section}</Tab>
                  ))}
              </TabList>
              <TabPanels>
                {selectedSections
                  .sort((a, b) => (a === "All" ? -1 : b === "All" ? 1 : 0))
                  .map((section) => (
                    <TabPanel key={section}>
                      {section === "All"
                        ? data.map((item) => (
                            <div key={item.id}>
                              <DataTableComponent item={item} />
                              <br />
                            </div>
                          ))
                        : data
                            .filter((item) => item.testUnit === section)
                            .map((item) => (
                              <div key={item.id}>
                                <DataTableComponent item={item} />
                                <br />
                              </div>
                            ))}
                    </TabPanel>
                  ))}
              </TabPanels>
            </Tabs>
          </>
        )}
      </div>
    </div>
  );
};

export default TestCatalog;
