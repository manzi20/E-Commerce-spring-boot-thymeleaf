package com.example.testing;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "E-Commerce API", version = "1.1.0", description = "E-Commerce in a simple way", license = @License(name = "Tell:+250781773034", url = "https://www.orionsystems.co.rw/"), contact = @Contact(url = "https://manzipaci4@gmail.com/", name = "Pro_Product", email = "ask@Proproduct.com")))
public class TestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingApplication.class, args);
    }

}
