import React from "react";
import { injectIntl } from "react-intl";
import ReflexRule from "./ReflexRuleForm";

function ReflexTestManagement() {
  return (
    <>
      <div className="adminPageContent">
        <ReflexRule />
      </div>
    </>
  );
}

export default injectIntl(ReflexTestManagement);
