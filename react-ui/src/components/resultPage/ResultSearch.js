// import React from 'react'
// import '../Style.css'
// import { FormattedMessage, injectIntl } from 'react-intl'
// import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading } from '@carbon/react';
// import { Formik } from "formik";
// import { Add } from '@carbon/react/icons';
// import ResultSearchAccession from './ResultSearchAccession';

import React from 'react'
import '../Style.css'
import { FormattedMessage, injectIntl } from 'react-intl'
import { Form, Stack, TextInput, Select, SelectItem, Button, InlineLoading } from '@carbon/react';
import { Formik } from "formik";
import { Add } from '@carbon/react/icons';
import ResultSearchAccession from './ResultSearchAccession';

class ResultSearch extends React.Component {


  render() {
    return (
      <>

        <div className='resultPageContent'>
          <ResultSearchAccession/>
        </div>
      </>
    );

  }
}

export default injectIntl(ResultSearch)