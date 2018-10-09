package com.kwong.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kwong.boot.system.model.User;
import com.kwong.boot.system.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KwongBootApplicationTests {

	@Autowired 
    private UserRepository userRepository;
	
	@Test
	public void contextLoads() {
		User user = userRepository.findByUsername("root");
		System.err.println(user.toString());
	}

}
