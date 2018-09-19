$(function(){
	
//根据是否保存用户名来展示手机号的输入框状态
	if(localStorage.username==""){
		$("#username").val("");
	}else{
		$("#username").val(localStorage.username);
	}
//登录
$("#loginButton").click(function(){
	var username=$("#username").val();
	var password=$("#password").val();
	var mess={
			"username":username,
			"password":password
		};
	console.log(mess);
	$.doajax("/user/login","POST",mess,function(data){
		if(data.code==0000){
			console.log("登录成功,并返回如下信息");
			console.log(data.returnObject);
			if($("#rememberCheck").is(':checked')){
				window.localStorage.setItem("username", username);
			}else{
				window.localStorage.setItem("username","");
	         }
			var role=data.returnObject.role;
			 localStorage.setItem('userName', data.returnObject.userName);
			 localStorage.setItem('ownChannelName', data.returnObject.ownChannelName);
			 localStorage.setItem('ownChannel', data.returnObject.ownChannel);
			 localStorage.setItem('role', data.returnObject.role);
			 window.location.href="../channelManagement/resource_allocation.html";
		}else{
			console.log("登录失败");
			console.log(data);
			$("#myModal").modal('show');
			$("#message").text(data.message);
		}
	});
});

//重置按钮
$("#resetButton").click(function(){
	$("#username").val("");
	$("#password").val("");
})

})