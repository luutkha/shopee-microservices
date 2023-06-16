package daily.learn.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import daily.learn.auth.dto.common.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AppAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    public AppAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        BaseResponse<?> unAuthorizeResponse = new BaseResponse<>();
        unAuthorizeResponse.setSuccess(false);
        unAuthorizeResponse.setCode("4010000");
        unAuthorizeResponse.setData(null);
        unAuthorizeResponse.setMessage("un authorize");
        response.getWriter().println(mapper.writeValueAsString(unAuthorizeResponse));
    }


}