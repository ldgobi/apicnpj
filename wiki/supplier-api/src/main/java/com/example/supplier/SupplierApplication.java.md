# Documentation: SupplierApplication.java

## Overview
The `SupplierApplication` class is the entry point for a Spring Boot application. It includes configuration for enabling Cross-Origin Resource Sharing (CORS) to allow requests from different origins. This class is designed to bootstrap the application and configure web-related settings.

---

## File Metadata
- **File Name**: `SupplierApplication.java`

---

## Class Details

### `SupplierApplication`
This class is annotated with `@SpringBootApplication`, which is a convenience annotation that combines:
- `@Configuration`: Marks the class as a source of bean definitions.
- `@EnableAutoConfiguration`: Enables Spring Boot's auto-configuration mechanism.
- `@ComponentScan`: Scans for components, configurations, and services in the package.

#### Methods

| Method Name         | Description                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| `main(String[] args)` | The main method that starts the Spring Boot application using `SpringApplication.run()`. |
| `corsConfigurer()`   | A bean method that configures CORS settings for the application.           |

---

## CORS Configuration

The `corsConfigurer()` method defines a `WebMvcConfigurer` bean to customize CORS settings. Below are the configurations applied:

| Configuration       | Value                                                                 |
|---------------------|-----------------------------------------------------------------------|
| **Mapping**         | `/**` (applies to all endpoints)                                      |
| **Allowed Origins** | `*` (allows requests from all origins)                                |
| **Allowed Methods** | `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`                            |
| **Allowed Headers** | `*` (allows all headers)                                              |

> **Note**: The `allowCredentials(true)` line is commented out, meaning credentials are not allowed in CORS requests by default.

---

## Insights

1. **Spring Boot Application**: The class leverages Spring Boot's auto-configuration and component scanning features to simplify application setup.
2. **CORS Configuration**: The application is configured to allow unrestricted cross-origin requests, which is useful for development but may require stricter rules in production for security purposes.
3. **Extensibility**: The `WebMvcConfigurer` bean can be extended further to include additional web-related configurations, such as interceptors or formatters.
4. **Scalability**: The use of `@SpringBootApplication` ensures that the application can scale easily by adding more components and configurations.

---
