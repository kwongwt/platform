package com.kwong.boot;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;

import com.kwong.boot.system.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KwongBootApplicationTests {

	@Autowired 
    private UserRepository userRepository;
	
	@Test
	public void contextLoads() {
		String keyStr = "kwong";
        byte[] keys;
		try {
			keys = keyStr.getBytes("UTF-8");
	        System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keys, 16)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
