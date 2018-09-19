//密码重置
$("#resetButton").click(function(){
	var username=$("#username").val();
	var email=$("#email").val();
	var mess={
			"username":username,
			"email":email
		};
	console.log(mess);
	$.doajax("/channel/accountManage/resetPassword","POST",mess,function(data){
		if(data.code==0000){
			console.log("重置密码成功,并返回如下信息");
			console.log(data);
			$("#successModal").modal('show');
		}else{
			console.log("重置密码失败");
			$("#myModal").modal('show');
		}
	});
})