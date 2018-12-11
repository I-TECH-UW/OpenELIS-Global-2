package us.mn.state.health.lims.datasubmission.valueholder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.common.valueholder.BaseObject;

public class DataResource extends BaseObject {
	
	//switch to enum if hibernate is upgraded and enums become available
	/*public enum Level {
		ALL, COUNTY, LAB, NATIONAL, PARTNER, SITE, SUBCOUNTY;

		public static List<Level> getAllLevels() {
			return Arrays.asList(Level.values());
		}
		public static List<Level> getAllNamedLevels() {
			List<Level> levels = new ArrayList<Level>(Arrays.asList(Level.values()));
			levels.remove(ALL);
			return levels;
		}
	}*/

	public static String ALL = "all";
	public static String COUNTY = "county";
	public static String LAB = "lab";
	public static String NATIONAL = "national";
	public static String PARTNER = "partner";
	public static String SITE = "facility";
	public static String SUBCOUNTY = "subcounty";

	private String id;
	private String name;
	private String collectionName;
	private String headerKey;
	private String level;
	//id of entries on VLDASH database by level
	private Map<String,String> levelIdMap = new HashMap<String,String>();
	private List<DataValue> columnValues = new ArrayList<DataValue>();
	
	
	public static List<String> getAllLevels() {
		return new ArrayList<String>(Arrays.asList(ALL,COUNTY,LAB,NATIONAL,PARTNER,SITE,SUBCOUNTY));
	}
	public static List<String> getAllNamedLevels() {
		return new ArrayList<String>(Arrays.asList(COUNTY,LAB,NATIONAL,PARTNER,SITE,SUBCOUNTY));
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String resourceName) {
		this.name = resourceName;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String resourceCollectionName) {
		this.collectionName = resourceCollectionName;
	}
	public String getHeaderKey() {
		return headerKey;
	}
	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Map<String, String> getLevelIdMap() {
		return levelIdMap;
	}
	public void setLevelIdMap(Map<String, String> levelIdMap) {
		this.levelIdMap = levelIdMap;
	}
	public List<DataValue> getColumnValues() {
		return columnValues;
	}
	public void setColumnValues(List<DataValue> columnValues) {
		this.columnValues = columnValues;
	}
	
	public String getValue(int index) {
		return columnValues.get(index).getValue();
	}
	public void setValue(int index, String value) {
		DataValue valueWrapper = columnValues.get(index);
		valueWrapper.setValue(value);
		columnValues.set(index, valueWrapper);
	}
}
