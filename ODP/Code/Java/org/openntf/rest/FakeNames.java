package org.openntf.rest;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.openntf.domino.Document;
import org.openntf.domino.DocumentCollection;
import org.openntf.domino.utils.XSPUtil;

import com.ibm.commons.util.io.json.JsonException;
import com.ibm.commons.util.io.json.JsonGenerator;
import com.ibm.commons.util.io.json.JsonJavaFactory;
import com.ibm.commons.util.io.json.JsonJavaObject;

public class FakeNames implements Serializable {

	private static final long serialVersionUID = 1L;
	private String data;

	public FakeNames() {
		init();
	}

	private void init() {
		ArrayList<Object> collections = new ArrayList<Object>();
		JsonJavaObject obj = new JsonJavaObject();
		DocumentCollection names = XSPUtil.getCurrentDatabase().getView("ByName").getAllDocuments();
		for (Document doc : names) {
			HashMap<String, Object> collection = new HashMap<String, Object>();
			collection.put("unid", doc.getUniversalID());
			collection.put("lastname", doc.getItemValueString("lastname"));
			collection.put("firstname", doc.getItemValueString("firstname"));
			collection.put("address", doc.getItemValueString("address"));
			collection.put("zip", getZip(doc.getItemValue("zip")));
			collection.put("city", doc.getItemValueString("city"));
			collection.put("country", doc.getItemValueString("country"));

			collections.add(collection);
		}

		obj.put("data", collections);
		try {
			data = JsonGenerator.toJson(JsonJavaFactory.instanceEx, obj);
		} catch (JsonException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private String getZip(final Vector v) {
		if (v.elementAt(0).getClass() == String.class) {
			return v.elementAt(0).toString();
		} else {
			return v.elementAt(0).toString().replace(".0", "");
		}

	}

	public String getData() {
		return data;
	}

}
