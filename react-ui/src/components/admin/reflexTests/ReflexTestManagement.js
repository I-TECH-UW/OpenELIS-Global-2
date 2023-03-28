import React from 'react'
import { injectIntl } from 'react-intl'
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