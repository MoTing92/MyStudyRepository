$.fn.extend({        
		distpicker:function(prov,area){
//			console.log($("#"+prov));
			// 加载省的数据
			LoadSheng();
			// 加载市的数据
			LoadShi();
			// 加载区的数据
			// LoadQu();
			// 给省的下拉列表添加点击事件，当省变化时，对应的市和区会发生变化
			$("#"+prov).click(function() {
				LoadShi();
				// LoadQu();
			})
	        // 给市的下拉列表添加点击事件，当市变化时，对应的区发生变化
			
//			  $("#area").click(function(){ LoadQu(); })
			
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
//							console.log(data);
							var str = "<option value =''>全部</option>";
							for (var i = 0; i < data.returnObject.length; i++) {
								var lie = data.returnObject[i];
								str = str + "<option value='" + lie.provinceId + "' date-name='"+lie.provinceName+"'>"
										+ lie.provinceName + "</option>";
							}
							$("#"+prov).html(str);
						}
						});
					}
				// 加载市的下拉列表
				function LoadShi() {
				var provinceId = $("#"+prov +" option:selected").val();
				if(provinceId==""){
					var str = "";
					str = str + "<option value =''>全部</option>";
					$("#"+area).html(str);
				}else{
//				console.log($("#"+prov +" option:selected").val())
				$.ajax({
					async : false,
					url : "/channel/channelManage/getCities/"+provinceId,
					type : "GET",
					dataType : "json",
					success : function(data) {
//						console.log(data);
						var str = "";
						for (var i = 0; i < data.returnObject.length; i++) {
							var lie = data.returnObject[i];
							str = str + "<option value = '" + lie.areaId + "' date-name='"+lie.areaName +"' date-pid='"+lie.provinceId +"'>"
									+ lie.areaName + "</option>";
						}
						$("#"+area).html(str);
					}
				});
				}
			} 
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

	      }       
});   
	


