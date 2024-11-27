import * as Yup from "yup";

const CreatePatientValidationSchema = Yup.object().shape({
  nationalId: Yup.string().required("National ID Required"),
  birthDateForDisplay: Yup.string()
    .required("Patient Birth date Required")
    .test("valid-date", "Invalid date format", function (value) {
      const dateFormat = /^\d{2}\/\d{2}\/\d{4}$/;
      if (!value || !value.match(dateFormat)) {
        return false;
      }
      const [day, month, year] = value.split("/");
      const date = new Date(`${year}-${month}-${day}`);

      return date instanceof Date && !isNaN(date);
    }),
  patientContact: Yup.object().shape({
    person: Yup.object().shape({
      email: Yup.string().email("Contact Email Must Be Valid"),
    }),
  }),
  gender: Yup.string().required("Gender is Required"),
});

export default CreatePatientValidationSchema;
