package com.expedia.data;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * Data Transfer Object that is being used to transfer http parameters and attributes
 * between controller and the command layer.
 * 
 * @author sasafi
 *
 */
public class DataTransfer {
	
	private Map<String, Object> attributes;
	private Map<String, String> parameters;
	
	public DataTransfer(HttpServletRequest request) {
		
		attributes = new HashMap();
		parameters = new HashMap();
		
		Enumeration<String> enums = request.getAttributeNames();
		while (enums.hasMoreElements()) {
			String attributeName = enums.nextElement();
			attributes.put(attributeName, request.getAttribute(attributeName));
		}
		
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = params.nextElement();
			parameters.put(paramName, request.getParameter(paramName));
		}
		
		
	}
	
	public Object getAttribute(String attributeName) {
		return attributes.get(attributeName);
	}
	
	public String getParameter(String parameterName) {
		return parameters.get(parameterName);
	}
	
	public Set<String> getParamNames() {
		return parameters.keySet();
	}
	
}
