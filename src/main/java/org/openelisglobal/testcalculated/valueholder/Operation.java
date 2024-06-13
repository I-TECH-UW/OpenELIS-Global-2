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

    // mathematical operands
    public final static  String ADD = "+";
    public final static  String SUBTRACT = "-";
    public final static  String DIVIDE =  "/";
    public final static  String MULTIPLY =  "*";
    public final static  String OPEN_BRACKET =  "(";
    public final static  String CLOSE_BRACKET =  ")";
    public final static  String EQUALS =  "==";
    public final static  String NOT_EQUALS =  "!=";
    public final static  String GREATER_OR_EQUALS =  ">=";
    public final static  String LESS_OR_EQUALS =  "<=";
    public final static  String IN_NORMAL_RANGE =  "IS_IN_NORMAL_RANGE";
    public final static  String OUTSIDE_NORMAL_RANGE =  "IS_OUTSIDE_NORMAL_RANGE";
    public final static String LOGICAL_AND = "&&";
    public final static String LOGICAL_OR = "||";
    // constants
    public final static  String TEST_RESULT =  "TEST_RESULT";
    
    
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
      mathFunctions.add(new IdValuePair(ADD ,"Plus"));
      mathFunctions.add(new IdValuePair(SUBTRACT ,"Minus"));
      mathFunctions.add(new IdValuePair(DIVIDE ,"Divided By"));
      mathFunctions.add(new IdValuePair(MULTIPLY ,"Multiplied By"));
      mathFunctions.add(new IdValuePair(OPEN_BRACKET ,"Open Bracket"));
      mathFunctions.add(new IdValuePair(CLOSE_BRACKET ,"Close Bracket"));
      mathFunctions.add(new IdValuePair(EQUALS ,"Equals"));
      mathFunctions.add(new IdValuePair(NOT_EQUALS ,"Does Not Equal"));
      mathFunctions.add(new IdValuePair(GREATER_OR_EQUALS ,"Is Greater Than Or Equal"));
      mathFunctions.add(new IdValuePair(LESS_OR_EQUALS ,"Is Less Than Or Equal"));
      mathFunctions.add(new IdValuePair(IN_NORMAL_RANGE ,"Is With In Normal Range"));
      mathFunctions.add(new IdValuePair(OUTSIDE_NORMAL_RANGE ,"Is Out Side Normal Range"));
      mathFunctions.add(new IdValuePair(LOGICAL_AND ,"And"));
      mathFunctions.add(new IdValuePair(LOGICAL_OR ,"Or"));
      return mathFunctions;
    }

    @Override
	public int compareTo(Operation operation) {
		return this.getOrder().compareTo(operation.getOrder());
	}
}
