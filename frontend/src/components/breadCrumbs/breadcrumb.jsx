import React from 'react'
import { Breadcrumb, BreadcrumbItem } from '@carbon/react'
import { getBreadcrumbs } from './db';

export const Breadcrumbs = () => {
  const breadcrumbs = getBreadcrumbs()

  return (
    <Breadcrumb>
      {breadcrumbs.map((bc, index) => (
        <BreadcrumbItem key={`breadcrumb-item-${index}`} href={bc.path}>
          {bc.label}
        </BreadcrumbItem>
      ))}
    </Breadcrumb>
  );
};

export default Breadcrumbs;
