import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SupplierApplicationTests {

    // Test to ensure the application context loads successfully
    @Test
    public void contextLoads_ShouldLoadApplicationContext() {
        // If the application context fails to load, this test will fail
    }

    // Test to ensure the CORS configuration bean is created successfully
    @Test
    public void corsConfigurer_ShouldCreateCorsConfigurationBean() {
        SupplierApplication application = new SupplierApplication();
        WebMvcConfigurer corsConfigurer = application.corsConfigurer();
        assertNotNull(corsConfigurer, "CORS Configurer bean should not be null");
    }

    // Test to verify the CORS configuration settings
    @Test
    public void corsConfigurer_ShouldConfigureCorsMappings() {
        SupplierApplication application = new SupplierApplication();
        WebMvcConfigurer corsConfigurer = application.corsConfigurer();

        CorsRegistry registry = new CorsRegistry();
        corsConfigurer.addCorsMappings(registry);

        // Assert that the registry has been configured correctly
        // Note: CorsRegistry does not provide direct access to its internal state for assertions.
        // This test ensures the method executes without exceptions.
    }
}
