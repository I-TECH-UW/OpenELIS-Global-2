
export interface CalculatedValueFormModel {
    id: number | undefined;
    ruleName: string;
    operations: Array<Operation>
    sampleId : number
    testName :string
    testId : number
}
export type OperationType = 'TEST_RESULT' | 'MATH_FUNCTION' | 'INTEGER' | 'PATIENT_ATTRIBUTE' | '';

export interface Operation {
    type: OperationType
    value: number|string
    sampleId : number
    testName :string
}

export const CalculatedValueFormValues:CalculatedValueFormModel = {
    id: null,
    ruleName: "",
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