package com.example.springboot_demo_round2.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getStudents() {

        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {

        studentService.saveStudent(student);

    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudentById(@PathVariable Long studentId) {
        studentService.delete(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

}
