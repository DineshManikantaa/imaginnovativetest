package com.imaginnovative.test.Student;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String firstName;

    @NotBlank
    @Size(min = 3)
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Past
    private LocalDate dob;

    @Pattern(regexp = "[A-C]", message = "Valid values are A, B, or C")
    private String section;

    @Pattern(regexp = "[MF]", message = "Valid values are M or F")
    private String gender;

    @Min(0)
    @Max(100)
    private Integer marks1;

    @Min(0)
    @Max(100)
    private Integer marks2;

    @Min(0)
    @Max(100)
    private Integer marks3;

    private Integer total;
    private Double average;
    private String result;
    
}
