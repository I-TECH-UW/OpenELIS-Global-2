import React, { useEffect, useRef, useState } from "react";
import { Column, Grid, Select, SelectItem } from "@carbon/react";
import { FormattedMessage, injectIntl } from "react-intl";
import "../Style.css";
import { getFromOpenElisServer } from "../utils/Utils";

function PanelSelectForm(props) {
  const mounted = useRef(false);
  const [panels, setPanels] = useState([]);

  const handleChange = (e) => {
    props.value(e.target.value, e.target.selectedOptions[0].text);
  };

  const getPanels = (res) => {
    if (mounted.current) {
      setPanels(res);
    }
  };

  useEffect(() => {
    mounted.current = true;
    getFromOpenElisServer("/rest/panels", getPanels);
    return () => {
      mounted.current = false;
    };
  }, []);

  return (
    <>
      <Grid fullWidth={true}>
        <Column lg={16}>
          <Select
            defaultValue="placeholder-item"
            id="select-1"
            invalidText={
              <FormattedMessage id="workplan.panel.selection.error.msg" />
            }
            helperText={props.title}
            labelText=""
            onChange={handleChange}
          >
            <SelectItem text="" value="" />
            {panels.map((item, idx) => {
              return <SelectItem key={idx} text={item.value} value={item.id} />;
            })}
          </Select>
        </Column>
      </Grid>
    </>
  );
}

export default injectIntl(PanelSelectForm);
