package us.mn.state.health.lims.observationhistorytype.valueholder;

import java.sql.Timestamp;

import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.common.valueholder.SimpleBaseEntity;

/**
 * DemographicHistoryType entity.
 * @author MyEclipse Persistence Tools
 * @author pahill 
 * @since 2010-04-09
 */

public class ObservationHistoryType extends	BaseObject implements SimpleBaseEntity<String> {
	private static final long serialVersionUID = 1L;
	
	// Fields

	private String id;
	private String typeName;
	private String description;

	// Constructors

	public ObservationHistoryType() {
	}

	/** minimal constructor */
	public ObservationHistoryType(String id, String typeName, String description) {
		this.setId(id);
		this.typeName = typeName;
		this.description = description;
	}

	/** full constructor */
	public ObservationHistoryType(String id, String typeName, String description,
			Timestamp lastupdated) {
		this.setId(id);
		this.typeName = typeName;
		this.description = description;
		this.setLastupdated(lastupdated);
	}

	// Property accessors
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}