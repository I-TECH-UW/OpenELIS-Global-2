export const rowData = [
    {
      id: 'a',
      name: 'Load Balancer 3',
      protocol: 'HTTP',
      port: 3000,
      rule: 'Round robin',
      attached_groups: 'Kevin’s VM Groups',
      status: 'Disabled',
    },
    {
      id: 'b',
      name: 'Load Balancer 1',
      protocol: 'HTTP',
      port: 443,
      rule: 'Round robin',
      attached_groups: 'Maureen’s VM Groups',
      status: 'Starting',
    },
    {
      id: 'c',
      name: 'Load Balancer 2',
      protocol: 'HTTP',
      port: 80,
      rule: 'DNS delegation',
      attached_groups: 'Andrew’s VM Groups',
      status: 'Active',
    },
    {
      id: 'd',
      name: 'Load Balancer 6',
      protocol: 'HTTP',
      port: 3000,
      rule: 'Round robin',
      attached_groups: 'Marc’s VM Groups',
      status: 'Disabled',
    },
    {
      id: 'e',
      name: 'Load Balancer 4',
      protocol: 'HTTP',
      port: 443,
      rule: 'Round robin',
      attached_groups: 'Mel’s VM Groups',
      status: 'Starting',
    },
    {
      id: 'f',
      name: 'Load Balancer 5',
      protocol: 'HTTP',
      port: 80,
      rule: 'DNS delegation',
      attached_groups: 'Ronja’s VM Groups',
      status: 'Active',
    },
  ];
  
  export const headerData = [
    {
      key: 'name',
      header: 'Last Name',
    },
    {
      key: 'protocol',
      header: 'First Name',
    },
    {
      key: 'port',
      header: 'Gender',
    },
    {
      key: 'rule',
      header: 'Date Of Birth',
    },
    {
      key: 'attached_groups',
      header: 'Unique Health ID number',
    },
    {
      key: 'status',
      header: 'National ID',
    },
  ];
  