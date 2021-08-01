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

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";

    /**
     * Returns ResponseEntity of Result by taking data, error and httpStatus
     *
     * @param data   data of result
     * @param error   error message of result
     * @param httpStatus   Success status of ResponseEntity
     * @return ResponseEntity of Result
     */
    private static ResponseEntity<Result> getResponse(Object data, String error, HttpStatus httpStatus) {
        return new ResponseEntity<>(new Result(Strings.isNullOrEmpty(error), data, error), httpStatus);
    }

    /**
     * Returns ResponseEntity of Result by taking data, error, successStatus and errorStatus
     * If success is true, then ResponseEntity of data and successStatus is sent
     * If success is false, then ResponseEntity of error and errorStatus is sent
     *
     * @param data   data of result
     * @param error   Error message to be shown when success is false
     * @param successStatus   Success status to be used when success is true
     * @param errorStatus   Success status to be used when success is false
     * @return ResponseEntity of Result
     */
    private static ResponseEntity<Result> createResponse(Object data, String error, HttpStatus successStatus, HttpStatus errorStatus) {
        return Optional.ofNullable(data)
                .map(user -> getResponse(user,null, successStatus))
                .orElseGet(() -> getResponse(null, error, errorStatus));
    }

    /**
     * Returns ResponseEntity of Result by taking supplier, error, successStatus and errorStatus
     * If supplier throws any error, the error is logged and default result of INTERNAL_SERVER_ERROR is sent
     * If supplier works fine and if success is true, then ResponseEntity of data and successStatus is sent
     * If supplier works fine and if success is false, then ResponseEntity of error and errorStatus is sent
     *
     * @param supplier Supplier
     * @param error   Error message to be shown when success is false
     * @param successStatus   Success status to be used when success is true
     * @param errorStatus   Success status to be used when success is false
     * @return ResponseEntity of Result
     */
    public static ResponseEntity<Result> getResponse(Supplier<Object> supplier, String error, HttpStatus successStatus, HttpStatus errorStatus) {
        try {
            Object response = supplier.get();
            log.info("{}", response);
            return createResponse(
                    response,
                    error,
                    successStatus,
                    errorStatus
            );
        } catch (Exception err) {
            log.error("{} {}", err.getMessage(), err.getStackTrace());
            return createResponse(
                    null,
                    INTERNAL_SERVER_ERROR_MESSAGE,
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
