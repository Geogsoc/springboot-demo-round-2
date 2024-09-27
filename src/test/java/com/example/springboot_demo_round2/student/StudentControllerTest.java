package com.example.springboot_demo_round2.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {
    @Mock
    private StudentService testStudentService;
    @InjectMocks
    private StudentController testStudentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void shouldGetStudents() {
        Student jeff = new Student("Jeffee", "j@gmail", LocalDate.of(2000, 12, 2));

        when(testStudentService.getStudents()).thenReturn(List.of(jeff));

        List<Student> students = testStudentController.getStudents();

        verify(testStudentService).getStudents();

        assertEquals(1, students.size());

        assertThat(students).usingRecursiveComparison().isEqualTo(List.of(jeff));
    }

    @Test
    void shouldRegisterStudent() {

        Student student = new Student("Jeff", "jeff@gmail.com", LocalDate.of(2000, 12, 2));

        testStudentController.registerNewStudent(student);

        verify(testStudentService).saveStudent(student);
    }

    @Test
    void shouldUpdateStudent() {

        testStudentController.updateStudent(1L, "Jeff", "jeff@hotmail.com");

        verify(testStudentService).updateStudent(1L, "Jeff", "jeff@hotmail.com");
    }

    @Test
    void shouldUpdateWithJustName() {

        testStudentController.updateStudent(1L, "Jeff", null);

        verify(testStudentService).updateStudent(1L, "Jeff", null);
    }

    @Test
    void shouldDeleteStudent() {

        testStudentController.deleteStudentById(1L);

        verify(testStudentService).delete(1L);
    }
}


