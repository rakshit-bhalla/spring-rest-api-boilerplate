package dev.rakshit.boilerplate.utils;

import dev.rakshit.boilerplate.models.Result;
import dev.rakshit.boilerplate.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static dev.rakshit.boilerplate.utils.ResponseUtil.INTERNAL_SERVER_ERROR_MESSAGE;
import static dev.rakshit.boilerplate.utils.ResponseUtil.getResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class ResponseUtilTests {

    private static final LocalDateTime NOW = LocalDateTime.now();
    private static final UUID UUID = java.util.UUID.randomUUID();
    private static final String EMAIL = "contact@rakshit.dev";
    private static final String ERROR_MESSAGE = "ERROR MESSAGE";
    private static final User USER = new User(UUID, EMAIL, NOW, NOW);
    private static final String NO_USER_FOUND = "NO USER FOUND";
    private static final HttpStatus OK_STATUS = HttpStatus.OK;
    private static final HttpStatus NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;
    private static final HttpStatus INTERNAL_SERVER_ERROR_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    @Test
    public void testGetResponseWithSupplierSuccess() {
        Result expectedResult = new Result(true, USER, null);
        ResponseEntity<Result> expectedResponse = new ResponseEntity<>(expectedResult, OK_STATUS);
        ResponseEntity<Result> response = getResponse(
                () -> getUser(true, false),
                NO_USER_FOUND,
                OK_STATUS,
                NOT_FOUND_STATUS
        );
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetResponseWithSupplierFailure() {
        Result expectedResult = new Result(false, null, NO_USER_FOUND);
        ResponseEntity<Result> expectedResponse = new ResponseEntity<>(expectedResult, NOT_FOUND_STATUS);
        ResponseEntity<Result> response = getResponse(
                () -> getUser(false, false),
                NO_USER_FOUND,
                OK_STATUS,
                NOT_FOUND_STATUS
        );
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetResponseWithSupplierError() {
        Result expectedResult = new Result(false, null, INTERNAL_SERVER_ERROR_MESSAGE);
        ResponseEntity<Result> expectedResponse = new ResponseEntity<>(expectedResult, INTERNAL_SERVER_ERROR_STATUS);
        ResponseEntity<Result> response = getResponse(
                () -> getUser(false, true),
                NO_USER_FOUND,
                OK_STATUS,
                NOT_FOUND_STATUS
        );
        assertThat(response).isEqualTo(expectedResponse);
    }

    public User getUser(boolean success, boolean error) {
        if(error) throw new RuntimeException(ERROR_MESSAGE);
        if(success) return USER;
        return null;
    }

}
