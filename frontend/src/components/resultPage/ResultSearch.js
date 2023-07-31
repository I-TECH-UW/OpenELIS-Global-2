import React from 'react'
import '../Style.css'
import { injectIntl } from 'react-intl'



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