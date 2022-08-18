package br.com.forttiori.mongodb.service;

import br.com.forttiori.mongodb.ApplicationConfigTest;
import br.com.forttiori.mongodb.persistence.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@DisplayName("StudentServiceTest")
public class StudentServiceTest extends ApplicationConfigTest {

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    StudentService studentService;

//    @Test
//    @DisplayName("Deve remover um estudante")
//    public void deveRemoverEstudante(){
//        List<String> id = Arrays.asList("id-mock");
//
//        Mockito.when(studentRepository.findBy)
//        studentService.deleteAll(id);
//    }

}
