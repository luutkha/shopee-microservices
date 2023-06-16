package daily.learn.auth.controllers;

import daily.learn.auth.dto.request.UserLoginRequest;
import daily.learn.auth.dto.response.UserLoginResponse;
import daily.learn.auth.services.UserService;
import daily.learn.auth.utils.JwtUtils;

import daily.learn.auth.models.User;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtTokenUtil;

    @GetMapping()
    public ResponseEntity<List<User>> getAll(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam(name = "sortBy", required = false, defaultValue = "username") String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<User> usersPage = userService.findAll(pageable);
        List<User> usersList = usersPage.getContent();
        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/{username}")
    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @PostAuthorize("hasAuthority('ADMIN_ROLE') or returnObject.username == authentication.principal.username")
    public User getUserByUserName(@PathVariable String username) {
        System.out.println(username);
        return userService.findByUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PermitAll
    @PostMapping("/login")
    public UserLoginResponse createAuthToken(@RequestBody UserLoginRequest authenticationRequest) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);

//        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateJwtAccessTokenFromAuthentication(authentication);
        return new UserLoginResponse(token);


    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUserIdFromJwtToken(token);
        UserDetails user = (UserDetails) userService.loadUserByUsername(username);

        String refreshedToken = jwtTokenUtil.generateJwtRefreshTokenFromUserId(username);
        return ResponseEntity.ok(refreshedToken);

    }

}
