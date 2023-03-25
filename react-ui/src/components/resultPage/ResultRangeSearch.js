import React from 'react'
import '../Style.css'
import { injectIntl } from 'react-intl'



import SearchResultRangeForm from '../common/SearchResultRangeForm';

class ResultRangeSearch extends React.Component {


  render() {
    return (
      <>

        <div className='resultPageContent'>
          <SearchResultRangeForm getSelectedResults={this.getSelectedResults}></SearchResultRangeForm>
        </div>
      </>
    );

  }
}

export default injectIntl(ResultRangeSearch)