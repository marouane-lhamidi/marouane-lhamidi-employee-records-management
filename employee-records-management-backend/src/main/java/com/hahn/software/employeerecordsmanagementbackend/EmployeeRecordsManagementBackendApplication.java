package com.hahn.software.employeerecordsmanagementbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Employee Records Management Backend",
				description = "A back-end of an Employee Records Management System",
				version = "1.0",
				contact = @Contact(
						name = "Marouane LHAMIDI",
						email = "marouane.lhamidi1@gmail.com",
						url = "https://www.lhamidi-marouane.com/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		)
)
public class EmployeeRecordsManagementBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRecordsManagementBackendApplication.class, args);
	}

}
