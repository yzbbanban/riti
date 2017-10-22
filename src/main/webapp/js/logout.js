$(function(){
	$("#log_out").click(function() {
//		alert("logout");
		$(location).attr('href', '/cool/login.html');
		delCookie("userId");
		delCookie("nick");
	});
});

