package org.openntf.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import lotus.domino.NotesException;
import lotus.domino.View;
import lotus.domino.ViewColumn;
import lotus.domino.ViewEntry;
import lotus.domino.ViewEntryCollection;

import com.ibm.commons.util.io.json.JsonException;
import com.ibm.commons.util.io.json.JsonGenerator;
import com.ibm.commons.util.io.json.JsonJavaFactory;
import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.xsp.extlib.util.ExtLibUtil;

public class DynamicView {

	private String data;
	private String id;
	private ArrayList<String> cols;
	private final String viewName;

	public DynamicView(final String v) {
		viewName = v;
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		data = "{}";
		cols = new ArrayList<String>();
		try {
			id = ExtLibUtil.getCurrentSession().evaluate("@Unique").elementAt(0).toString();
			if (viewName == null || viewName.toString().equals("")) {
				System.out.println("no view name specified");
				return;
			}

			ArrayList<Object> collections = new ArrayList<Object>();
			JsonJavaObject obj = new JsonJavaObject();
			View view = ExtLibUtil.getCurrentDatabase().getView(viewName);
			if (view == null) {
				System.out.println("view not found");
				return;
			}

			Vector columns = view.getColumns();
			for (Object column : columns) {
				ViewColumn co = (ViewColumn) column;
				if (!co.isHidden()) {
					cols.add(co.getTitle());
				}
			}
			ViewEntryCollection col = view.getAllEntries();
			ViewEntry ent = col.getFirstEntry();
			ViewEntry tmp = null;
			int count;

			while (ent != null) {
				tmp = col.getNextEntry(ent);
				count = 0;
				// documents only
				if (ent.isDocument()) {
					// add all view columns
					HashMap<String, Object> collection = new HashMap<String, Object>();
					for (Object column : columns) {

						ViewColumn co = (ViewColumn) column;
						if (!co.isHidden()) {
							// visible column added to collection
							collection.put(Integer.valueOf(count).toString(), ent.getColumnValues().elementAt(count).toString());
							count++;
						}
					}

					// last column always contains the UNID to open the document
					collection.put("unid", ent.getDocument().getUniversalID());
					collections.add(collection);
				}

				ent.recycle();
				ent = tmp;
			}

			col.recycle();
			view.recycle();

			obj.put("data", collections);
			try {
				data = JsonGenerator.toJson(JsonJavaFactory.instanceEx, obj);
			} catch (JsonException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (NotesException e) {
			e.printStackTrace();
		}
	}

	public String getData() {
		return data;
	}

	public ArrayList<String> getCols() {
		return cols;
	}

	public String getId() {
		return "dataTable-" + id;
	}
}
