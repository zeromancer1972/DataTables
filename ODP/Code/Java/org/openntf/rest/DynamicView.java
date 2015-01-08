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

	private String data;
	private ArrayList<String> cols;

	public DynamicView() {
		init();
	}

	private void init() {
		data = "{}";
		cols = new ArrayList<String>();
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			XSPContext context = XSPContext.getXSPContext(facesContext);
			String viewName = context.getUrl().getParameter("viewName");

			if (viewName == null || viewName.toString().equals(""))
				return;

			ArrayList<Object> collections = new ArrayList<Object>();
			JsonJavaObject obj = new JsonJavaObject();
			View view = ExtLibUtil.getCurrentDatabase().getView(viewName);
			if (view == null)
				return;

			@SuppressWarnings("unchecked")
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

	public synchronized String getData() {
		return data;
	}

	public synchronized ArrayList<String> getCols() {
		return cols;
	}

}
