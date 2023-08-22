package com.imaginnovative.test.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;

    }
    // @PostMapping("/createStudent")
    // public ResponseEntity<Student> createStudent (@RequestBody Student student){
    //     Student newStudent = studentService.createStudent(student);
    //     return new ResponseEntity<Student>(newStudent, null, 0);
    // }


    @PostMapping("/createStudent")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            Student newStudent = studentService.createStudent(student);
            return ResponseEntity.ok(newStudent);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
        }   
    }

    @PutMapping("/{id}/updateMarks")
    public ResponseEntity<Student> updateStudentMarks(
            @PathVariable Long id,
            @RequestParam Integer marks1,
            @RequestParam Integer marks2,
            @RequestParam Integer marks3) {
        Student updatedStudent = studentService.updateStudentMarks(id, marks1, marks2, marks3);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }
    
}
