package com.crud.student;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private Long id;
    private String fullName;
    private String email;
    private LocalDate dob;

    public Student() {

    }

    public Student(Long id, String fullName, String email, LocalDate dob) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dob = dob;
    }

    public Student(String fullName, String email, LocalDate dob) {
        this.fullName = fullName;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
