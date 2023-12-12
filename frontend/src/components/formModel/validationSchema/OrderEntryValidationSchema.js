import * as Yup from "yup";
import CreatePatientValidationSchema from "./CreatePatientValidationShema";

const OrderEntryValidationSchema = Yup.object().shape({
  sampleXML: Yup.string().required("Sample is required"),
  patientProperties: CreatePatientValidationSchema,
  sampleOrderItems: Yup.object().shape({
    labNo: Yup.string().required("Sample Lab Number is required"),
    referringSiteName: Yup.string().required("Referring Site Name is required"),
    providerLastName: Yup.string().required("Provider Last Name is required"),
    providerFirstName: Yup.string().required("Provider First Name is required"),
  }),
});

export default OrderEntryValidationSchema;
