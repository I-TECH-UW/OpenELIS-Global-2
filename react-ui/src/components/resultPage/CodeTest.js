import React from 'react'
import '../Style.css'
import { injectIntl } from 'react-intl'




import CodeTestForm from '../common/CodeTestForm';

class CodeTest extends React.Component {


  render() {
    return (
      <>

        <div className='resultPageContent'>
          <CodeTestForm getSelectedResults={this.getSelectedResults}></CodeTestForm>
        </div>
      </>
    );

  }
}

export default injectIntl(CodeTest)