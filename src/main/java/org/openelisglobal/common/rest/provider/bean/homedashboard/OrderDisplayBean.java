package org.openelisglobal.common.rest.provider.bean.homedashboard;

public class OrderDisplayBean {
    
    private String priority;
    
    private String orderDate;
    
    private String patientId;
    
    private String labNumber;
    
    private String testName;
    
    public String getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
    public String getPatientId() {
        return patientId;
    }
    
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
    public String getLabNumber() {
        return labNumber;
    }
    
    public void setLabNumber(String labNumber) {
        this.labNumber = labNumber;
    }
    
    public String getTestName() {
        return testName;
    }
    
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
}
