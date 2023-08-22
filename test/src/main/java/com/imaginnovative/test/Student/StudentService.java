package com.imaginnovative.test.Student;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ValidationException;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
  
    public Student createStudent(Student student){
        validateStudent(student);
        int totalMarks = student.getMarks1() + student.getMarks2() + student.getMarks3();
        double averageMarks = totalMarks / 3.0;
        String result = (student.getMarks1() >= 35 && student.getMarks3() >= 35 &&
                         student.getMarks2() >= 35) ? "Pass" : "Fail";
        student.setTotal(totalMarks);
        student.setAverage(averageMarks);
        student.setResult(result);
        return studentRepository.save(student);
    }
    private void validateStudent(Student student) {
        if (student.getFirstName() != null && student.getFirstName().length() < 3) {
            throw new ValidationException("min 3 chars required");
        }
        if (student.getLastName() != null && student.getLastName().length() < 3) {
            throw new ValidationException("min 3 chars required");
        }
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(student.getDob(), currentDate);
        if (age.getYears() <= 15 || age.getYears() > 20) {
            throw new ValidationException("Age should be greater than 15 years and less than or equal to 20 years");
        }
        if (student.getMarks1() < 0 || student.getMarks1() > 100 ||
            student.getMarks2() < 0 || student.getMarks2() > 100 ||
            student.getMarks3() < 0 || student.getMarks3() > 100) {
            throw new ValidationException("Marks should be in the range of 0 to 100");
        }
        if (!"A".equals(student.getSection()) && !"B".equals(student.getSection()) &&
            !"C".equals(student.getSection())) {
            throw new ValidationException("Valid values for section are A, B, and C");
        }

        if (!"M".equals(student.getGender()) && !"F".equals(student.getGender())) {
            throw new ValidationException("Valid values for gender are M and F");
        }
    }
    public Student updateStudentMarks(Long id, Integer marks1, Integer marks2, Integer marks3) {
        Student student = getStudentById(id);

        student.setMarks1(marks1);
        student.setMarks2(marks2);
        student.setMarks3(marks3);

        int totalMarks = marks1 + marks2 + marks3;
        double average = totalMarks / 3.0;
        String result = totalMarks >= 105 && average >= 35 && marks1 >= 35 && marks2 >= 35 && marks3 >= 35 ? "Pass" : "Fail";  

        student.setTotal(totalMarks);
        student.setAverage(average);
        student.setResult(result);

        return studentRepository.save(student);
    }
    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow();
                     
    }
}
