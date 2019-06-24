package com.thoughtmechanix.core.spmiaauthenticationservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication
public class SpmiaAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpmiaAuthenticationServiceApplication.class, args);
	}

	@RequestMapping(value = { "/user" }, produces = "application/json")
	public Map<String, Object> user(OAuth2Authentication user) {
		Map<String, Object> userInfo = new HashMap<>();
	    userInfo.put("user", user.getUserAuthentication().getPrincipal());
	    userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
	    return userInfo;
	  }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
