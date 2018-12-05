/**
 * 
 */
package us.mn.state.health.lims.patient.valueholder;

import java.io.Serializable;

public class AdverseEffect implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String grade;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}