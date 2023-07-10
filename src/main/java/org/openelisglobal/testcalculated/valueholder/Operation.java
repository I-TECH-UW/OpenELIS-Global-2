package org.openelisglobal.testcalculated.valueholder;

import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "calculation_operation")
public class Operation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calculation_operation_generator")
    @SequenceGenerator(name = "calculation_operation_generator", sequenceName = "calculation_operation_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationType type;
    
    @Column(name = "value")
    private String value;;
    
    @Column(name = "sample_id")
    private Integer sampleId;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public OperationType getType() {
        return type;
    }
    
    public void setType(OperationType type) {
        this.type = type;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public Integer getSampleId() {
        return sampleId;
    }
    
    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }
    
    public enum OperationType {
        
        TEST_RESULT("Test Result"),
        MATH_FUNCTION("Math Function"),
        INTEGER("Integer"),
        PATIENT_ATTRIBUTE("Patient Attribute");
        
        private String displayName;
        
        private OperationType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return this.displayName;
        }
        
        public static Stream<OperationType> stream() {
            return Stream.of(OperationType.values());
        }
    }
}
