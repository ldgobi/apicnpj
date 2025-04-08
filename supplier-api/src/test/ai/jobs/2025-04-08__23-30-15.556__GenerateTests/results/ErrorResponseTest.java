import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ErrorResponseTest {

    // Helper method to create a sample ErrorResponse object
    private ErrorResponse createSampleErrorResponse() {
        return new ErrorResponse("404", "Not Found");
    }

    @Test
    public void constructor_ShouldInitializeFieldsCorrectly() {
        // Arrange & Act
        ErrorResponse errorResponse = new ErrorResponse("500", "Internal Server Error");

        // Assert
        assertNotNull(errorResponse, "ErrorResponse object should not be null");
        assertEquals("500", errorResponse.getCode(), "Code should be initialized correctly");
        assertEquals("Internal Server Error", errorResponse.getMessage(), "Message should be initialized correctly");
    }

    @Test
    public void getCode_ShouldReturnCorrectCode() {
        // Arrange
        ErrorResponse errorResponse = createSampleErrorResponse();

        // Act
        String code = errorResponse.getCode();

        // Assert
        assertEquals("404", code, "getCode should return the correct code");
    }

    @Test
    public void setCode_ShouldUpdateCodeCorrectly() {
        // Arrange
        ErrorResponse errorResponse = createSampleErrorResponse();

        // Act
        errorResponse.setCode("500");

        // Assert
        assertEquals("500", errorResponse.getCode(), "setCode should update the code correctly");
    }

    @Test
    public void getMessage_ShouldReturnCorrectMessage() {
        // Arrange
        ErrorResponse errorResponse = createSampleErrorResponse();

        // Act
        String message = errorResponse.getMessage();

        // Assert
        assertEquals("Not Found", message, "getMessage should return the correct message");
    }

    @Test
    public void setMessage_ShouldUpdateMessageCorrectly() {
        // Arrange
        ErrorResponse errorResponse = createSampleErrorResponse();

        // Act
        errorResponse.setMessage("Service Unavailable");

        // Assert
        assertEquals("Service Unavailable", errorResponse.getMessage(), "setMessage should update the message correctly");
    }
}
