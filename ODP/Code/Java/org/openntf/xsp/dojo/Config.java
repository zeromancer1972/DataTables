package org.openntf.xsp.dojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.ibm.xsp.context.DojoLibrary;

public class Config implements Serializable {

	private static final long serialVersionUID = -5429830701050906773L;
	private final Map<String, String> config;
	private final boolean fp2;

	public Config() {
		this.config = new HashMap<String, String>();
		DojoLibrary dojolib = com.ibm.xsp.context.DojoLibraryFactory.getDefaultLibrary();
		this.fp2 = dojolib.getVersion().toString().indexOf("1.9") != -1;
		
		// get the current version of Dojo
		config.put("version", dojolib.getVersion().toString());
		// get the keyword for setting toolbar options
		config.put("toolbartype", this.isFp2() ? "toolbar" : "toolbarType");
		// to be extended...
	}

	public Map<String, String> getConfig() {
		return config;
	}

	public boolean isFp2() {
		return fp2;
	}
	
}
