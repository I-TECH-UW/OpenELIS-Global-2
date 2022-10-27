import React from 'react'
import '../Style.css'
import { FormattedMessage, injectIntl } from 'react-intl'
import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading } from '@carbon/react';
import { Formik } from "formik";
import { Add } from '@carbon/react/icons';
import Rule from './RuleTemplate';
import AddRule from './AddRule';


class ReflectTests extends React.Component {


  render() {
    return (
      <>
        <div className='adminPageContent'>
          <AddRule/>
        </div>
      </>
    );

  }
}

export default injectIntl(ReflectTests)