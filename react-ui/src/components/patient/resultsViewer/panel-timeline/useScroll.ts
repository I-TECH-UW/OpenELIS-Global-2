import * as React from 'react';
import { makeThrottled } from '../helpers';

const useScrollIndicator = (xThreshold: number, yThreshold: number): [boolean, boolean, React.Ref<HTMLElement>] => {
  const [xIsScrolled, setXIsScrolled] = React.useState(false);
  const [yIsScrolled, setYIsScrolled] = React.useState(false);

  const ref = React.useCallback(
    (element) => {
      if (!element) {
        return;
      }

      const scrollHandler = makeThrottled(() => {
        setXIsScrolled(element.scrollLeft > xThreshold);
        setYIsScrolled(element.scrollTop > yThreshold);
      }, 200);

      element.addEventListener('scroll', scrollHandler);
      return () => element.removeEventListener('scroll', scrollHandler);
    },
    [xThreshold, yThreshold],
  );

  return [xIsScrolled, yIsScrolled, ref];
};

export default useScrollIndicator;
