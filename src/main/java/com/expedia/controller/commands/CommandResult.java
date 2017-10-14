package com.expedia.controller.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Objects of this class represents command results, to help the front controller
 * append attributes to the request object and fwd to the right resource
 * 
 * @author sasafi
 *
 */
public class CommandResult {

	private String path;
	
	private Map<String, Object> attributes = new HashMap();
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}
	
	public Map<String, Object> getAttributes() {
		return new HashMap(attributes);
	}

	public String getPath() {
		
		return path;
	}
	
	
	
	
}
