
export interface CalculatedValueFormModel {
    id: number | undefined;
    name: string;
    operations: Array<OperationModel>
    sampleId : number
    testName :string
    testId : number
}
export type OperationType = 'TEST_RESULT' | 'MATH_FUNCTION' | 'INTEGER' | 'PATIENT_ATTRIBUTE' | '';

export interface OperationModel {
    type: OperationType
    value: number|string
    sampleId : number
    testName :string
}

export const CalculatedValueFormValues:CalculatedValueFormModel = {
    id: null,
    name: "",
    operations: [
        {
            type: 'TEST_RESULT',
            value: "",
            testName : "",
            sampleId: null,
        }
    ],
    testName : "",
    sampleId: null,
    testId: null
};