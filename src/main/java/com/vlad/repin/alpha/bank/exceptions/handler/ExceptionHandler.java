package com.vlad.repin.alpha.bank.exceptions.handler;

import com.vlad.repin.alpha.bank.annotation.BaseExceptionHandler;
import com.vlad.repin.alpha.bank.api.ResponseDto;
import com.vlad.repin.alpha.bank.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice(annotations = BaseExceptionHandler.class)
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({BaseException.class})
    public ResponseEntity<ResponseDto<Void>> handleGymBaseException(BaseException exc) {
        String message = exc.getMessage();
        log.error("ERROR, message = {}, type = {}", message, exc.getType());
        return new ResponseEntity<>(ResponseDto.error(message), HttpStatus.OK);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseDto<Void>> handleException(Exception exc) {
        String message = exc.getMessage();
        log.error("ERROR, message = {}", message);
        return new ResponseEntity<>(ResponseDto.error(message), HttpStatus.OK);
    }
}
