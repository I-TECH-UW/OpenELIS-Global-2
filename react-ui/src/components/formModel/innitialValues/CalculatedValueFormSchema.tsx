
export interface CalculatedValueFormModel {
    id: number | undefined;
    ruleName: string;
    operations: Array<Operation>
}
export type OperationType = 'TEST_RESULT' | 'MATH_FUNCTION' | 'INTEGER' | 'PATIENT_ATTRIBUTE' | '';

export interface Operation {
    type: OperationType
    value: any
}

export const CalculatedValueFormValues:CalculatedValueFormModel = {
    id: null,
    ruleName: "",
    operations: [
        {
            type: 'TEST_RESULT',
            value: ""
        }
    ]
};