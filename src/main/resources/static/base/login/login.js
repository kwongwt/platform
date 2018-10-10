define(function(require, exports, module) {
	var $ = require("jquery2");
	var md5 = require("md5");
	require("layui");
	
	// 登陆操作
	$("#login-btn").click(function(){
		login();
	})
	$('#username').keypress(function(event) {
		if (event.keyCode == "13") {
			login();
		}
	});
	$('#password').keypress(function(event) {
		if (event.keyCode == "13") {
			login();
		}
	});
	function login(){
		if($("#username").val().trim()==""){
			layer.tips('请输入账号', '#username');
			return false;
		}
		if($("#password").val().trim()==""){
			layer.tips('请输入密码', '#password');
			return false;
		}
		var data = {
			username:$("#username").val(),
			password:$.md5($("#password").val()),
			rememberMe:$(".layui-form-checkbox").hasClass("layui-form-checked")?true:false,
		}
		$.ajax({
			type : "POST",
			url : "/rest/login",                                                               
			dataType: "json",
			async : false,
			data:data,
			success:function(data){
				window.location.href="/index";
			},
			error: function (data) {
				layer.alert(data.responseJSON.message, {icon: 2})
            } 
		});
	}
})