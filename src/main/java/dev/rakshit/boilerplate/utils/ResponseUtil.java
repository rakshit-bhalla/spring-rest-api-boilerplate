package dev.rakshit.boilerplate.utils;

import com.google.common.base.Strings;
import dev.rakshit.boilerplate.models.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
public class ResponseUtil {

    private static ResponseEntity<Result> getResponse(Object data, String error, HttpStatus httpStatus) {
        return new ResponseEntity<>(new Result(Strings.isNullOrEmpty(error), data, error), httpStatus);
    }

    private static ResponseEntity<Result> getResponse(Object data, String error, HttpStatus successStatus, HttpStatus errorStatus) {
        return Optional.ofNullable(data)
                .map(user -> getResponse(user, null, successStatus))
                .orElseGet(() -> getResponse(null, error, errorStatus));
    }

    public static ResponseEntity<Result> getResponse(Supplier<Object> supplier, String error, HttpStatus successStatus, HttpStatus errorStatus) {
        try {
            Object response = supplier.get();
            log.info("{}", response);
            return getResponse(
                    response,
                    error,
                    successStatus,
                    errorStatus
            );
        } catch (Exception err) {
            log.error("{} {}", err.getMessage(), err.getStackTrace());
            return getResponse(
                    null,
                    "Internal Server Error",
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
