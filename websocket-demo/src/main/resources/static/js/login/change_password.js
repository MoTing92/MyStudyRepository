//修改密码

	// 输入验证
	$("#change_form").validate({
		rules: {
			"username": {
				required: true
			},
			"oldpassword": {
				required: true,
				maxlength: 6,
				minlength: 6
			},
			"password": {
				required: true,
				maxlength: 6,
				minlength: 6
			},
			"checkPassword": {
				required: true,
				equalTo: "#newpassword"
			},
		},
		messages: {
			"username": {
				required: "用户名不能为空",
			},
			"oldpassword": {
				required: "旧密码不能为空",
				rangelength: "密码必须是6位数字"
			},
			"password": {
				required: "新密码不能为空",
				rangelength: "密码必须是6位数字"
			},
			"checkPassword": {
				required: "请再次确认密码",
				equalTo: "两次输入密码不一致"
			},
		},
		debug: true,
		errorPlacement: function (error, element) {
			error.appendTo(element.parent());
		}
	});

$("#changeButton").click(function(){
	if ($("#change_form").valid()) {// 触发表单验证
	var username=$("#username").val();
	var oldpassword=$("#oldpassword").val();
	var newpassword=$("#newpassword").val();
	var mess={
			"username":username,
			"newpassword":newpassword,
			"oldpassword":oldpassword
		};
	console.log(mess);
	$.doajax("/channel/accountManage/modifiedPassword","put",mess,function(data){
		if(data.code==0000){
			console.log("修改密码成功,并返回如下信息");
			console.log(data);
			$("#successModal").modal('show');
		}else{
			console.log("修改密码失败");
			$("#myModal").modal('show');
			$("#fail_mess").text(data.message);
		}
	});
	}else{
		return false;
	}
})