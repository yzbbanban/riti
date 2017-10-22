package test.device;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.riti.cool.dao.DeviceDAO;
import com.riti.cool.entity.DeviceParams;
import com.riti.cool.entity.QueryDeviceParams;

public class TestDeviceCase {
	private DeviceDAO dao;
	private String[] conf = new String[] { "conf/spring-mybatis.xml", "conf/springmvc.xml" };

	@Before
	public void init() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		dao = ac.getBean("deviceDAO", DeviceDAO.class);
	}

	@Test
	public void test1() {
		DeviceParams dp = new DeviceParams();
		dp.setUid(121212121);
		dp.setCompany("riti");
		dp.setDeviceNum("444444");
		dp.setDataUploadMode(1);
		dp.setInterval(1);
		dp.setMaxHum(new Float("90.0"));
		dp.setMaxTemperature(new Float("40.0"));
		dp.setMinHum(new Float("60.0"));
		dp.setMinTemperature(new Float("10.0"));
		dp.setModel(1);
		dp.setReadStop(1);
		dp.setTagType(1);
		dp.setGoodsName("uu");
		dp.setStart(1);
		dao.save(dp);
	}

	@Test
	public void test2() {
		QueryDeviceParams qdp=new QueryDeviceParams();
		qdp.setCompany("riti");
		List<DeviceParams> dps = dao.findAll(qdp);
		System.out.println(dps.size());
		System.out.println(dps);
	}

	@Test
	public void test3() {
		DeviceParams dp = dao.findById(2517334);
		System.out.println(dp);
	}
	
	@Test
	public void test4() {
		DeviceParams dp = dao.findById(444444444);
		dp.setCompany("riti2");
		System.out.println(dao.modify(dp));
		DeviceParams dp2 = dao.findById(444444444);
		System.out.println(dp2);
	}
	
}
