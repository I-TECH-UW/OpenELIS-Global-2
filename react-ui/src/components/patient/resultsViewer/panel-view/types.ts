import { OBSERVATION_INTERPRETATION } from '../commons';

export interface FhirResponse<T> {
  total: number;
  link: Array<{
    relation: 'self' | 'next' | 'previous';
    url: string;
  }>;
  entry: Array<{
    resource: T;
  }>;
}

export interface FHIRObservationResource {
  resourceType: string;
  id: string;
  category: Array<{
    coding: Array<{
      system: string;
      code: string;
      display: string;
    }>;
  }>;
  code: {
    coding: Array<{
      code: string;
      display: string;
    }>;
    text: string;
  };
  encounter?: {
    reference: string;
    type: string;
  };
  effectiveDateTime: string;
  issued: string;
  valueString?: string;
  valueQuantity?: {
    value: number;
    unit: string;
    system: string;
    code: string;
  };
  valueCodeableConcept?: {
    coding: [
      {
        code: string;
        display: string;
      },
    ];
    text: string;
  };
  referenceRange: Array<{
    low?: {
      value: number;
    };
    high?: {
      value: number;
    };
    type: {
      coding: Array<{
        system: string;
        code: string;
      }>;
    };
  }>;
  hasMember?: Array<{
    reference: string;
  }>;
}

export interface Concept {
  uuid: string;
  display: string;
  conceptClass: {
    uuid: string;
    display: string;
    name: string;
  };
  answers: [];
  setMembers: [];
  hiNormal: number;
  hiAbsolute: number;
  hiCritical: number;
  lowNormal: number;
  lowAbsolute: number;
  lowCritical: number;
  units: string;
  allowDecimal: boolean;
  displayPrecision: null;
  attributes: [];
}

export interface ConceptMeta {
  display: string;
  hiNormal: number;
  hiAbsolute: number;
  hiCritical: number;
  lowNormal: number;
  lowAbsolute: number;
  lowCritical: number;
  units: string;
  getInterpretation: (value: string) => OBSERVATION_INTERPRETATION;
  range: string;
}

export interface ObsRecord extends FHIRObservationResource {
  conceptUuid: string;
  relatedObs: Array<ObsRecord>;
  meta: ConceptMeta;
  value: string;
  name: string;
  interpretation: OBSERVATION_INTERPRETATION;
}

export enum observationInterpretation {
  NORMAL = 'NORMAL',
  HIGH = 'HIGH',
  CRITICALLY_HIGH = 'CRITICALLY_HIGH',
  OFF_SCALE_HIGH = 'OFF_SCALE_HIGH',
  LOW = 'LOW',
  CRITICALLY_LOW = 'CRITICALLY_LOW',
  OFF_SCALE_LOW = 'OFF_SCALE_LOW',
}

export interface LabSetRecord {
  [k: string]: ObservationSet;
}
export interface ObservationSet {
  entries: ObsRecord[];
  type: string;
  uuid: string;
  meta: ConceptMeta;
}
