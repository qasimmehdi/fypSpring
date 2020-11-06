package com.example.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
@EnableScheduling
public class CryptoApplication {


    public static void main(String[] args) {

        SpringApplication.run(CryptoApplication.class, args);
    }

}
