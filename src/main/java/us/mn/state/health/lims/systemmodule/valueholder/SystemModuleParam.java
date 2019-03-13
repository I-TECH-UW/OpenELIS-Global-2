package us.mn.state.health.lims.systemmodule.valueholder;

import java.util.HashSet;
import java.util.Set;

import us.mn.state.health.lims.common.valueholder.BaseObject;

public class SystemModuleParam extends BaseObject {

	private String id;
	private String name;
	private String value;
	private Set<SystemModuleUrl> urls = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set<SystemModuleUrl> getUrls() {
		return urls;
	}

	public void setUrls(Set<SystemModuleUrl> urls) {
		this.urls = urls;
	}
}
