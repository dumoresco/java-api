package br.com.forttiori.mongodb.controller;

import br.com.forttiori.mongodb.controllers.StudentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
