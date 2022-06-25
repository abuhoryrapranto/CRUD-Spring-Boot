package com.crud.student;

import com.sun.xml.txw2.IllegalSignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return  studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> emailExist = studentRepository.findStudentByEmail(student.getEmail());

        if(emailExist.isPresent()) {
            throw new IllegalStateException("Email Exist");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {

        boolean exists = studentRepository.existsById(id);

        if(!exists) {
            throw new IllegalStateException("Student not found!");
        }

        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String fullName, String email) {

        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("Student not found!"));

        if(fullName != null && fullName.length() > 0 && !Objects.equals(student.getFullName(), fullName)) {

            student.setFullName(fullName);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {

            Optional<Student> emailExist = studentRepository.findStudentByEmail(email);

            if(emailExist.isPresent()) {
                throw new IllegalStateException("Email Exist");
            }

            student.setEmail(email);
        }
    }
}
