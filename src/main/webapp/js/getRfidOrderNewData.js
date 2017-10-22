$(function() {
	addCookie("isRfidNewPageLoading", 0, 2);
	// 查询客户入库吨桶
	$("#getNewOrderDataWithUser").click(function() {
		var rfidNewSelect_uid = $("#rfidNewSelect_uid").val();
		addCookie("rfidNewSelect_uid", rfidNewSelect_uid, 2);
		addCookie("searchType", "3", 2);// 区别其他类型，此为查询搜索类型
		getRfidNewData(0, 10);

	});
	// 查询在途吨桶
	$("#waySum").click(function() {
		addCookie("searchType", "1", 2);
		getRfidNewData(0, 10);
	});
	// 查询客户吨桶
	$("#userOrderSum").click(function() {
		addCookie("searchType", "4", 2);
		getRfidNewData(0, 10);
	});
	// 查询总仓（长兴）吨桶
	$("#orderSum").click(function() {
		addCookie("searchType", "6", 2);
		getRfidNewData(0, 10);
	});
	// 查询（长兴）在库吨桶
	$("#stockSum").click(function() {
		addCookie("searchType", "5", 2);
		getRfidNewData(0, 10);
	});

});

function getRfidOrderNewData() {
	// console.log("getRfidOrderNewData");
	// 获取所有吨桶数量
	getAllRfidOrderCount();
	// 显示各个用户 的资产数量
	getRfidUserCount();
}

/**
 * 获取所有吨桶数量
 */
function getAllRfidOrderCount() {
	$.ajax({
		url : "rfidOrder/getRfidOrderCount.do",
		type : "post",
		data : {
			"uid" : '',
			"startTime" : "1991-05-16 00:00:00",
			"endTime" : "2080-11-18 00:00:00",
			"startPage" : 0,
			"endPage" : 0,
			"stockType" : 6,
			"type" : 3
		// 全部订单
		},
		dataType : "json",
		success : function(result) {
			// alert("result: " + result);
			if (result.code = "10000") {
				$("#orderSum").val(result.result.orderSum);
				$("#waySum").val(result.result.waySum);
				$("#stockSum").val(result.result.stockSum);
				$("#userOrderSum").val(result.result.userOrderSum);
			} else {
				alert(result.message);
			}

		},
		error : function() {
			alert("此用户无RFID数据");
		}
	});
}

function getRfidUserCount() {

	var userId = getCookie("userId");
	if (userId != null) {
		$.ajax({
			url : "rfidUser/getRfidUserList.do",
			type : "post",
			data : {
				"id" : userId
			},
			dataType : "json",
			success : function(result) {
				if (result.code == 10000) {
					// alert(result.result[0]);
					var rfidNewSelectUid = $("#rfidNewSelect_uid");
					rfidNewSelectUid.html("");
					var ss = "";
					for (var i = 0; i < result.result.length; i++) {
						var rfidObj = result.result[i];
						if (rfidObj.rfidUserName != "长兴化工") {// 不能为长兴化工
							// console.log(rfidObj.rfidUserName);
							ss = "<option value='" + rfidObj.rfidUserId + "'>"
									+ rfidObj.rfidUserName + '&nbsp&nbsp('
									+ rfidObj.rfidOrderCount + ')'
									+ "</option>";
							var $ss = $(ss);
							rfidNewSelectUid.append($ss);
						}
					}

				} else {
					alert(result.message);
				}
			},
			error : function() {
				alert("此用户无RFID数据");
			}
		});
	} else {
		alert("用户信息加载失败，请重新登录");
		$(location).attr('href', '/cool/login.html');
	}
}

function getRfidNewData(startPage, endPage) {
	var rfidNewSelect_uid = getCookie("rfidNewSelect_uid");
	var searchType = getCookie("searchType");
	$.ajax({
		url : "rfidOrder/getRfidOrderList.do",
		type : "post",
		data : {
			"uid" : rfidNewSelect_uid,
			"startTime" : "1991-05-16 00:00:00",
			"endTime" : "2080-11-18 00:00:00",
			"startPage" : startPage,
			"endPage" : endPage,
			"stockType" : searchType, // 在库,
			"type" : 3
		// 查询类型
		// 在仓库
		},
		dataType : "json",
		success : function(result) {
			var rfidNewTb = $("#rfidNewTb");
			if (result.code == 10000) {
				getParseRfidNewData(result.result, startPage, endPage,
						rfidNewTb);
				if (getCookie("isRfidNewPageLoading") == 0
						|| getCookie("isRfidNewUid") != rfidNewSelect_uid
						|| getCookie("isRfidNewStockType")!=searchType
						) {
					setRfidNewOrderPaging(result.result.count);
					addCookie("isRfidNewUid", rfidNewSelect_uid, 2);
					addCookie("isRfidNewPageLoading", 1, 2);
					addCookie("isRfidNewStockType", searchType, 2);
				}

			} else {
				rfidNewTb.html("");
				alert(result.message);
			}
		},
		error : function() {
			setRfidNewOrderPaging(0);
			alert("此用户无RFID数据");
		}
	});
}
function getParseRfidNewData(result, startPage, endPage, rfidNewTb) {
	rfidNewTb.html("");
	var so = "";
	// console.log(result.rfidOrders);
	for (var i = 0; i < result.rfidOrders.length; i++) {
		var rfidOrder = result.rfidOrders[i];
		var idName = rfidOrder.idName;
		var rfidOrderCreateTime = rfidOrder.rfidOrderCreateTime;
		var nick = result.user.nick;
		var ti = getNewTimer(rfidOrderCreateTime);
		// <td>序号</td>
		// <td>吨桶ID号</td>
		// <td>操作员</td>
		// <td>时间</td>
		var stayTime = setStayTime(rfidOrderCreateTime);

		so = '<tr>' + '<td>' + (i + 1) + '</td>' + '<td>' + idName + '</td>'
				+ '<td>' + nick + '</td>' + '<td>' + ti + '</td>' + +'</tr>';

		var $so = $(so);
		rfidNewTb.append($so);
	}
}

/**
 * 格式化时间
 * 
 * @param rfidOrderCreateTime
 */
function getNewTimer(rfidOrderCreateTime) {
	var d = new Date(rfidOrderCreateTime);
	return d.getFullYear() + "年" + (d.getMonth() + 1) + "月" + d.getDate() + "日"
			+ d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
}