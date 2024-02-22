package com.demo.testo;

import com.demo.testo.models.User;
import com.demo.testo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestOApplicationTests {



	@Test
	void contextLoads() {
		User user = new User();
		user.setId(5);
		user.setPassword("123");
		user.setUsername("www");
	}

}
