# Controller 예외 처리

## @RestControllerAdvice

- 가독성이 떨어지는 try-catch 문 대신 Controller 패키지에서 사용하기 위해 고안된 어노테이션

- 단순 예외 처리는 @ControllerAdvice, 응답으로 객체를 리턴할 때는 @RestControllerAdvice

- Controller 패키지 내에 advice 패키지 추가 후 새로운 클래스 생성


## @ExceptionHandler

- 해당 어노테이션을 메서드에 선언한 뒤 특정 예외 클래스를 지정해주면, 메서드에 정의한 로직으로 처리가 가능함

- @Controller, @RestController에만 적용이 가능함
- @Service 계층에 사용하는 어노테이션이 아님

``` Java
package org.zerock.mallapi.controller.advice;


import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> notExist (NoSuchElementException e) {

        String msg = e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg", msg));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleIllegalArgumentException(MethodArgumentNotValidException e) {

        String msg = e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("msg", msg));
    }
    
}
```

-> ResponseEntity 와 관련된 예외 처리가 실행됨
    1. notExist : Entity가 존재하지 않을 시
    2. handleIllegalArgumentException  : Entity가 형식에 맞지 않을 시
    


