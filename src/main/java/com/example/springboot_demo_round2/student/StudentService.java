package com.example.springboot_demo_round2.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public void saveStudent(Student student) {

        Optional<Student> maybeStudent = studentRepository.findStudentByEmail(student.getEmail());

        if (maybeStudent.isPresent()) {
            throw new IllegalStateException("Student email already exists");
        }
        if (!isValidEmail(student.getEmail())) {

            throw new IllegalStateException("Student email format is invalid");
        }

        studentRepository.save(student);


        System.out.println("saving   " + student);
    }


    public void delete(Long studentId) {
        Boolean studentExists = studentRepository.existsById(studentId);

        if (!studentExists) {
            throw new IllegalStateException("can't delete as id:" + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {

        Optional<Student> maybeStudent = studentRepository.findById(id);

        if (maybeStudent.isEmpty()) {
            throw new IllegalStateException("can't update as id:" + id + " does not exist");
        }

        Student existingStudent = maybeStudent.get();

        if (name != null && name.length() > 0 && !Objects.equals(name, existingStudent.getName())) {

            existingStudent.setName(name);
            System.out.println("updating name");
        }
        if (isValidEmail(email)) {

            existingStudent.setEmail(email);
            System.out.println("updating email");
        }
    }

    Boolean isValidEmail(String email) {

        final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

}

