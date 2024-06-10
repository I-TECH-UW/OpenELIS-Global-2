import {
  Button,
  Checkbox,
  Column,
  Form,
  Grid,
  Heading,
  Section,
  Select,
  SelectItem,
  TextInput,
} from "@carbon/react";
import { React, useEffect, useState } from "react";
import CustomDatePicker from "../common/CustomDatePicker";

import { FormattedMessage, useIntl, injectIntl } from "react-intl";
import { getFromOpenElisServer } from "../utils/Utils";
import PageBreadCrumb from "../common/PageBreadCrumb";
let breadcrumbs = [{ label: "home.label", link: "/" }];

const EOrderSearch = ({
  setEOrders = (eOrders) => {
    console.debug("set EOrders default");
  },
  eOrderRef,
}) => {
  const intl = useIntl();

  const [hasEOrders, setHasEOrders] = useState(false);
  const [searchValue, setSearchValue] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [statusId, setStatusId] = useState("");
  const [statusOptions, setStatusOptions] = useState([]);
  const [allInfo, setAllInfo] = useState(false);
  const [searchCompleted, setSearchCompleted] = useState(false);

  useEffect(() => {
    getFromOpenElisServer("/rest/ElectronicOrders", handleElectronicOrders);
  }, []);

  const handleElectronicOrders = (response) => {
    console.log(response);
  };

  function searchByIdentifier() {
    const params = new URLSearchParams({
      searchType: "IDENTIFIER",
      searchValue: searchValue,
      useAllInfo: allInfo,
    });

    getFromOpenElisServer(
      "/rest/ElectronicOrders?" + params.toString(),
      parseEOrders,
    );
  }

  function searchByDateAndStatus() {
    const params = new URLSearchParams({
      searchType: "DATE_STATUS",
      startDate: startDate,
      endDate: endDate,
      statusId: statusId,
      useAllInfo: allInfo,
    });
    getFromOpenElisServer(
      "/rest/ElectronicOrders?" + params.toString(),
      parseEOrders,
    );
  }

  const parseEOrders = (response) => {
    setSearchCompleted(true);
    setHasEOrders(
      response.eOrders instanceof Array && response.eOrders.length > 0,
    );
    setEOrders(
      response.eOrders.map((item) => {
        return { ...item, id: item.electronicOrderId };
      }),
    );
    if (eOrderRef?.current) {
      window.scrollTo({
        top: eOrderRef.current.offsetTop - 50,
        left: 0,
        behavior: "smooth",
      });
    }
  };

  return (
    <>
      <PageBreadCrumb breadcrumbs={breadcrumbs} />
      <Grid fullWidth={true}>
        <Column lg={16} md={8} sm={4}>
          <Section>
            <Section>
              <Heading>
                <FormattedMessage id="eorder.header" />
              </Heading>
            </Section>
          </Section>
        </Column>
      </Grid>
      <br></br>
      <div className="orderLegendBody">
        <FormattedMessage id="eorder.search1.text" />
        <br></br>
        <div className="formInlineDiv">
          <TextInput
            id="searchValue"
            labelText={intl.formatMessage({ id: "eorder.searchValue" })}
            value={searchValue}
            onChange={(e) => {
              setSearchValue(e.target.value);
            }}
            onKeyDown={(e) => {
              if (e.key === "Enter") {
                searchByIdentifier();
              }
            }}
          />
          <div className="formInlineDiv bottomAlign">
            <Checkbox
              id="allInfo1"
              labelText={intl.formatMessage({ id: "eorder.allInfo" })}
              checked={allInfo}
              onChange={(e) => {
                setAllInfo(e.currentTarget.checked);
              }}
            />
            <div className="formInlineDiv">
              <div></div>
              <Button onClick={searchByIdentifier}>
                <FormattedMessage id="label.button.search" />
              </Button>
            </div>
          </div>
        </div>
        <hr></hr>
        <FormattedMessage id="eorder.search2.text" />
        <br></br>
        <div className="formInlineDiv">
          <CustomDatePicker
            id={"eOrder_startDate"}
            labelText={intl.formatMessage({ id: "eorder.date.start" })}
            value={startDate}
            className="inputDate"
            onChange={(date) => setStartDate(date)}
          />
        </div>
        <div className="formInlineDiv">
          <CustomDatePicker
            id={"eOrder_startDate"}
            labelText={intl.formatMessage({ id: "eorder.date.end" })}
            value={startDate}
            className="inputDate"
            onChange={(date) => setEndDate(date)}
          />
        </div>
        <div className="formInlineDiv">
          <Select
            id="statusId"
            labelText={intl.formatMessage({ id: "eorder.status" })}
            value={statusId}
            onChange={(e) => {
              setStatusId(e.target.value);
            }}
          >
            <SelectItem value="" text="All Statuses" />
            {statusOptions.map((statusOption, index) => {
              return (
                <SelectItem
                  key={index}
                  value={statusOption.id}
                  text={statusOption.value}
                />
              );
            })}
          </Select>
          <div className="formInlineDiv bottomAlign">
            <Checkbox
              id="allInfo2"
              labelText={intl.formatMessage({ id: "eorder.allInfo" })}
              checked={allInfo}
              onChange={(e) => {
                setAllInfo(e.currentTarget.checked);
              }}
            />
            <div className="formInlineDiv">
              <div></div>
              <Button onClick={searchByDateAndStatus}>
                <FormattedMessage id="label.button.search" />
              </Button>
            </div>
          </div>
        </div>
      </div>
      {searchCompleted && !hasEOrders && (
        <FormattedMessage id="eorder.search.noresults" />
      )}
    </>
  );
};

export default EOrderSearch;
