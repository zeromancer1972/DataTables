package org.openntf.bstemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openntf.domino.Document;
import org.openntf.domino.DocumentCollection;
import org.openntf.domino.utils.XSPUtil;

public class AppConfig implements Serializable {

	private static final long serialVersionUID = 1815182743940537568L;

	private Map<String, ArrayList<String>> valueLists = null;

	public AppConfig() {
		init();
	}

	private void init() {
		valueLists = new HashMap<String, ArrayList<String>>();
		DocumentCollection col = XSPUtil.getCurrentDatabase().getView("valuelists").getAllDocuments();
		for (Document doc : col) {
			ArrayList<String> list = new ArrayList<String>();
			list = doc.getItemValue("values", ArrayList.class);
			valueLists.put(doc.getItemValueString("key"), list);
		}
	}

	public void reload() {
		init();
	}

	public ArrayList<String> getList(final String key) {
		return valueLists.get(key);
	}

	public ArrayList<String> getSortedList(final String key) {
		ArrayList<String> list = valueLists.get(key);
		if (list != null) {
			Collections.sort(list);
		}
		return list;
	}

	public String getAppTitle() {
		try {
			return valueLists.get("appTitle").get(0);
		} catch (Exception e) {
			return XSPUtil.getCurrentDatabase().getTitle();
		}
	}
	public String getAppIcon() {
		try {
			return valueLists.get("appIcon").get(0);
		} catch (Exception e) {
			return "fa fa-coffee";
		}
	}

	public String getString(final String key) {
		try {
			return valueLists.get(key).get(0);
		} catch (Exception e) {
			return null;
		}

	}

}
