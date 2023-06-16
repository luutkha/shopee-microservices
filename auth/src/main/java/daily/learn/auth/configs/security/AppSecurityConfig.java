package daily.learn.auth.configs.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppSecurityConfig  {

//    private  ConfigAuthenticationManager authenticationManager;
//
//    private UserService userService;

    @Bean
    public SecurityFilterChain apiAdminSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/admin/***")

                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .build();
    }
//    @Bean
//    public SecurityFilterChain apiUserSecurityFilterChain(HttpSecurity http) throws Exception {
//        return http.securityMatcher("/api/user/***")
//                .authorizeRequests(author -> author.anyRequest().hasRole("ADMIN_ROLE"))
//
//                .build();
//    }


}
