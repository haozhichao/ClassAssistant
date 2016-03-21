define([ 'toastr'], function (toastr) {
	var pwdmin = 6; // 密码最小长度
	// 初始化页面
	function init() {
		initPage();
	}

	// 初始化页面事件
	function initPage() {
		$("#u").focus();
		$('#switch_qlogin').click(function(){
			$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
			$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
			$('#switch_bottom').animate({left:'0px',width:'70px'});
			$('#qlogin').css('display','none');
			$('#web_qr_login').css('display','block');

		});
		$('#switch_login').click(function(){

			$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
			$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
			$('#switch_bottom').animate({left:'154px',width:'70px'});

			$('#qlogin').css('display','block');
			$('#web_qr_login').css('display','none');
		});
		if(getParam("a")=='0')
		{
			$('#switch_login').trigger('click');
		}
		$('#reg').click(function() {

			if ($('#user').val() == "") {
				$('#user').focus().css({
					border: "1px solid red",
					boxShadow: "0 0 2px red"
				});
				$('#userCue').html("<font color='red'><b>×用户名不能为空</b></font>");
				return false;
			}



			if ($('#user').val().length < 2 || $('#user').val().length > 16) {

				$('#user').focus().css({
					border: "1px solid red",
					boxShadow: "0 0 2px red"
				});
				$('#userCue').html("<font color='red'><b>×用户名位4-16字符</b></font>");
				return false;

			}
			if ($('#passwd').val().length < pwdmin) {
				$('#passwd').focus();
				$('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
				return false;
			}
			if ($('#passwd2').val() != $('#passwd').val()) {
				$('#passwd2').focus();
				$('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
				return false;
			}
			var url;
			var tpye1 = $('input:radio[name="type1"]:checked').val();
			if(tpye1 == 0){
				url = "/student/add";
			}else{
				url = "/teacher/add";
			}

			$.ajax({
				type: "POST",
				url: url,
				data: {username:$("#user").val(),password:$('#passwd').val()},
				dataType: 'JSON',
				success: function(result) {

					if (result == -1) {
						$('#user').focus().css({
							border: "1px solid red",
							boxShadow: "0 0 2px red"
						});$("#userCue").html("<span style='color: red'>用户名存在</span>");
						return false;
					} else {
						$('#user').css({
							border: "1px solid #D7D7D7",
							boxShadow: "none"
						});

						window.location.reload();//刷新当前页面.
					}
				}
			});
		});
		$('#login').click(function (){
			var url;
			var tpye1 = $('input:radio[name="type"]:checked').val();
			if(tpye1 == 0){
				url = "/student/login";
			}else{
				url = "/teacher/login";
			}
			var username = $('#u').val();
			var password = $('#p').val();
			$.ajax({
				type: "POST",
				url: url,
				data: {username:username,password:password},
				dataType: 'JSON',
				success:function(data){
					if(data){
						window.location.href = "../classroom/classroomList.html";
					}else{
						toastr.error("登录失败");
					}
				}
			});
		});
	}

	//根据参数名获得该参数 pname等于想要的参数名
	function getParam(pname) {
		var params = location.search.substr(1); // 获取参数 平且去掉？
		var ArrParam = params.split('&');
		if (ArrParam.length == 1) {
			//只有一个参数的情况
			return params.split('=')[1];
		}
		else {
			//多个参数参数的情况
			for (var i = 0; i < ArrParam.length; i++) {
				if (ArrParam[i].split('=')[0] == pname) {
					return ArrParam[i].split('=')[1];
				}
			}
		}
	}

	// =================
	var login = {};
	login.init = function () {
		init();
	};
	return login;
});
