import React from "react";
import { InlineNotification } from "@carbon/react";
import { FormattedMessage } from "react-intl";
import { useOrderContext, SaveStatus } from "./OrderContext";

/**
 * What a blocked save asks the user to correct. Fields the screen already marks
 * inline are left out so nothing is reported twice.
 */
const SaveFailureNotice = ({ inlineFields = [] }) => {
  const { saveStatus, error, fieldErrors } = useOrderContext();
  if (saveStatus !== SaveStatus.ERROR) {
    return null;
  }
  const remaining = Object.entries(fieldErrors || {}).filter(
    ([field]) => !inlineFields.includes(field),
  );
  return (
    <InlineNotification
      kind="error"
      lowContrast
      hideCloseButton
      className="order-save-failure"
      title={
        <FormattedMessage
          id="order.save.blocked"
          defaultMessage="The order was not saved"
        />
      }
      subtitle={
        <span>
          {error}
          {remaining.length > 0 && (
            <ul className="order-save-failure-fields">
              {remaining.map(([field, message]) => (
                <li key={field}>
                  {field}: {message}
                </li>
              ))}
            </ul>
          )}
        </span>
      }
    />
  );
};

export default SaveFailureNotice;
