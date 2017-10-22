package test.tagList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.riti.cool.dao.CompanyDAO;
import com.riti.cool.dao.TagListDAO;
import com.riti.cool.dao.UserDAO;
import com.riti.cool.entity.Company;
import com.riti.cool.entity.DeviceList;
import com.riti.cool.entity.User;

import test.TestBase;

public class TestTagListCase extends TestBase {
	private TagListDAO tagListDAO;

	@Before
	public void init() {
		tagListDAO = super.getContext().getBean("tagListDAO", TagListDAO.class);
	}

	@Test
	public void test1() {
		DeviceList deviceList = new DeviceList();
		deviceList.setId("aa");
		deviceList.setUid("bb");
		deviceList.setUserId("bb");
		int row=tagListDAO.saveTagList(deviceList);
		System.out.println(row);
	}

	@Test
	public void test2() {
		List<DeviceList> deviceLists = tagListDAO.getTagList("10207a63-5035-49e7-a3ae-935b74e0a6e8");
		System.out.println(deviceLists);
	}

	@Test
	public void test3() {
		DeviceList dev = new DeviceList();
		dev.setUid("bb");
		dev.setUserId("bb");
		DeviceList deviceList = tagListDAO.getDevice(dev);
		System.out.println(deviceList);
	}

	@Test
	public void test4() {
		
	}
}
