$(document).ready(function(e) {
	// 扔三个下拉列表到主页面建的div中
	$("#sanji").html("<label>归属省份</label><select id='sheng' class='sheng'  name='provinceName'></select><label>归属地市</label><select id='shi' class='shi' name='cityName'></select>");
	// 加载省的数据
	LoadSheng();
	// 加载市的数据
	LoadShi();
	// 加载区的数据
	// LoadQu();
	// 给省的下拉列表添加点击事件，当省变化时，对应的市和区会发生变化
	$("#sheng").click(function() {
		LoadShi();
		// LoadQu();
	})
	// 给市的下拉列表添加点击事件，当市变化时，对应的区发生变化
	/*
	 * $("#shi").click(function(){ LoadQu(); })
	 */
	//console.log($("#sheng").find("option:selected").val());
	
});


// 加载省的下拉列表
function LoadSheng() {
//	var pcode = "100";
	$.ajax({
		async : false,
		url : "/channel/channelManage/getProvinces",
		/*data : {
			code : pcode
		},*/
		type : "GET",
		dataType : "json",
		success : function(data) {
//			console.log(data);
			var str = "<option value =''>全部</option>";
			for (var i = 0; i < data.returnObject.length; i++) {
				var lie = data.returnObject[i];
				str = str + "<option value='" + lie.provinceId + "' date-name='"+lie.provinceName+"'>"
						+ lie.provinceName + "</option>";
			}
			$("#sheng").html(str);
		}
	});
}

// 加载市的下拉列表
function LoadShi() {
	var provinceId = $("#sheng option:selected").val();
	if(provinceId==""){
		var str = "";
		str = str + "<option value =''>全部</option>";
		$("#shi").html(str);
	}else{
//console.log(provinceId);
	$.ajax({
		async : false,
		url : "/channel/channelManage/getCities/"+provinceId,
		type : "GET",
		dataType : "json",
		success : function(data) {
//			console.log(data);
			var str = "";
			for (var i = 0; i < data.returnObject.length; i++) {
				var lie = data.returnObject[i];
				str = str + "<option value = '" + lie.areaId + "' date-name='"+lie.areaName +"' date-pid='"+lie.provinceId +"'>"
						+ lie.areaName + "</option>";
			}
			$("#shi").html(str);
		}
	});
	}
}



//console.log($("#sheng").find("option:selected").val());

// 加载区的下拉列表
// function LoadQu() {
// var pcode = $("#shi").val();
// $.ajax({
// url: "",
// data: { code: pcode },
// type: "POST",
// dataType: "TEXT",
// success: function(data) {
// var hang = data.trim().split("|");
// var str = "";
// for(var i = 0; i < hang.length; i++) {
// var lie = hang[i].split("^");
// str = str + "<option value = '" + lie[0] + "'>" + lie[1] + "</option>";
// }
// $("#qu").html(str);
// }
// });
// }
