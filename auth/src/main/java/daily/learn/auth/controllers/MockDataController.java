package daily.learn.auth.controllers;


import daily.learn.auth.models.User;
import daily.learn.auth.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lkha
 */
@RestController()
@RequestMapping("/api/mock")
@RequiredArgsConstructor
public class MockDataController {

    private final UserService userService;

//    public MockDataController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {

        return ResponseEntity.ok("aaaa");
    }

    @GetMapping("/user")
    public ResponseEntity<String> mockUsers() {

        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .email("luutkha@email")
                    .username("luutkha")
                    .password("123456")
                    .build();
            user.setUsername("luutkha"+i);
            System.out.println(userService.saveUser(user).toString());
        }
        return ResponseEntity.ok("created!");
    }

}
