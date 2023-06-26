import React from 'react'
import '../Style.css'
import { injectIntl } from 'react-intl'



import SearchResultValidationForm from '../common/SearchResultValidationForm';

class ResultValidation extends React.Component {


  render() {
    return (
      <>

        <div className='resultPageContent'>
          <SearchResultValidationForm getSelectedResults={this.getSelectedResults}></SearchResultValidationForm>
        </div>
      </>
    );

  }
}

export default injectIntl(ResultValidation)