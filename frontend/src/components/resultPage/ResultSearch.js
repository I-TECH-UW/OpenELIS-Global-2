import React from 'react'
import '../Style.css'
import { injectIntl } from 'react-intl'
import ResultSearchPage from '../common/SearchResultForm';

class ResultSearch extends React.Component {

  render() {
    return (
      <>
        <div className='resultPageContent'>
          <ResultSearchPage />
        </div>
      </>
    );

  }
}

export default injectIntl(ResultSearch)