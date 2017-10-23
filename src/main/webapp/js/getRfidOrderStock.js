function getRfidOrderStock() {
	addCookie("isRfidPageLoading", 0, 2);

	$("#searchRfidStockData").click(function() {
		var rfidStockSelect_uid = $("#rfidStockSelect_uid").val();
		addCookie("rfidStockSelect_uid", rfidStockSelect_uid, 2);
		// 允许时间
		var rfidStockSetDays = $("#rfidStockSetDays").val();
		if (isUnsignedNumeric(rfidStockSetDays)) {
			setSearchData(rfidStockSetDays);// 设置日期
		} else {
			return;
		}
		getRfidStockData(0, 10);

	});
};
/**
 * 判断是不是数字
 * 
 * @param a
 * @returns {Boolean}
 */
function isUnsignedNumeric(a) {
	var rule = /^[0-9]\d*$/;
	if (!rule.test(a)) {
		console.log(a);
		alert("天数请输入数字");
		$("#rfidStockSetDays").val("");
		return false;
	}
	return true;
}

/**
 * 设置查询日期
 * 
 * @param day
 */
function setSearchData(day) {
	var stockSetDays = "";
	if (day != 0) {
		var n = day;
		var d = new Date();
		var year = d.getFullYear();
		var mon = d.getMonth() + 1;
		var day = d.getDate();
		if (day <= n) {
			if (mon > 1) {
				mon = mon - 1;
			} else {
				year = year - 1;
				mon = 12;
			}
		}
		d.setDate(d.getDate() - n);
		year = d.getFullYear();
		mon = d.getMonth() + 1;
		day = d.getDate();
		stockSetDays = year + "-" + (mon < 10 ? ('0' + mon) : mon) + "-"
				+ (day < 10 ? ('0' + day) : day) + " 00:00:00";
//		console.log("time: " + stockSetDays);
	} else {
		stockSetDays = "2080-11-18 00:00:00";
	}
	addCookie("rfidStockSetDays", stockSetDays, 2);
}

function getRfidStockData(startPage, endPage) {
	var rfidStockSelect_uid = getCookie("rfidStockSelect_uid");
	var rfidDays = getCookie("rfidStockSetDays");
	$
			.ajax({
				url : "rfidOrder/getRfidOrderList.do",
				type : "post",
				data : {
					"uid" : rfidStockSelect_uid,
					"startTime" : "1991-05-16 00:00:00",
					"endTime" : rfidDays,
					"startPage" : startPage,
					"endPage" : endPage,
					"stockType" : 3,
					"type" : 3
				// 在仓库
				},
				dataType : "json",
				success : function(result) {
					var rfidStockTb = $("#rfid_stock_tb");
					if (result.code == 10000) {
						getParseRfidStockData(result.result, startPage,
								endPage, rfidStockTb);
						if (getCookie("isRfidPageLoading") == 0
								|| getCookie("isRfidStockSetDays") != rfidDays
								|| getCookie("isRfidStockUid") != rfidStockSelect_uid) {
//							console.log("sssssssss_______"+result.result.count);
							setRfidStockOrderPaging(result.result.count);
							addCookie("isRfidStockUid", rfidStockSelect_uid, 2);
							addCookie("isRfidPageLoading", 1, 2);
							addCookie("isRfidStockSetDays", rfidDays, 2);
						}

					} else {
						rfidStockTb.html("");
						alert(result.message);
					}
				},
				error : function() {
					setRfidStockOrderPaging(0);
					alert("此用户无RFID数据");
				}
			});
}
function getParseRfidStockData(result, startPage, endPage, rfidStockTb) {
	rfidStockTb.html("");
	var so = "";
	// console.log(result.rfidOrders);
	for (var i = 0; i < result.rfidOrders.length; i++) {
		var rfidOrder = result.rfidOrders[i];
		var idName = rfidOrder.idName;
		var rfidOrderCreateTime = rfidOrder.rfidOrderCreateTime;
		var stockType = rfidOrder.stockType;
		var stock = "";
		var nick = result.user.nick;
		var rfidOrderNum = rfidOrder.rfidOrderNum;
		var rfidUserName = rfidOrder.rfidUser.rfidUserName;
		var ti = getStockTimer(rfidOrderCreateTime);
		// <td>序号</td>
		// <td>吨桶ID号</td>
		// <td>时间</td>
		// <td>操作员</td>
		// <td>单号</td>
		// <td>停留时间</td>
		var stayTime = setStayTime(rfidOrderCreateTime);
		// var color = "#676a6d";
		// if (stayTime > getCookie("rfidStockSetDays")) {// 大于多少天会出现红色
		var color = "red";
		// }
		// style="color: red;"
		so = '<tr style="color:' + color + ';">' + '<td>' + (i + 1) + '</td>'
				+ '<td>' + idName + '</td>' + '<td>' + ti + '</td>' + '<td>'
				+ nick + '</td>' + '<td>' + rfidOrderNum + '</td>' + '<td>'
				+ stayTime + '</td>' + '</tr>';

		var $so = $(so);
		rfidStockTb.append($so);
	}
}
/**
 * 获取停留时间（天数）
 * 
 * @param rfidOrderCreateTime
 */
function setStayTime(rfidOrderCreateTime) {
	var d = new Date();
	// console.log("stayTime: "+(d - rfidOrderCreateTime)/ 1000 / 60 / 60);
	return parseInt(Math.abs(d - rfidOrderCreateTime) / 1000 / 60 / 60 / 24);
}
/**
 * 格式化时间
 * 
 * @param rfidOrderCreateTime
 */
function getStockTimer(rfidOrderCreateTime) {
	var d = new Date(rfidOrderCreateTime);
	return d.getFullYear() + "年" + (d.getMonth() + 1) + "月" + d.getDate() + "日"
			+ d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
}