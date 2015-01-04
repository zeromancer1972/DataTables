// DataTables init
$(document).ready( function() {
	var table = $("#datatable").dataTable( {
		stateSave : true,
		"language" : {
			"lengthMenu" : "Records per page _MENU_",
			"info" : "Page _PAGE_ of _PAGES_",
			"infoEmpty" : "No records available",
			"infoFiltered" : ""
		},
		scrollY : 400,
		"ajax" : "data.xsp",
		"columns" : [ {
			"data" : "lastname"
		}, {
			"data" : "firstname"
		}, {
			"data" : "address"
		}, {
			"data" : "zip"
		}, {
			"data" : "city"
		}, {
			"data" : "country"
		}, {
			"data" : "unid",
			"visible" : false
		} ]
	});

	table.on("dblclick", "tr", function() {
		var data = table.fnGetData(this);
		location.href = "fn.xsp?documentId=" + data.unid;
	});

})