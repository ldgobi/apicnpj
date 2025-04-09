import com.example.supplier.GlobalExceptionHandler;
import com.example.supplier.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    // Helper method to validate ErrorResponse
    private void validateErrorResponse(ErrorResponse errorResponse, String expectedStatus, String expectedMessage) {
        assertNotNull(errorResponse, "ErrorResponse should not be null");
        assertEquals(expectedStatus, errorResponse.getStatus(), "Status should match expected value");
        assertEquals(expectedMessage, errorResponse.getMessage(), "Message should match expected value");
    }

    @Test
    public void handleConstraintViolationException_ShouldReturnBadRequest() {
        // Arrange
        ConstraintViolationException exception = new ConstraintViolationException("Invalid input", null);

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleConstraintViolationException(exception);

        // Assert
        assertNotNull(response, "ResponseEntity should not be null");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status code should be BAD_REQUEST");
        validateErrorResponse(response.getBody(), HttpStatus.BAD_REQUEST.toString(), "Validation error: Invalid input");
    }

    @Test
    public void handleIllegalArgumentException_ShouldReturnBadRequest() {
        // Arrange
        IllegalArgumentException exception = new IllegalArgumentException("Illegal argument provided");

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleIllegalArgumentException(exception);

        // Assert
        assertNotNull(response, "ResponseEntity should not be null");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "Status code should be BAD_REQUEST");
        validateErrorResponse(response.getBody(), HttpStatus.BAD_REQUEST.toString(), "Illegal argument provided");
    }

    @Test
    public void handleException_ShouldReturnInternalServerError() {
        // Arrange
        Exception exception = new Exception("Unexpected error occurred");

        // Act
        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleException(exception);

        // Assert
        assertNotNull(response, "ResponseEntity should not be null");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Status code should be INTERNAL_SERVER_ERROR");
        validateErrorResponse(response.getBody(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), "An error occurred: Unexpected error occurred");
    }
}
