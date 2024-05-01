package org.openelisglobal.common.rest.bean;

import java.util.List;

import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.qaevent.valueholder.NcEvent;
import org.openelisglobal.qaevent.valueholder.NceCategory;
import org.openelisglobal.qaevent.valueholder.NceType;
import org.openelisglobal.sampleitem.valueholder.SampleItem;
import org.openelisglobal.systemuser.valueholder.SystemUser;


public class ViewNonConformEventsResponse {

      private List<NcEvent> results;
    private List<NceCategory> nceCategories;
    private List<NceType> nceTypes;
    private List<IdValuePair> labComponentList;
    private List<IdValuePair> severityConsequenceList;
    private List<IdValuePair> reportingUnits;
    private List<IdValuePair> severityRecurs;
    private Integer repoUnit;
    private SystemUser currentUserId;
    private List<SampleItem> specimen;
    private String reportDate;
    private String dateOfEvent;

    // Getters and setters for all fields

    public List<NcEvent> getResults() {
        return results;
    }

    public void setResults(List<NcEvent> results) {
        this.results = results;
    }

    public List<NceCategory> getNceCategories() {
        return nceCategories;
    }

    public void setNceCategories(List<NceCategory> nceCategories) {
        this.nceCategories = nceCategories;
    }

    public List<NceType> getNceTypes() {
        return nceTypes;
    }

    public void setNceTypes(List<NceType> nceTypes) {
        this.nceTypes = nceTypes;
    }

    public List<IdValuePair> getLabComponentList() {
        return labComponentList;
    }

    public void setLabComponentList(List<IdValuePair> labComponentList) {
        this.labComponentList = labComponentList;
    }

    public List<IdValuePair> getSeverityConsequenceList() {
        return severityConsequenceList;
    }

    public void setSeverityConsequenceList(List<IdValuePair> severityConsequenceList) {
        this.severityConsequenceList = severityConsequenceList;
    }

    public List<IdValuePair> getReportingUnits() {
        return reportingUnits;
    }

    public void setReportingUnits(List<IdValuePair> reportingUnits) {
        this.reportingUnits = reportingUnits;
    }

    public List<IdValuePair> getSeverityRecurs() {
        return severityRecurs;
    }

    public void setSeverityRecurs(List<IdValuePair> severityRecurs) {
        this.severityRecurs = severityRecurs;
    }

    public Integer getRepoUnit() {
        return repoUnit;
    }

    public void setRepoUnit(Integer repoUnit) {
        this.repoUnit = repoUnit;
    }

    public SystemUser getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(SystemUser currentUserId) {
        this.currentUserId = currentUserId;
    }

    public List<SampleItem> getSpecimen() {
        return specimen;
    }

    public void setSpecimen(List<SampleItem> specimen) {
        this.specimen = specimen;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(String dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }
    
}
