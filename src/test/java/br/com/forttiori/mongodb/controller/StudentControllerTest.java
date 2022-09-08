package br.com.forttiori.mongodb.controller;

import br.com.forttiori.mongodb.v1.controllers.StudentController;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest
public class StudentControllerTest {

    @Mock
    private StudentController studentController;

    @BeforeEach // vai ocorrer antes de cada teste
    public void setup(){
    }



}
