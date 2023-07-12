package org.openelisglobal.testcalculated.valueholder;

import java.util.ArrayList;
import java.util.List;
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

import org.openelisglobal.common.util.IdValuePair;

@Entity
@Table(name = "calculation_operation")
public class Operation implements Comparable<Operation>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calculation_operation_generator")
    @SequenceGenerator(name = "calculation_operation_generator", sequenceName = "calculation_operation_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "operation_order")
    private Integer order;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationType type;
    
    @Column(name = "value")
    private String value;
    
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
    
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public enum PatientAttribute {
        
        AGE("Patient Age(Years)"),
        WEIGHT("Patient Weight(Kg)");
        
        private String displayName;
        
        private PatientAttribute(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return this.displayName;
        }
        
        public static Stream<PatientAttribute> stream() {
            return Stream.of(PatientAttribute.values());
        }
    }
     
    public static List<IdValuePair> mathFunctions(){
      List<IdValuePair>  mathFunctions = new ArrayList<>();
      mathFunctions.add(new IdValuePair("+" ,"Plus"));
      mathFunctions.add(new IdValuePair("-" ,"Minus"));
      mathFunctions.add(new IdValuePair("/" ,"Divided By"));
      mathFunctions.add(new IdValuePair("*" ,"Multiplied By"));
      mathFunctions.add(new IdValuePair("(" ,"Open Bracket"));
      mathFunctions.add(new IdValuePair(")" ,"Close Bracket"));
      mathFunctions.add(new IdValuePair("==" ,"Equals"));
      mathFunctions.add(new IdValuePair("!=" ,"Does Not Equal"));
      mathFunctions.add(new IdValuePair(">=" ,"Is Greater Than Or Equal"));
      mathFunctions.add(new IdValuePair("<=" ,"Is Less Than Or Equal"));
      mathFunctions.add(new IdValuePair("INSIDE_NORMAL_RANGE" ,"Is With In Normal Range"));
      mathFunctions.add(new IdValuePair("OUTSIDE_NORMAL_RANGE" ,"Is Out Side Normal Range"));
      return mathFunctions;
    }

    @Override
	public int compareTo(Operation operation) {
		return this.getOrder().compareTo(operation.getOrder());
	}
}
