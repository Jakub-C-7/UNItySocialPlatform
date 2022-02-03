package com.example.demo;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import com.example.demo.registration.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
//
//	private AppUserRepository appUserRepository;
//	private AppUser appUser = new AppUser(
//			"firstnametest",
//			"lastnametest",
//			"firstnamelastnametest@gmail.com",
//			"passwordtest",
//			AppUserRole.USER
//	);
//	@Test
//	public void testRegisterUser(){
//		appUserRepository.save(appUser);
//
//		boolean userExists = appUserRepository
//				.findByEmail(appUser.getEmail())
//						.isPresent();
//		assertThat(userExists).isTrue();
//	}

}
