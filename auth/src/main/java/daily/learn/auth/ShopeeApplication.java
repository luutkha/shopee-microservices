package daily.learn.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories
public class ShopeeApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShopeeApplication.class, args);

	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	CommandLineRunner fakeData(UserService userService, RoleService roleService){
//		return args -> {
//			Role userRole = new Role("USER_ROLE");
//			Role adminRole = new Role("ADMIN_ROLE");
//
//			System.out.println(userRole.toString());
//			roleService.saveRole(userRole);
//			roleService.saveRole(adminRole);
//
//
//
//			User user = User.builder()
//					.email("luutkha@gmail.com")
//					.username("luutkha")
//					.password("123456")
//					.build();
//			User user2 = User.builder()
//					.email("luuctuong@gmail.com")
//					.username("luuctuong")
//					.password("123456")
//					.build();
//			userService.saveUser(user);
//			userService.saveUser(user2);
////
//
//			User user = userService.findByUsername("luutkha");
//			User user2 = userService.findByUsername("luuctuong");
//
//			Role userRole = roleService.findByName("USER_ROLE");
//			Role adminRole = roleService.findByName("ADMIN_ROLE");
//
//			System.out.println(userRole.toString());
//			userService.grantRole(user,userRole);
//			userService.grantRole(user2,adminRole);
//
////			System.out.println(user2.toString());
//
//
//		};
//
//	}

}
