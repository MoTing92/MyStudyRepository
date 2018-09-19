$(function(){
//跳转页事件
	$("#nowPage").keyup(function(e){
//		var num = Number($("#page").val());
		var maxpage = $("#pageCount").html();
		if (!e) var e = window.event;
		if(e.keyCode==13){
			if (!(Math.ceil(num) == num) || !num || num > maxpage || num <= 0) {
				customPromptMsg('请输入合适的整数!');      	
				return;
			}else{
				queryList(num,pageSize);
			}
		}
	});

	//首页
	$("#firstPage").click(function(){
		deal(1,10);
		console.log("shouye");
	});
	//上一页
	$("#prePage").click(function(){
		var nowpage = parseInt($("#nowPage").val())-1;
		if(nowpage>0){
			deal(nowpage,pageSize);
		}
		if(nowpage == 0){
			customPromptMsg("已经是第一页了！")
		}
	});
	//下一页
	$("#nextPage").click(function(){
		var nowpage = $("#nowPage").val();
		var maxpage = $("#pageCount").html();
		if(nowpage==maxpage){
			customPromptMsg("已经是最后一页了！")
		}else{
			deal(nowpage,pageSize);
		}
	});
	//尾页
	$("#lastPage").click(function(){
		var maxpage = $("#pageCount").html();
		deal(maxpage,pageSize);
	});

	//每页显示的条数
	/*$("#pagesel").on('click','.pageul > li',function(){
		var num = $(this).text();
		$("#pageSize").html("每页"+num+"条");
		queryList(1,num);
	});
*/
})
//待我处理查询方法
function deal(nowPage,pageSize){
	$(".pending_table").html("");
	var ordermess={
			"pageNum":nowPage,
			"pageSize":pageSize
	}
	$.doajax("/channel/channelManage/waitMeDealOrder","POST",ordermess,function(data){
		if(data.code==0000){
			console.log("待我处理查询成功");
			console.log(data);
			var str="";
			str+="<table>"+
      		"<tr><td>订单编号</td><td>渠商道编号</td><td>申请时间</td><td>归属号段</td><td>归属省份</td><td>归属地市</td><td>申请SIM卡数量</td><td>申请号码数量</td><td>当前状态</td><td>当前处理人</td><td>操作</td></tr>";
			for(var i=0;i<data.returnObject.list.length;i++){
				str+=" <tr><td class='order_number'>"+data.returnObject.list[i].orderId+"</td><td>"+data.returnObject.list[i].iniChannel+"</td><td>"+data.returnObject.list[i].createDate+"</td><td>"+data.returnObject.list[i].hlr+"</td><td>"+provinceName(data.returnObject.list[i].provCode)+"</td><td>"+areaName(data.returnObject.list[i].areaCode)+"</td><td>"+data.returnObject.list[i].simQuantity+"</td><td>"+data.returnObject.list[i].numberQuantity+"</td><td>"+operationState(data.returnObject.list[i].state)+"</td><td>"+data.returnObject.list[i].processingChannel+"</td><td><span onclick='"+functionState(data.returnObject.list[i].state)+"'>"+operationState(data.returnObject.list[i].state)+"</span></td></tr>";	
			}
            str+="</table>";
			$(".pending_table").append(str);
		}else{
			console.log("待我处理查询失败");
			console.log(data.message);
		}
	});
}
