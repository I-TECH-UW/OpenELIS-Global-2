
export interface CalculatedValueFormModel {
    id: number | undefined;
    name: string;
    operations: Array<OperationModel>
    sampleId : number
    testId : number
}
export type OperationType = 'TEST_RESULT' | 'MATH_FUNCTION' | 'INTEGER' | 'PATIENT_ATTRIBUTE' | '';

export interface OperationModel {
    id : number
    type: OperationType
    value: string|number
    sampleId : number
}

export const CalculatedValueFormValues:CalculatedValueFormModel = {
    id: null,
    name: "",
    operations: [
        {
            id : null,
            type: 'TEST_RESULT',
            value: "",
            sampleId: null,
        }
    ],
    sampleId: null,
    testId: null
};