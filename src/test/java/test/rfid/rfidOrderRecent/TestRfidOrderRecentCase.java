package test.rfid.rfidOrderRecent;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.riti.rfid.dao.RfidOrderRecentDAO;
import com.riti.rfid.entity.QueryRfidParams;
import com.riti.rfid.entity.RfidOrder;

import test.TestBase;

public class TestRfidOrderRecentCase extends TestBase {
	private RfidOrderRecentDAO dao;

	@Before
	public void init() {
		dao = super.getContext().getBean("rfidOrderRecentDAO", RfidOrderRecentDAO.class);
	}

	@Test
	public void test1() {
		RfidOrder rfidOrder = new RfidOrder();
		rfidOrder.setRfidOrderCreateTime(new Date());
		rfidOrder.setRfidUserId("sss");
		rfidOrder.setIdName("ban");
		rfidOrder.setRfidOrderNum("");
		dao.addOrderRecentListTask(rfidOrder);
	}

	@Test
	public void test2() {
		
		QueryRfidParams qrp=new QueryRfidParams();
		qrp.setStartPage(0);
		qrp.setEndPage(10);
		qrp.setStartTime("1991-05-16 00:00:00");
		qrp.setEndTime("2080-11-18 00:00:00");
		qrp.setStockType(6);
		qrp.setUid("b547633f-0c36-40b7-b3b2-04dfd8f8ea78");
		List<RfidOrder> s = dao.getOrderRecentListTaskById(qrp);
		System.out.println(s.size());
	}

	@Test
	public void test3() {
	}

	
}
