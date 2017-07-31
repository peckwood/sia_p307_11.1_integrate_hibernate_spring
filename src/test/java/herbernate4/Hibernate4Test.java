package herbernate4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hibernate4.data.UserRepository;
import hibernate4.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)

//@ContextConfiguration("classpath:data.xml")
//@ContextConfiguration(classes=hibernate4.config.HibernateConfig.class)
@ContextConfiguration(classes=hibernate4.config.HibernateConfigSIA.class)

public class Hibernate4Test {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void hibernateTest(){
		//14 is age, not ID.
		User user = new User(27, "raiden", "raiden@qq.com", "中国");
		Long id = userRepository.save(user).getId();
		System.out.println("found by username: " + userRepository.findByUsername("raiden"));
		System.out.println("found by ID: " + userRepository.findById(id));
		userRepository.deleteByUsername(user.getUsername());
		
	}
}
