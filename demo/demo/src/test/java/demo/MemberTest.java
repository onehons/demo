package demo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.duantian.cms.entity.Member;
import com.duantian.cms.entity.Member.Gender;
import com.duantian.cms.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MemberTest {

	@Autowired
	private MemberService userService;

	@Test
	public void saveTest() {
		Member member = new Member();
		member.setAddress("address");
		member.setBirth(new Date());
		member.setEmail("email");
		member.setPassword("password");
		member.setTel("13000000000");
		member.setGender(Gender.MALE);
		member.setName("duantian");
		userService.save(member);
	}

	@Test
	public void findTest() {
//		Page page = userService.find();
//		System.out.println(page);
	}
}
