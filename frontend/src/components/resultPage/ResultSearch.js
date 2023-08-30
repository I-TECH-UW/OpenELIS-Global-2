import React from "react";
import "../Style.css";
import { injectIntl } from "react-intl";
import ResultSearchPage from "../common/SearchResultForm";

function ResultSearch() {
  return (
    <>
      <div className="resultPageContent">
        <ResultSearchPage />
      </div>
    </>
  );
}

export default injectIntl(ResultSearch);
