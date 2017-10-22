package test.rfid.rfidUser;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.riti.cool.dao.CompanyDAO;
import com.riti.cool.dao.UserDAO;
import com.riti.cool.entity.Company;
import com.riti.cool.entity.User;
import com.riti.rfid.dao.RfidUserDAO;
import com.riti.rfid.entity.RfidUser;

import test.TestBase;

public class TestRfidUserCase extends TestBase {
	private RfidUserDAO dao;

	@Before
	public void init() {
		dao = super.getContext().getBean("rfidUserDAO", RfidUserDAO.class);
	}

	@Test
	public void test1() {
		RfidUser rfidUser = new RfidUser();
		rfidUser.setRfidUserId("11");
		rfidUser.setUserId("22");
		rfidUser.setRfidUserName("33");
		rfidUser.setRfidUserLocation("44");
		rfidUser.setRfidUserCreatTime(new Date());
		dao.addRfidUser(rfidUser);
	}

	@Test
	public void test2() {
		String id = "441f14d7-219e-4d97-9f7a-10e36be23eab";
		List<RfidUser> rus = dao.getRfidUserList(id);
		System.out.println(rus);

	}
	@Test
	public void test3() {
		String id = "e882600d-c95d-4321-9bc7-93cc4ec1d5f3";
		RfidUser rfidUser=new RfidUser();
		rfidUser.setRfidUserId(id);
		rfidUser.setRfidUserLocation("ss");
		rfidUser.setRfidUserCreatTime(new Date());
		rfidUser.setRfidUserName("UUUUUUUUUUUUUUUUUU");
		dao.updateRfidUser(rfidUser);
		System.out.println(rfidUser);

	}
	
	@Test
	public void test4() {
		String id = "b58cf79b-af82-462d-a47b-8e107e75ca4f";
		dao.deleteRfidUser(id);
		System.out.println(id);

	}

}
