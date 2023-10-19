package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class TaxiManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxiManageSystemApplication.class, args);
    }

}
