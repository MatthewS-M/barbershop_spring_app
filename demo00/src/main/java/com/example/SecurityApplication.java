/**
 The SecurityApplication class is the entry point for the security application.
 This class uses Spring Boot to bootstrap the application.
 The main() method starts the application by calling SpringApplication.run()
 and passing in the SecurityApplication class and any command line arguments.
 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */

package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication {
/**
 This method is the entry point for the application.
 @param args An array of command-line arguments.
 */
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
}




