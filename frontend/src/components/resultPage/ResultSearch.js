import React from 'react'
import '../Style.css'
import { injectIntl } from 'react-intl'
import ResultSearchPage from './SearchResultForm';

class ResultSearch extends React.Component {

  render() {
    return (
      <>
        <div className='orderLegendBody'>
          <ResultSearchPage />
        </div>
      </>
    );

  }
}

export default injectIntl(ResultSearch)