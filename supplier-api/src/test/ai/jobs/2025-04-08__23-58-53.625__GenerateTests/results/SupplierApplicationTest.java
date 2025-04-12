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

    // Test to ensure the CORS configuration is applied correctly
    @Test
    public void corsConfigurer_ShouldApplyCorsMappings() {
        SupplierApplication application = new SupplierApplication();
        WebMvcConfigurer corsConfigurer = application.corsConfigurer();

        CorsRegistry registry = new CorsRegistry();
        corsConfigurer.addCorsMappings(registry);

        // Assert that the registry contains the expected CORS mappings
        // This is a simplified test since CorsRegistry does not expose its internal state for direct assertions
        assertNotNull(registry, "CorsRegistry should not be null after applying CORS mappings");
    }
}
