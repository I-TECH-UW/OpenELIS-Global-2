import { usePatient, openmrsFetch } from '../commons';
import { useMemo } from 'react';
import useSWR from 'swr';
import useSWRInfinite from 'swr/infinite';
import { assessValue, exist } from '../loadPatientTestData/helpers';
import { getFromOpeElisServerSync } from "../../../utils/Utils.js";

export const getName = (prefix, name) => {
  return prefix ? `${prefix}-${name}` : name;
};

const augmentObstreeData = (node, prefix) => {
  const outData = JSON.parse(JSON.stringify(node));
  outData.flatName = getName(prefix, node.display);
  outData.hasData = false;

  if (outData?.subSets?.length) {
    outData.subSets = outData.subSets.map((subNode) => augmentObstreeData(subNode, getName(prefix, node?.display)));
    outData.hasData = outData.subSets.some((subNode) => subNode.hasData);
  }
  if (exist(outData?.hiNormal, outData?.lowNormal)) {
    outData.range = `${outData.lowNormal} – ${outData.hiNormal}`;
  }
  if (outData?.obs?.length) {
    const assess = assessValue(outData);
    outData.obs = outData.obs.map((ob) => ({ ...ob, interpretation: assess(ob.value) }));
    outData.hasData = true;
  }

  return { ...outData };
};

const useGetObstreeData = (conceptUuid) => {
  const { patientUuid } = usePatient();
  const response = useSWR(`/ws/rest/v1/obstree?patient=${patientUuid}&concept=${conceptUuid}`, openmrsFetch);
  const result = useMemo(() => {
    if (response.data) {
      const { data, ...rest } = response;
      const newData = augmentObstreeData(data?.data, '');
      return { ...rest, loading: false, data: newData };
    } else {
      return {
        data: {},
        error: false,
        loading: true,
      };
    }
  }, [response]);
  return result;
};



const useGetManyObstreeData = (patientUuid) => {
 
  var { data, error, isLoading } = {data : [] , error: null , isLoading: null}

  const fetchResultsTree = (resultsTree) => {
        data  =resultsTree ;
        error = false ;
        isLoading = false;
  }
   if(patientUuid){
    getFromOpeElisServerSync(`/rest/result-tree?patientId=${patientUuid}` , fetchResultsTree)
   }

  const result = useMemo(() => {
    return (
      data?.map((resp) => {
        if (resp) {
          const { ...rest } = resp;
          const newData = augmentObstreeData(resp, '');
          return { ...rest, loading: false, data: newData };
        } else {
          return {
            data: {},
            error: false,
            loading: false,
          };
        }
      }) || [
        {
          data: {},
          error: false,
          loading: false,
        },
      ]
    );
  }, [data]);
  const roots = result.map((item) => item.data);
  const loading = result.some((item) => item.loading);
  return { roots, loading, error };
}

export default useGetManyObstreeData;
export { useGetManyObstreeData, useGetObstreeData };
