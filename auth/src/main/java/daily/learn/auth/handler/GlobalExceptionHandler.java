package daily.learn.auth.handler;

import daily.learn.auth.dto.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse<?>> authenticationExceptionHandle(AuthenticationException ex, WebRequest request) {
        log.error(ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>();
        response.setSuccess(false);
        response.setCode("4010000");
        response.setData(null);
        response.setMessage("un authorize");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(daily.learn.authen.handler.BusinessException.class)
    public ResponseEntity<BaseResponse<?>> businessExceptionHandle(daily.learn.authen.handler.BusinessException ex, WebRequest request) {
        log.error(ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>();
        response.setSuccess(false);
        response.setCode("fixme");
        response.setData(null);
        response.setMessage(ex.getMessage());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> baseExceptionHandle(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        BaseResponse<?> response = new BaseResponse<>();
        response.setSuccess(false);
        response.setCode("fixme");
        response.setData(null);
        response.setMessage(ex.getMessage());
        return ResponseEntity.ok(response);
    }

}
