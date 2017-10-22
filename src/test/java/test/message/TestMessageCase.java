package test.message;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.riti.cool.dao.CompanyDAO;
import com.riti.cool.dao.MessageDAO;
import com.riti.cool.dao.UserDAO;
import com.riti.cool.entity.Company;
import com.riti.cool.entity.User;
import com.riti.cool.entity.UserMessage;

import test.TestBase;

public class TestMessageCase extends TestBase {
	private MessageDAO messageDAO;

	@Before
	public void init() {
		messageDAO = super.getContext().getBean("messageDAO", MessageDAO.class);
	}

	@Test
	public void test1() {
		UserMessage message = new UserMessage();
		message.setCompanyId("111");
		java.util.Date createTime = new java.util.Date(
				System.currentTimeMillis());
		message.setCreateTime(createTime);
		message.setHandleId("nnn");
		message.setUserId("sss");
		message.setId("222");
		message.setMessageDetial("sss");
		messageDAO.addMessage(message);
	}

	@Test
	public void test2() {
		List<UserMessage> messages = messageDAO
				.getMessageList("a540c5be-ccde-4fc5-9dec-225ed43f32d6");
		System.out.println(messages);

	}

	@Test
	public void test3() {
		UserMessage message=new UserMessage("111","sss");
		int  n = messageDAO.deleteMessageById(message);
		System.out.println(n );
	}

	@Test
	public void test4() {

	}
}
