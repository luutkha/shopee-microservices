package daily.learn.auth.configs.security.filters;

import daily.learn.auth.models.User;
import daily.learn.auth.repositories.UserRepository;
import daily.learn.auth.utils.JwtUtils;
import daily.learn.authen.handler.BusinessException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class AppAuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;


    private String parseJwt(HttpServletRequest request) {
        log.info(request.getRequestURI());
        String headerAuth = request.getHeader("Authorization");
        log.info("header auth = {}", headerAuth);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) {
            headerAuth = headerAuth.replace("Bearer", "").trim();
            return headerAuth;
        }
        log.error("Authorization header is null, path = {}", request.getRequestURI());
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getRequestURI();
//        if (requestURI != null && requestURI.startsWith("/api/user/login")) {
//            return true;
//        }
//        if (requestURI != null && requestURI.startsWith("/api/v1/user/register")) {
//            return true;
//        }
//        if (requestURI != null && requestURI.startsWith("/webjars/swagger-ui")) {
//            return true;
//        }
//        if (requestURI != null && requestURI.startsWith("/v3/api-docs")) {
//            return true;
//        }
//        if (requestURI != null && requestURI.startsWith("/swagger-resources")) {
//            return true;
//        }
//        if (requestURI != null && requestURI.startsWith("/webjars")) {
//            return true;
//        }
//        if (requestURI != null && requestURI.startsWith("/swagger-ui/")) {
//            return true;
//        }
//        if (requestURI != null && requestURI.startsWith("/swagger-ui.html")) {
//            return true;
//        }
        return super.shouldNotFilter(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("in do filter internal");
        String jwt = parseJwt(request);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = String.valueOf(jwtUtils.getUserIdFromJwtToken(jwt));

            Optional<User> findByUsername = userRepository.findByUsername(username);
            if (findByUsername.isEmpty()) {
                String userNotFound = "user_not_found";
                log.error(userNotFound);
                throw new BusinessException(userNotFound);
            }
            var userDetails = findByUsername.get();
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            var context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        log.info("in do filter internal");
//        String jwt = parseJwt(request);
//        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
//            Long userId = Long.valueOf(jwtUtils.getUserIdFromJwtToken(jwt));
//
//            Optional<User> findById = userRepository.findById(userId);
//            if (findById.isEmpty()) {
//                String userNotFound = "user_not_found";
//                log.error(userNotFound);
//                throw new BusinessException(userNotFound);
//            }
//            var userDetails = findById.get();
//            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
//                    userDetails.getAuthorities());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            var context = SecurityContextHolder.createEmptyContext();
//            context.setAuthentication(authentication);
//            SecurityContextHolder.setContext(context);
//        }
//        filterChain.doFilter(request, response);
//    }
}