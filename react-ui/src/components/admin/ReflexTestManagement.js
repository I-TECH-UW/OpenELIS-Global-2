import React from 'react'
import '../Style.css'
import { FormattedMessage, injectIntl } from 'react-intl'
import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading } from '@carbon/react';
import { Formik } from "formik";
import { Add } from '@carbon/react/icons';
import ReflexRule from './ReflexRule';


class ReflexTestManagement extends React.Component {


  render() {
    return (
      <>
        <div className='adminPageContent'>
          <ReflexRule/>
        </div>
      </>
    );

  }
}

export default injectIntl(ReflexTestManagement)