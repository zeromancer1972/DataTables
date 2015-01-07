package org.openntf.rest;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.faces.context.FacesContext;

import lotus.domino.NotesException;
import lotus.domino.View;
import lotus.domino.ViewColumn;
import lotus.domino.ViewEntry;
import lotus.domino.ViewEntryCollection;

import com.ibm.commons.util.io.json.JsonException;
import com.ibm.commons.util.io.json.JsonGenerator;
import com.ibm.commons.util.io.json.JsonJavaFactory;
import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.xsp.designer.context.XSPContext;
import com.ibm.xsp.extlib.util.ExtLibUtil;

public class DynamicView implements Serializable {

	private static final long serialVersionUID = 3369811607367745396L;

	private String data = "{}";
	private ArrayList<String> cols;

	public DynamicView() {
		init();
	}

	private void init() {
		try {
			cols = new ArrayList<String>();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			XSPContext context = XSPContext.getXSPContext(facesContext);
			String viewName = context.getUrl().getParameter("viewName");

			if (viewName == null || viewName.toString().equals(""))
				return;

			ArrayList<Object> collections = new ArrayList<Object>();
			JsonJavaObject obj = new JsonJavaObject();

			View view = ExtLibUtil.getCurrentDatabase().getView(viewName);
			@SuppressWarnings("unchecked")
			Vector columns = view.getColumns();
			ViewEntryCollection col = view.getAllEntries();
			ViewEntry ent = col.getFirstEntry();
			ViewEntry tmp = null;
			int count = 0;
			while (ent != null) {
				tmp = col.getNextEntry(ent);
				count = 0;
				// nur Dokumente
				if (ent.isDocument()) {
					// Alle Spalten durchlaufen
					HashMap<String, Object> collection = new HashMap<String, Object>();
					for (Object column : columns) {
						ViewColumn co = (ViewColumn) column;
						if (!co.isHidden()) {
							// Spalte ist sichtbar, also Wert in Hash aufnehmen
							cols.add(co.getTitle());
							collection.put(Integer.valueOf(count).toString(), ent.getColumnValues().elementAt(count).toString());
							count++;
						}
					}
					collections.add(collection);
				}

				ent.recycle();
				ent = tmp;
			}

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

	public synchronized String getData() {
		return data;
	}

	public synchronized ArrayList<String> getCols() {
		return cols;
	}

}
