import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    // Helper method to validate ErrorResponse
    private void validateErrorResponse(ResponseEntity<ErrorResponse> response, HttpStatus expectedStatus, String expectedMessage) {
        assertNotNull(response, "Response should not be null");
        assertEquals(expectedStatus, response.getStatusCode(), "Unexpected HTTP status");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(expectedStatus.toString(), response.getBody().getStatus(), "Unexpected status in ErrorResponse");
        assertEquals(expectedMessage, response.getBody().getMessage(), "Unexpected message in ErrorResponse");
    }

    @Test
    public void handleConstraintViolationException_ShouldReturnBadRequest() {
        // Arrange
        ConstraintViolationException exception = new ConstraintViolationException("Invalid input", null);

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleConstraintViolationException(exception);

        // Assert
        validateErrorResponse(response, HttpStatus.BAD_REQUEST, "Validation error: Invalid input");
    }

    @Test
    public void handleIllegalArgumentException_ShouldReturnBadRequest() {
        // Arrange
        IllegalArgumentException exception = new IllegalArgumentException("Illegal argument provided");

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleIllegalArgumentException(exception);

        // Assert
        validateErrorResponse(response, HttpStatus.BAD_REQUEST, "Illegal argument provided");
    }

    @Test
    public void handleException_ShouldReturnInternalServerError() {
        // Arrange
        Exception exception = new Exception("Unexpected error occurred");

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleException(exception);

        // Assert
        validateErrorResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred: Unexpected error occurred");
    }

    @Test
    public void handleException_ShouldHandleNullMessageGracefully() {
        // Arrange
        Exception exception = new Exception();

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleException(exception);

        // Assert
        validateErrorResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred: null");
    }
}
