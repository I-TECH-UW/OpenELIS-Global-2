/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
*
* The Original Code is OpenELIS code.
*
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package us.mn.state.health.lims.dictionary.valueholder;

import java.util.Comparator;

import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.common.valueholder.ValueHolder;
import us.mn.state.health.lims.common.valueholder.ValueHolderInterface;
import us.mn.state.health.lims.dictionarycategory.valueholder.DictionaryCategory;

public class Dictionary extends BaseObject{

	private static final long serialVersionUID = 1L;

	public class ComparatorLocalizedName implements Comparator<Dictionary> {
        public int compare(Dictionary o1, Dictionary o2) {
            return o1.getLocalizedName().compareTo(o2.getDefaultLocalizedName());
        }
    }

    private String id;

	private String isActive;

	private String dictEntry;

    
	private String selectedDictionaryCategoryId;

    
	private ValueHolderInterface dictionaryCategory;

    
	private String localAbbreviation;

	private Integer sortOrder;
    
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }	

	public String getLocalAbbreviation() {
		return localAbbreviation;
	}

	public void setLocalAbbreviation(String localAbbreviation) {
		this.localAbbreviation = localAbbreviation;
	}

	public Dictionary() {
		super();
		this.dictionaryCategory = new ValueHolder();
	}

	public String getId() {
		return this.id;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public DictionaryCategory getDictionaryCategory() {
		return (DictionaryCategory) this.dictionaryCategory.getValue();
	}

	public void setDictionaryCategory(DictionaryCategory dictionaryCategory) {
		this.dictionaryCategory.setValue(dictionaryCategory);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}



	public String getDictEntry() {
		return dictEntry;
	}

	public void setDictEntry(String dictEntry) {
		this.dictEntry = dictEntry;
	}

    
	public String getDictEntryDisplayValue() {
		String dictEntryDisplayValue;
		if (!StringUtil.isNullorNill(this.localAbbreviation)) {
		    
			dictEntryDisplayValue = localAbbreviation + IActionConstants.LOCAL_CODE_DICT_ENTRY_SEPARATOR_STRING + dictEntry;
		} else {
			dictEntryDisplayValue = dictEntry;
		}
		return dictEntryDisplayValue;
	}

	public String getSelectedDictionaryCategoryId() {
		return selectedDictionaryCategoryId;
	}

	public void setSelectedDictionaryCategoryId(String selectedDictionaryCategoryId) {
		this.selectedDictionaryCategoryId = selectedDictionaryCategoryId;
	}
	

	@Override
	protected String getDefaultLocalizedName() {
		return dictEntry;
	}

	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", localAbbreviation="
				+ localAbbreviation + ", nameKey=" + nameKey + "]";
	}
}
