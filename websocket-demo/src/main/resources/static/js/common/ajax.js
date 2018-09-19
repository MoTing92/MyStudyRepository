$.doajax = function(url,type,data,callback){
	$.ajax({
		url :url,
		type : type,
		contentType: "application/json",
		data :JSON.stringify(data),
		success :callback,
		error:function(){
			console.log("服务器异常！");
		}
	});
};
var curWwwPath = window.document.location.href;
var pathname= window.document.location.pathname;
var pos = curWwwPath.indexOf(pathname);
var localhostPath = curWwwPath .substring(0,pos);
//console.log(localhostPath);
$("#logout").attr("href",localhostPath+"/user/logout");
/*$("#logout").click(function(){
	window.open(localhostPath+"/channel/accountManage/logout");
})*/
$("#username").text(localStorage.userName);
//根据code查询省份
function provinceName(provincecode){
	var provinceName
	$.ajax({
		async : false,
		url : "/channel/channelManage/getProvince/"+provincecode,
		type : "GET",
		dataType : "json",
		success : function(data) {
			provinceName=data.returnObject.provinceName
//			console.log(provinceName);
		}
	});
	return provinceName;
	}
//根据code查询地市
	function areaName(areacode){
		var areaName;
		$.ajax({
			async : false,
			url : "/channel/channelManage/getCity/"+areacode,
			type : "GET",
			dataType : "json",
			success : function(data) {
//				console.log(data.returnObject[0].areaName);
				areaName=data.returnObject[0].areaName
			}
		});
		return areaName;
	}
	
	var websocket=null;
    if('WebSocket' in window){
        websocket=new WebSocket('ws://localhost:8081/webSocket');
    }else{
        alert('该浏览器不支持websocket');
    }
    websocket.onopen=function (ev) {
    	websocket.send("连接建立后，向服务器发送消息。");
        console.log('建立连接');
    }
    websocket.onclose=function (ev) {
        console.log('连接关闭');
    }
    websocket.onmessage=function (ev) {
        console.log('收到消息：'+ev.data);
        //弹窗提醒，播放消息
//        $('#loginOther').play();
        $('#notice').html(ev.data);
//        document.getElementById('notice').play();
    }
    window.onbeforeunload=function (ev) {
        websocket.close();
    }
