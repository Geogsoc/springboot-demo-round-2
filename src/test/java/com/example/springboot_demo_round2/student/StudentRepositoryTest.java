package com.example.springboot_demo_round2.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    Student studentTest;

    @BeforeEach
    void setUp() {
        studentTest = new Student("Elis", "elis@email.com", LocalDate.of(1990, 12, 19));
        studentRepository.save(studentTest);
    }

    @Test
    void shouldFindStudentByEmail() {

        Optional<Student> maybeStudent = studentRepository.findStudentByEmail("elis@email.com");

        assertThat(maybeStudent).isPresent().hasValueSatisfying(student1 ->
                assertThat(student1).usingRecursiveComparison().isEqualTo(studentTest));
    }

    @Test
    void shouldReturnNullIfEmailNotFound() {
        Optional<Student> maybeStudent = studentRepository.findStudentByEmail("emaildoesntexist@email.com");

        assertThat(maybeStudent).isEmpty();

    }
}