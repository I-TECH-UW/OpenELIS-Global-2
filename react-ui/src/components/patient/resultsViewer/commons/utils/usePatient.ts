/** @module @category API */
import { useEffect, useReducer } from "react";
import {  PatientUuid } from "../../commons";
import patData from "../../../../data/dummy/patient.json"

export type NullablePatient = {} | null;


interface CurrentPatientState {
  patientUuid: string | null;
  patient: NullablePatient;
  isPendingUuid: boolean;
  isLoadingPatient: boolean;
  err: Error | null;
}

interface LoadPatient {
  type: ActionTypes.loadPatient;
  patientUuid: string | null;
}

interface NewPatient {
  type: ActionTypes.newPatient;
  patient: NullablePatient;
}

interface PatientLoadError {
  type: ActionTypes.loadError;
  err: Error | null;
}

type Action = LoadPatient | NewPatient | PatientLoadError;

enum ActionTypes {
  loadPatient = "loadPatient",
  newPatient = "newPatient",
  loadError = "patientLoadError",
}

const initialState: CurrentPatientState = {
  patientUuid: null,
  patient: null,
  isPendingUuid: true,
  isLoadingPatient: false,
  err: null,
};

function getPatientUuidFromUrl(): PatientUuid {
  const match = /\/patient\/([a-zA-Z0-9\-]+)\/?/.exec("");
  return match && match[1];
}

function reducer(
  state: CurrentPatientState,
  action: Action
): CurrentPatientState {
  switch (action.type) {
    case ActionTypes.loadPatient:
      return {
        ...state,
        patientUuid: action.patientUuid,
        patient: null,
        isPendingUuid: false,
        isLoadingPatient: true,
        err: null,
      };
    case ActionTypes.newPatient:
      return {
        ...state,
        patient: action.patient,
        isPendingUuid: false,
        isLoadingPatient: false,
        err: null,
      };
    case ActionTypes.loadError:
      return {
        ...state,
        patient: null,
        isPendingUuid: false,
        isLoadingPatient: false,
        err: action.err,
      };
    default:
      return state;
  }
}

/**
 * This React hook returns a patient object. If the `patientUuid` is provided
 * as a parameter, then the patient for that UUID is returned. If the parameter
 * is not provided, the patient UUID is obtained from the current route, and
 * a route listener is set up to update the patient whenever the route changes.
 */
export function usePatient(patientUuid?: string) {
  const [state, dispatch] = useReducer(reducer, {
    ...initialState,
    patientUuid: patientUuid ?? null,
    isPendingUuid: !patientUuid,
    isLoadingPatient: !!patientUuid,
  });

  useEffect(() => {
    if (state.isPendingUuid) {
      const patientUuidFromUrl = getPatientUuidFromUrl();
      if (patientUuidFromUrl) {
        dispatch({
          type: ActionTypes.loadPatient,
          patientUuid: patientUuidFromUrl,
        });
      } else {
        dispatch({ type: ActionTypes.newPatient, patient: null });
      }
    }

    let active = true;
    if (state.isLoadingPatient && state.patientUuid) {
      // fetchCurrentPatient(state.patientUuid).then(
      //   (patient) =>
      //     active &&
      //     dispatch({
      //       patient: patient.data,
      //       type: ActionTypes.newPatient,
      //     }),
      //   (err) =>
      //     active &&
      //     dispatch({
      //       err,
      //       type: ActionTypes.loadError,
      //     })
      // );
      dispatch({
              patient: patData,
              type: ActionTypes.newPatient,
            })
    }
    return () => {
      active = false;
    };
  }, [state.isPendingUuid, state.isLoadingPatient, state.patientUuid]);

  useEffect(() => {
    const handleRouteUpdate = (evt) => {
      const newPatientUuid = getPatientUuidFromUrl();
      if (newPatientUuid != state.patientUuid) {
        dispatch({
          type: ActionTypes.loadPatient,
          patientUuid: newPatientUuid,
        });
      }
    };
    window.addEventListener("single-spa:routing-event", handleRouteUpdate);
    return () =>
      window.removeEventListener("single-spa:routing-event", handleRouteUpdate);
  }, [state.patientUuid]);

  return {
    isLoading: state.isPendingUuid || state.isLoadingPatient,
    patient: state.patient,
    patientUuid: patientUuid ?? state.patientUuid,
    error: state.err,
  };
}
