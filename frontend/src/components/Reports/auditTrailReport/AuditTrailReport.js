import React, { useEffect, useState, useRef } from 'react';
import {
  Form,
  Heading,
  Select,
  SelectItem,
  Grid,
  Column,
  Section,
  Button,
  Loading,
} from "@carbon/react";
import { FormattedMessage, useIntl } from 'react-intl';
import "../../Style.css";
import { getFromOpenElisServer } from "../../utils/Utils";
import { AlertDialog } from "../../common/CustomNotification";
import CustomDatePicker from "../../common/CustomDatePicker";
import PageBreadCrumb from "../../common/PageBreadCrumb";
import config from "../../../config.json";

const AuditTrailReport = () => {
  return (
    <div>Audit Trail Report</div>
  )
}

export default AuditTrailReport