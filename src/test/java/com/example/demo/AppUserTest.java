package com.example.demo;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AppUserTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @Test
    public void contextLoads() {
    }

    @Test
	public void testRegisterUser(){

        AppUser appUser = new AppUser(
                "firstname",
                "lastname",
                "first@last.gmail.com",
                "test",
                AppUserRole.USER);

        appUserService.signUpUser(appUser);

		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

		assertThat(userExists).isTrue();
	}

    @Test
    public void testDeletingUser(){

        AppUser appUser = new AppUser(
                "firstname",
                "lastname",
                "first@last.gmail.com",
                "test",
                AppUserRole.USER);

        appUserService.deleteUser(appUserRepository.findByEmail(appUser.getEmail()).get().getId());

        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        assertThat(userExists).isFalse();
    }

}
