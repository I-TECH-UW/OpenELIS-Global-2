import useSWR from 'swr';
import { OBSERVATION_INTERPRETATION } from '../commons';
import { assessValue } from '../loadPatientTestData/helpers';
import { useMemo } from 'react';
import { FetchResponse, openmrsFetch, showNotification } from '../commons';
import { TreeNode } from '../filter/filter-types';
import {obs} from '../../../data/dummy/obs.js'

function computeTrendlineData(treeNode: TreeNode): Array<TreeNode> {
  const tests: Array<TreeNode> = [];
  if (!treeNode) {
    return tests;
  }
  treeNode?.subSets.forEach((subNode) => {
    if ((subNode as TreeNode)?.obs) {
      const TreeNode = subNode as TreeNode;
      const assess = assessValue(TreeNode.obs);
      tests.push({
        ...TreeNode,
        range: TreeNode.hiNormal && TreeNode.lowNormal ? `${TreeNode.lowNormal} - ${TreeNode.hiNormal}` : '',
        obs: TreeNode.obs.map((ob) => ({ ...ob, interpretation: assess(ob.value) })),
      });
    } else if (subNode?.subSets) {
      const subTreesTests = computeTrendlineData(subNode as TreeNode); // recursion
      tests.push(...subTreesTests);
    }
  });
  return tests;
}

export function useObstreeData(
  patientUuid: string,
  conceptUuid: string,
): {
  isLoading: boolean;
  trendlineData: TreeNode;
  isValidating: boolean;
} {
  // const { data, error, isLoading, isValidating } = useSWR<FetchResponse<TreeNode>, Error>(
  //   `/ws/rest/v1/obstree?patient=${patientUuid}&concept=${conceptUuid}`,
  //   openmrsFetch,
  // );
  const { data, error, isLoading ,isValidating } = { data : {data :obs}  , error:{name:"sss" ,message:"lll"}, isLoading:false ,isValidating:true};
  if (error) {
    showNotification({
      title: error.name,
      description: error.message,
      kind: 'error',
    });
  }

  const returnValue = useMemo(
    () => ({
      isLoading,
      trendlineData:
        computeTrendlineData(data?.data)?.[0] ??
        ({
          obs: [],
          display: '',
          hiNormal: 0,
          lowNormal: 0,
          units: '',
          range: '',
        } as TreeNode),
      isValidating,
    }),
    [data?.data, isLoading, isValidating],
  );

  return returnValue;
}
