$(function() {
	// alert("home");
	var companyId = getCookie("companyId");
	var userId = getCookie("userId");
	var level = getCookie("level");
	var nick = getCookie("nick");
	if (nick == "elocationCool") {// 温标账号
		$("#nfc_user").show();
		$('#company_logo').attr('src', 'img/elocation_logo.png');
		$('#company_logo').css("width", "200px");
		$('#company_logo').css("height", "40px");
		// alert(companyId);
		if (companyId != null) {
			$
					.ajax({
						url : "user/getUserList.do",
						type : "post",
						data : {
							"userId" : userId,
							"companyId" : companyId,
							"level" : level
						},
						dataType : "json",
						success : function(result) {
							// alert(result.message);

							if (result.code == "10000") {
								// alert(result.message);
								var ul = $("#sub_users");
								ul.html("");
								var sli = "";
								for (var i = 0; i < result.result.length; i++) {
									var uId = result.result[i].id;
									var userName = result.result[i].name;
									var userNickName = result.result[i].nick;
									if (userId == uId) {
										sli = '<li id="user_li"><a href="charts.html"><strong>'
												+ userNickName
												+ '</strong></a></li>';
									} else {
										sli = '<li id="user_li"><a href="charts.html">'
												+ userNickName + '</a></li>';
									}
									sli = $(sli);
									sli.data('userId', uId);
									sli.data('userName', userName);
									sli.data('userNickName', userNickName);
									ul.append(sli);
								}
							}
						},
						error : function() {
							alert("创建失败，请重试");
						}
					});
		}
	}else{
		$('#company_logo').attr('src', 'img/homelogo.png');
	}
});

$(function() {
	$("#sub_users").on("click", "#user_li", function() {
		var li = $(this);
		var userId = li.data('userId');
		addCookie("tagUserId", userId, 2);
	});

});
