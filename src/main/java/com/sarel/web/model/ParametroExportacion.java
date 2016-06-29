package com.sarel.web.model;

import java.util.Map;

public class ParametroExportacion {

	private Map<String, Object> parameters;

	public ParametroExportacion(Map<String, Object> parameters) { this.parameters = parameters; }

	public ParametroExportacion() { }

	public Map<String, Object> getParameters() { return parameters; }

	public void setParameters(Map<String, Object> parameters) { this.parameters = parameters; }
}
