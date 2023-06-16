package daily.learn.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import daily.learn.auth.dto.common.BaseResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AppAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper;

    public AppAccessDeniedHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }



    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        BaseResponse<?> unAuthorizeResponse = new BaseResponse<>();
        unAuthorizeResponse.setSuccess(false);
        unAuthorizeResponse.setCode("4030000");
        unAuthorizeResponse.setData(null);
        unAuthorizeResponse.setMessage("access denied");
        response.getWriter().println(mapper.writeValueAsString(unAuthorizeResponse));
    }
}