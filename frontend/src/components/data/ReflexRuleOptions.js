import { FormattedMessage } from 'react-intl';

export const relationOptions = [
  {
    value: "EQUALS",
    label: <FormattedMessage id="label.equals"/>,
  },
  {
    value: "NOT_EQUALS",
    label: <FormattedMessage id="label.notequals"/>,
  },
  {
    value: "INSIDE_NORMAL_RANGE",
    label: <FormattedMessage id="label.inside.normalrange"/>,
  },
  {
    value: "OUTSIDE_NORMAL_RANGE",
    label: <FormattedMessage id="label.outside.normalrange"/>,
  },
];

export const overallOptions = [
  {
    value: "ANY",
    label: <FormattedMessage id="label.any"/>,
  },
  {
    value: "ALL",
    label: <FormattedMessage id="all.label"/>,
  },
];

export const actionOptions = [
  {
    value: "ADD_TEST",
    label: <FormattedMessage id="label.test.add"/>,
  },
  {
    value: "ADD_NOTIFICATION",
    label: <FormattedMessage id="label.add.notification"/>,
  },
  {
    value: "ADD_INTERNAL_NOTE",
    label: <FormData id="rulebuilder.label.addInternalNote"/>,
  },
  {
    value: "ADD_EXTERNAL_NOTE",
    label: <FormattedMessage id="rulebuilder.label.addExternalNote"/>,
  },
  {
    value: "ADD_INTERNAL_TRIGGER_MESSAGE",
    label: <FormattedMessage id="rulebuilder.label.add.internaltrigger"/>,
  },
  {
    value: "ADD_EXTERNAL_TRIGGER_MESSAGE",
    label: <FormattedMessage id="rulebuilder.label.add.externaltrigger"/>,
  },
];
