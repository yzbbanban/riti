package test.rfid.service;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.riti.rfid.dao.RfidOrderDAO;
import com.riti.rfid.entity.RfidOrder;
import com.riti.rfid.service.RfidOrderService;
import com.riti.rfid.service.impl.RfidOrderServiceImpl;

import test.TestBase;

public class TestRfidOrderServiceCase extends TestBase {

	@Before
	public void init() {
	}

	@Test
	public void test1() {
		RfidOrderService service=new RfidOrderServiceImpl();
		String orderJs="[{\"idName\":\"1111111111111D1000001607\",\"idTime\":\"19\","
				+ "\"isChecked\":true,"
				+ "\"rfidOrderCreateTime\":\"Sep 25, 2017 8:25:28 AM\","
				+ "\"rfidUserId\":\"b3b4521b-70ca-47d9-a9f9-9120a3860c85\"}"
				+ ",{\"idName\":\"1111111111111D1000001606\",\"idTime\":16,"
				+ "\"isChecked\":\"true\",\"rfidOrderCreateTime\":\"Sep 25, 2017 8:25:28 AM\","
				+ "\"rfidUserId\":\"b3b4521b-70ca-47d9-a9f9-9120a3860c85\"}]";
		service.addOrderListTask(orderJs);
	}

}
