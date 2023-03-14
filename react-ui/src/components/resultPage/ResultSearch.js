import React from 'react'
import '../Style.css'
import { FormattedMessage, injectIntl } from 'react-intl'
import { Select, SelectItem, InlineLoading } from '@carbon/react';
import { Formik } from "formik";
import { Add } from '@carbon/react/icons';

import {
  Heading,
  Form,
  FormLabel,
  TextInput,
  Button,
  Grid,
  Column,
  DatePicker,
  DatePickerInput,
  RadioButton,
  RadioButtonGroup,
  Stack,
  DataTable, TableContainer, Table, TableHead, TableRow, TableHeader, TableBody, TableCell,
  Section ,
  Pagination

} from '@carbon/react';
import SearchResultForm from '../common/SearchResultForm';

class ResultSearch extends React.Component {


  render() {
    return (
      <>

        <div className='resultPageContent'>
          <SearchResultForm getSelectedResults={this.getSelectedResults}></SearchResultForm>
        </div>
      </>
    );

  }
}

export default injectIntl(ResultSearch)