package test.temp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.TestBase;

import com.riti.cool.dao.TempDAO;
import com.riti.cool.entity.QueryTempParams;
import com.riti.cool.entity.Temperature;
import com.riti.cool.util.ResultCode;

public class TestTempCase extends TestBase{
	private TempDAO dao;

	@Before
	public void init() {
		dao = super.getContext().getBean("tempDAO", TempDAO.class);
	}

	@Test
	public void test1() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < 100; i++) {
			Temperature temp = new Temperature();
			temp.setUid(1111112);
			temp.setDateTime(sdf.format(new Date(1502696936203L + i * 1000 * 60)));
			temp.setGoodsName("good" + i);
			temp.setMinTemperature(-10);
			temp.setMaxTemperature(20);
			temp.setTemperature(10);
			temp.setStatus(0);
			temp.setHum(80);
			temp.setMinHum(60);
			temp.setMaxHum(90);
			temp.setHumStatus(0);
			System.out.println(temp);
			dao.save(temp);
		}

	}
	@Test
	public void test2(){
		QueryTempParams qtp=new QueryTempParams();
		qtp.setUid(1111112);
		qtp.setStartTime("2017-08-14 15:50:56");
		qtp.setEndTime("2017-08-14 17:20:56");
		List<Temperature> temps=dao.findAll(qtp);
		System.out.println(temps);
	}
	
	@Test
	public void test3(){
		ResultCode<List<Temperature>> result=new ResultCode<List<Temperature>>();
		QueryTempParams qtp=new QueryTempParams();
		qtp.setUid(1111112);
		qtp.setStartTime("2017-08-14 15:50:56");
		qtp.setEndTime("2017-08-14 17:20:56");
		List<Temperature> temps=dao.findAll(qtp);
		result.setCode("10000");
		result.setMessage("成功");
		result.setResult(temps);
		System.out.println(result);
	}
	
	@Test
	public void test4(){
		Temperature temp=dao.findByDateTime(120003638);
		System.out.println(temp);
	}
	@Test
	public void test5(){
		QueryTempParams params=new QueryTempParams();
		params.setStartTime("2017-09-03 00:00:00");
		params.setEndTime("2017-09-06 00:00:00");
		params.setUid(120003638);
		int count=dao.getCount(params);
		System.out.println(count);
	}
	
}
