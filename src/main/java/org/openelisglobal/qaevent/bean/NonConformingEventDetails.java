package org.openelisglobal.qaevent.bean;

import java.util.List;

import org.openelisglobal.common.util.IdValuePair;
import org.openelisglobal.qaevent.valueholder.NceCategory;
import org.openelisglobal.sampleitem.valueholder.SampleItem;

public class NonConformingEventDetails  {

      private String labOrderNumber;
    private String specimenId;
    private String currentUserId;
    private List<NceCategory> categories;
    private String name;
    private String nceNumber;
    private String id;
    private List<SampleItem> specimens;
    private List<IdValuePair> reportUnits;
    private String site;
    private String prescriberName;
    private List<NceCategory> nceCategories;
    private String reportDate;

    // Getters and setters for all properties

    public String getLabOrderNumber() {
        return labOrderNumber;
    }

    public void setLabOrderNumber(String labOrderNumber) {
        this.labOrderNumber = labOrderNumber;
    }

    public String getSpecimenId() {
        return specimenId;
    }

    public void setSpecimenId(String specimenId) {
        this.specimenId = specimenId;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public List<NceCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<NceCategory> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNceNumber() {
        return nceNumber;
    }

    public void setNceNumber(String nceNumber) {
        this.nceNumber = nceNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SampleItem> getSpecimens() {
        return specimens;
    }

    public void setSpecimens(List<SampleItem> specimens) {
        this.specimens = specimens;
    }

    public List<IdValuePair> getReportUnits() {
        return reportUnits;
    }

    public void setReportUnits(List<IdValuePair> reportUnits) {
        this.reportUnits = reportUnits;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPrescriberName() {
        return prescriberName;
    }

    public void setPrescriberName(String prescriberName) {
        this.prescriberName = prescriberName;
    }

    public List<NceCategory> getNceCategories() {
        return nceCategories;
    }

    public void setNceCategories(List<NceCategory> nceCategories) {
        this.nceCategories = nceCategories;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    
}
