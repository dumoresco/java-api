package br.com.forttiori.mongodb.service;

import br.com.forttiori.mongodb.ApplicationConfigTest;
import br.com.forttiori.mongodb.exceptions.EntityNotFoundException;
import br.com.forttiori.mongodb.model.Student.StudentResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("StudentServiceTest")
@SpringBootTest
public class StudentServiceTest extends ApplicationConfigTest {



    @Autowired
    StudentService studentService;

    @Test
    @DisplayName("Get student by ID")
    public void getStudentByIdTest() throws EntityNotFoundException {
        StudentResponse student = studentService.getStudentsById("6307aba28496a44726e3dcd0");
        Assertions.assertEquals("6307aba28496a44726e3dcd0", student.getId());
    }


}
