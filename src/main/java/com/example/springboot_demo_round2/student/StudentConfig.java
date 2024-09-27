package com.example.springboot_demo_round2.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {

        return args -> {
            Student jeff = new Student("Jeff", "j@gmail", LocalDate.of(2000, 12, 2));


            Student brian = new Student("Brian", "b@gmail", LocalDate.of(1990, 1, 23));

            repository.saveAll(List.of(jeff, brian));
        };


    }
}
