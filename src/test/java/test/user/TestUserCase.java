package test.user;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.riti.cool.dao.UserDAO;
import com.riti.cool.entity.User;

import test.TestBase;

public class TestUserCase extends TestBase {
	private UserDAO userDAO;

	@Before
	public void init() {
		userDAO = super.getContext().getBean("userDAO", UserDAO.class);
	}

	@Test
	public void test1() {
		java.util.Date createTime = new java.util.Date(
				System.currentTimeMillis());
		User user = new User("5555", "name", "password", "token", "nick",
				createTime);
		System.out.println(user);
		userDAO.addUser(user);
	}

	@Test
	public void test2() {
		User user = userDAO.findUserByName("banban");
		System.out.println(user);
	}

	@Test
	public void test3() {
		User user = userDAO.findUserById("111");
		System.out.println(user);
	}

	@Test
	public void test4() {
		User user = new User();
		user.setId("111");
		user.setCompanyId("111");
		int row = userDAO.updateUserCompany(user);
		System.out.println(row);
	}

	@Test
	public void test5() {
		User user = userDAO.findUserByName("111111");
		User u = userDAO.findUserByCompany(user);
		System.out.println(u);
	}

	@Test
	public void test6() {
		User user = userDAO.findUserByName("name");
		System.out.println(user);
		user.setUserLevel(10);
		int u = userDAO.updateUserCompanyLevel(user);
		System.out.println(u);
	}
	@Test
	public void test7() {
		User user = userDAO.findUserByName("444444");
		 List<User> u = userDAO.findUserByCompanyId(user);
		System.out.println(u);
	}

}
