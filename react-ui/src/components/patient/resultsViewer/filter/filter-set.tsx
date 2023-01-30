import React, { useContext, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { Accordion, AccordionItem, Checkbox, Button, Search } from '@carbon/react';
import { TreeViewAlt, Close, Search as SearchIcon } from '@carbon/react/icons';
import { useConfig, useLayoutType } from '../commons';
import type { FilterNodeProps, FilterLeafProps } from './filter-types';
import FilterContext from './filter-context';
import styles from './filter-set.styles.scss';

const isIndeterminate = (kids, checkboxes) => {
  return kids && !kids?.every((kid) => checkboxes[kid]) && !kids?.every((kid) => !checkboxes[kid]);
};

interface FilterSetProps {
  hideFilterSetHeader?: boolean;
}

const FilterSet: React.FC<FilterSetProps> = ({ hideFilterSetHeader = false }) => {
  const { roots } = useContext(FilterContext);
  const config = useConfig();
  const tablet = useLayoutType();
  const { t } = useTranslation();
  const { resetTree } = useContext(FilterContext);
  const [searchTerm, setSearchTerm] = useState('');
  const [showSearchInput, setShowSearchInput] = useState(false);

  return (
    <div className={!tablet ? styles.stickyFilterSet : ''}>
      {!hideFilterSetHeader &&
        (!showSearchInput ? (
          <div className={styles.filterSetHeader}>
            <h4>{t('tree', 'Tree')}</h4>
            <div className={styles.filterSetActions}>
              <Button
                kind="ghost"
                size="sm"
                onClick={resetTree}
                renderIcon={(props) => <TreeViewAlt size={16} {...props} />}
              >
                {t('resetTreeText', 'Reset tree')}
              </Button>
              <Button kind="ghost" size="sm" renderIcon={SearchIcon} onClick={() => setShowSearchInput(true)}>
                {t('search', 'Search')}
              </Button>
            </div>
          </div>
        ) : (
          <div className={styles.filterTreeSearchHeader}>
            <Search size="sm" value={searchTerm} onChange={(evt) => setSearchTerm(evt.target.value)} light />
            <Button kind="secondary" size="sm" onClick={() => {}}>
              {t('search', 'Search')}
            </Button>
            <Button hasIconOnly renderIcon={Close} size="sm" kind="ghost" onClick={() => setShowSearchInput(false)} />
          </div>
        ))}
      <div className={styles.filterSetContent}>
        {roots?.map((root, index) => (
          <div className={styles.nestedAccordion} key={`filter-node-${index}`}>
            <FilterNode root={root} level={0} open={config.concepts[index].defaultOpen} />
          </div>
        ))}
      </div>
    </div>
  );
};

const FilterNode = ({ root, level, open }: FilterNodeProps) => {
  const tablet = false;
  const { checkboxes, parents, updateParent } = useContext(FilterContext);
  const indeterminate = isIndeterminate(parents[root.flatName], checkboxes);
  const allChildrenChecked = parents[root.flatName]?.every((kid) => checkboxes[kid]);
  return (
    <Accordion align="start" size={tablet ? 'md' : 'sm'}>
      <AccordionItem
        title={
          <Checkbox
            id={root?.flatName}
            checked={root.hasData && allChildrenChecked}
            indeterminate={indeterminate}
            labelText={`${root?.display} (${parents?.[root?.flatName]?.length})`}
            onChange={() => updateParent(root.flatName)}
            disabled={!root.hasData}
          />
        }
        style={{ paddingLeft: `${level > 0 ? 1 : 0}rem` }}
        open={open ?? false}
      >
        {!root?.subSets?.[0]?.obs &&
          root?.subSets?.map((node, index) => <FilterNode root={node} level={level + 1} key={index} />)}
        {root?.subSets?.[0]?.obs && root.subSets?.map((obs, index) => <FilterLeaf leaf={obs} key={index} />)}
      </AccordionItem>
    </Accordion>
  );
};

const FilterLeaf = ({ leaf }: FilterLeafProps) => {
  const { checkboxes, toggleVal } = useContext(FilterContext);
  return (
    <div className={styles.filterItem}>
      <Checkbox
        id={leaf?.flatName}
        labelText={leaf?.display}
        checked={checkboxes?.[leaf.flatName]}
        onChange={() => toggleVal(leaf.flatName)}
        disabled={!leaf.hasData}
      />
    </div>
  );
};

export default FilterSet;
