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
package us.mn.state.health.lims.sampleitem.valueholder;

import java.sql.Timestamp;

import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.common.valueholder.ValueHolder;
import us.mn.state.health.lims.common.valueholder.ValueHolderInterface;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.sourceofsample.valueholder.SourceOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

public class SampleItem extends BaseObject {

	private static final long serialVersionUID = 1L;

	private String id;

	private String quantity;

	private ValueHolderInterface sample;
	private String sampleItemId;
	private String sortOrder;
	private ValueHolderInterface sourceOfSample;
	private String sourceOfSampleId;
	private String sourceOther;
	private ValueHolderInterface typeOfSample;
	private String typeOfSampleId;
	private ValueHolderInterface unitOfMeasure;
	private String unitOfMeasureName;
	private String externalId;
	private Timestamp collectionDate;
	private String statusId;
	private String collector;
	
	public SampleItem() {
		super();
		this.typeOfSample = new ValueHolder();
		this.sourceOfSample = new ValueHolder();
		this.unitOfMeasure = new ValueHolder();
		this.sample = new ValueHolder();
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Timestamp getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Timestamp collectionDate) {
		this.collectionDate = collectionDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getTypeOfSampleId() {
		if( typeOfSampleId == null){
			if( getTypeOfSample() != null){
				typeOfSampleId = getTypeOfSample().getId();
			}
		}

		return typeOfSampleId;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSourceOfSampleId() {
		if( sourceOfSampleId == null){
			if(getSourceOfSample() != null){
				sourceOfSampleId = getSourceOfSample().getId();
			}
		}
		return sourceOfSampleId;
	}

	public void setSourceOfSampleId(String sourceOfSampleId) {
		this.sourceOfSampleId = sourceOfSampleId;
	}

	public String getSourceOther() {
		return sourceOther;
	}

	public void setSourceOther(String sourceOther) {
		this.sourceOther = sourceOther;
	}

	public Sample getSample() {
		return (Sample) this.sample.getValue();
	}

	public void setSample(Sample sample) {
		this.sample.setValue(sample);
	}

	public String getSampleItemId() {
		return sampleItemId;
	}

	public void setSampleItemId(String sampleItemId) {
		this.sampleItemId = sampleItemId;
	}

	public TypeOfSample getTypeOfSample() {
		return (TypeOfSample) this.typeOfSample.getValue();
	}

	public void setTypeOfSample(TypeOfSample typeOfSample) {
		this.typeOfSample.setValue(typeOfSample);
	}

	public SourceOfSample getSourceOfSample() {
		return (SourceOfSample) this.sourceOfSample.getValue();
	}

	public void setSourceOfSample(SourceOfSample sourceOfSample) {
		this.sourceOfSample.setValue(sourceOfSample);
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return (UnitOfMeasure) this.unitOfMeasure.getValue();
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure.setValue(unitOfMeasure);
	}

	public String getUnitOfMeasureName() {
		return unitOfMeasureName;
	}

	public void setUnitOfMeasureName(String unitOfMeasureName) {
		this.unitOfMeasureName = unitOfMeasureName;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getCollector() {
		return collector;
	}

	public void setCollector(String collector) {
		this.collector = collector;
	}

}