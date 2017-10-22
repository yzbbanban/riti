package test.company;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.riti.cool.dao.CompanyDAO;
import com.riti.cool.dao.UserDAO;
import com.riti.cool.entity.Company;
import com.riti.cool.entity.User;

import test.TestBase;

public class TestCompanyCase extends TestBase {
	private CompanyDAO companyDAO;

	@Before
	public void init() {
		companyDAO = super.getContext().getBean("companyDAO", CompanyDAO.class);
	}

	@Test
	public void test1(){
		Company company=new Company();
		company.setId("111");
		company.setName("riti");
		company.setAddress("aaa");
		company.setTel("11111");
		company.setEmail("qqqq");
		companyDAO.addCompany(company);
	}

	@Test
	public void test2() {
		
		Company company=companyDAO.findCompanyByName("q111111");
		System.out.println(company.getId());
	}

	@Test
	public void test3() {
		Company company=companyDAO.findCompanyById("92662db9-4345-4dc0-96b5-cf316111f667");
		System.out.println(company);
	}
	
	
	@Test
	public void test4() {
		
		 List<Company> companys = companyDAO.findCompanyByBlurName("q111");
		System.out.println(companys);
	}
}
