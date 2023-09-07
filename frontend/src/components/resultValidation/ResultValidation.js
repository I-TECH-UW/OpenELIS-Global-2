import React from "react";
import "../Style.css";
import { injectIntl } from "react-intl";
import SearchResultValidationForm from "../common/SearchResultValidationForm";

function ResultValidation() {
  return (
    <>
      <div className="resultPageContent">
        <SearchResultValidationForm />
      </div>
    </>
  );
}

export default injectIntl(ResultValidation);
