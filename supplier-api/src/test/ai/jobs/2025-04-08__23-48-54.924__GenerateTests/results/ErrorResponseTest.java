import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ErrorResponseTest {

    // Test to verify the constructor initializes fields correctly
    @Test
    public void ErrorResponse_Constructor_ShouldInitializeFieldsCorrectly() {
        String expectedCode = "404";
        String expectedMessage = "Not Found";

        ErrorResponse errorResponse = new ErrorResponse(expectedCode, expectedMessage);

        assertNotNull(errorResponse, "ErrorResponse object should not be null");
        assertEquals(expectedCode, errorResponse.getCode(), "Code should match the expected value");
        assertEquals(expectedMessage, errorResponse.getMessage(), "Message should match the expected value");
    }

    // Test to verify the getter for 'code'
    @Test
    public void ErrorResponse_GetCode_ShouldReturnCorrectValue() {
        String expectedCode = "500";
        ErrorResponse errorResponse = new ErrorResponse(expectedCode, "Internal Server Error");

        assertEquals(expectedCode, errorResponse.getCode(), "getCode() should return the correct code");
    }

    // Test to verify the setter for 'code'
    @Test
    public void ErrorResponse_SetCode_ShouldUpdateValueCorrectly() {
        ErrorResponse errorResponse = new ErrorResponse("200", "OK");
        String newCode = "201";

        errorResponse.setCode(newCode);

        assertEquals(newCode, errorResponse.getCode(), "setCode() should update the code correctly");
    }

    // Test to verify the getter for 'message'
    @Test
    public void ErrorResponse_GetMessage_ShouldReturnCorrectValue() {
        String expectedMessage = "Bad Request";
        ErrorResponse errorResponse = new ErrorResponse("400", expectedMessage);

        assertEquals(expectedMessage, errorResponse.getMessage(), "getMessage() should return the correct message");
    }

    // Test to verify the setter for 'message'
    @Test
    public void ErrorResponse_SetMessage_ShouldUpdateValueCorrectly() {
        ErrorResponse errorResponse = new ErrorResponse("200", "OK");
        String newMessage = "Created";

        errorResponse.setMessage(newMessage);

        assertEquals(newMessage, errorResponse.getMessage(), "setMessage() should update the message correctly");
    }
}
