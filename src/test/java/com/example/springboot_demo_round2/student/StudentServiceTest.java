package com.example.springboot_demo_round2.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepository mockStudentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);


        jeff = new Student("Jeff", "jeff@gmail.com", LocalDate.of(2000, 12, 2));
        studentId = 1L;

    }

    Student jeff;

    Long studentId;

    @Test
    void shouldSaveStudentWhenValid() {

        when(mockStudentRepository.findStudentByEmail(jeff.getEmail())).thenReturn(Optional.empty());
        when(mockStudentRepository.save(any(Student.class))).thenReturn(jeff);
        when(mockStudentRepository.findAll()).thenReturn(List.of(jeff));

        studentService.saveStudent(jeff);

        List<Student> studentList = studentService.getStudents();

        verify(mockStudentRepository).save(jeff);

        assertThat(studentList).hasSize(1).usingRecursiveFieldByFieldElementComparator().containsExactly(jeff);
    }

    @Test
    void shouldNotSaveIfEmailAlreadyExists() {

        when(mockStudentRepository.findStudentByEmail(jeff.getEmail())).thenReturn(Optional.of(jeff));

        assertThatThrownBy(() -> studentService.saveStudent(jeff))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Student email already exists");

        verify(mockStudentRepository, never()).save(any(Student.class));
    }

    @Test
    void shouldNotSaveIfEmailInvalid() {

        Student invalidJeff = new Student("Jeff", "jeffgmail.com", LocalDate.of(2000, 12, 2));

        assertThatThrownBy(() -> studentService.saveStudent(invalidJeff))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Student email format is invalid");

        verify(mockStudentRepository, never()).save(any(Student.class));
    }

    @Test
    void shouldDeleteStudent() {

        when(mockStudentRepository.existsById(studentId)).thenReturn(true);

        studentService.delete(studentId);

        verify(mockStudentRepository).deleteById(studentId);
    }

    @Test
    void shouldNotDeleteStudentIfIdDoesNotExist() {

        when(mockStudentRepository.existsById(studentId)).thenReturn(false);

        assertThatThrownBy(() -> studentService.delete(studentId)).hasMessageContaining("can't delete as id:" + studentId + " does not exist");

        verify(mockStudentRepository, never()).deleteById(studentId);
    }

    @Test
    void shouldUpdateStudentNameAndEmail() {

        when(mockStudentRepository.findById(studentId)).thenReturn(Optional.of(jeff));

        studentService.updateStudent(studentId, "elis", "elis@gmail.com");

        assertThat(jeff.getName()).isEqualTo("elis");
        assertThat(jeff.getEmail()).isEqualTo("elis@gmail.com");
    }

    @Test
    void shouldNotUpdateStudentNameIfNameInvalid() {

        when(mockStudentRepository.findById(studentId)).thenReturn(Optional.of(jeff));

        studentService.updateStudent(studentId, "", "elis@gmail.com");

        assertThat(jeff.getName()).isEqualTo("Jeff");
        assertThat(jeff.getEmail()).isEqualTo("elis@gmail.com");
    }

    @Test
    void shouldNotUpdateStudentEmailIfEmailIsInvalid() {

        when(mockStudentRepository.findById(studentId)).thenReturn(Optional.of(jeff));

        studentService.updateStudent(studentId, null, "elisgmail.com");

        assertThat(jeff.getName()).isEqualTo("Jeff");
        assertThat(jeff.getEmail()).isEqualTo("jeff@gmail.com");
    }
}
