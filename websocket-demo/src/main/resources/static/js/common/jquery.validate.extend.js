$.extend($.validator.messages,{
	required: "这是必填字段",
	remote: "请修正此字段",
	email: "请输入有效的电子邮件地址",
	url: "请输入有效的网址",
	date: "请输入有效的日期",
	dateISO: "请输入有效的日期 (YYYY-MM-DD)",
	number: "请输入有效的数字",
	digits: "只能输入数字",
	creditcard: "请输入有效的信用卡号码",
	equalTo: "你的输入不相同",
	extension: "请输入有效的后缀",
	maxlength: $.validator.format("最多可以输入 {0} 个字符"),
	minlength: $.validator.format("最少要输入 {0} 个字符"),
	rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
	range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
	max: $.validator.format("请输入不大于 {0} 的数值"),
	min: $.validator.format("请输入不小于 {0} 的数值")
});
jQuery.validator.addMethod("phone", function(value, element) { 
    var length = value.length;  
    var regPhone = /^1([3578]\d|4[57])\d{8}$/;  
    return this.optional(element) || ( length == 11 && regPhone.test( value ) );    
}, "请正确填写您的手机号码"); 
jQuery.validator.addMethod("IDcard", function(value, element) { 
    var length = value.length;  
    var regIDcard = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;  
    return this.optional(element) || ( length == 18 && regIDcard.test( value ) );    
},"请正确填写您的身份证号码");
jQuery.validator.addMethod("Nickname", function(value, element) { 
    var length = value.length;  
    var regNickname = /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;  
    return this.optional(element) || (regNickname.test( value ) );    
},"请输入合法的昵称");
jQuery.validator.addMethod("hanzi", function(value, element) { 
	var regName = /[^\u4e00-\u9fa5]+$/;  
    return this.optional(element) ||  (!regName.test(value));    
}, "请使用中文"); 