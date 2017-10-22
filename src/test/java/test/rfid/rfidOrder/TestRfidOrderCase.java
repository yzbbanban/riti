package test.rfid.rfidOrder;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.riti.rfid.dao.RfidOrderDAO;
import com.riti.rfid.entity.QueryRfidParams;
import com.riti.rfid.entity.RfidOrder;

import test.TestBase;

public class TestRfidOrderCase extends TestBase {
	private RfidOrderDAO dao;

	@Before
	public void init() {
		dao = super.getContext().getBean("rfidOrderDAO", RfidOrderDAO.class);
	}

	@Test
	public void test1() {
		RfidOrder rfidOrder = new RfidOrder();
		rfidOrder.setRfidOrderCreateTime(new Date());
		rfidOrder.setRfidUserId("sss");
		rfidOrder.setIdName("ban");
		rfidOrder.setRfidOrderNum("");
		dao.addOrderListTask(rfidOrder);
	}

	@Test
	public void test2() {
		String id = "8c79eb38-ca44-481f-b698-2daa41774df4";
		// System.out.println(dao.getOrderListTaskById(id));
	}

	@Test
	public void test3() {
		String id = "123456789";
		// System.out.println(dao.getOrderListTaskByNum(id));
	}

	@Test
	public void test4() {
		QueryRfidParams qrp = new QueryRfidParams();
		qrp.setStartPage(1);
		qrp.setEndPage(10);
		qrp.setStartTime("2017-09-01 00:00:00");
		qrp.setEndTime("2017-10-11 10:32:00");
		qrp.setStockType(3);
		qrp.setType(2);
		qrp.setUid("D1000001606");
		List<RfidOrder> e = dao.getOrderListTaskByRfidName(qrp);
		System.out.println(e);
	}
	@Test
	public void test5() {
		QueryRfidParams qrp = new QueryRfidParams();
		qrp.setStartPage(1000000);
		qrp.setEndPage(0);
		qrp.setStartTime("1991-05-16 00:00:00");
		qrp.setEndTime("2080-11-18 00:00:00");
		qrp.setStockType(3);
		qrp.setType(3);
		long e = dao.getRfidCount(qrp);
		System.out.println(e);
	}
}
