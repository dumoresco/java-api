package br.com.forttiori.mongodb.controllers;

import br.com.forttiori.mongodb.model.Student.StudentRequest;
import br.com.forttiori.mongodb.model.Student.StudentResponse;
import br.com.forttiori.mongodb.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.service.StudentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
public class StudentController {


    private final StudentService studentService;

    // Get para retornar todos os estudantes ou por idade.
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<StudentResponse> getAll(@RequestParam(required = false, value = "age") Integer age,
                                        @RequestParam(required = false,value = "gender" ) String gender){
        return this.studentService.find(age,gender);
    }

    // Get passando uma rota para retornar um estudante pelo id
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public StudentResponse getStudentsById(@PathVariable String id){
        return studentService.getStudentsById(id);
    }

    // Post para criar um estudante
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody @Valid StudentRequest request) {
         studentService.createStudent(request);
    }

    // Delete para deletar um ou mais estudantes
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllByIds(@RequestParam(required = false ) List<String> id){
        this.studentService.deleteAll(id);
    }


    // Put para atualizar um estudante pelo id
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateStudent(@PathVariable String id, @RequestBody @Valid StudentRequest request){
       this.studentService.updateStudent(id, request);
    }




    // Cookies
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String cookie(HttpServletResponse response){
        //create a cookie with name 'website' and value 'javapointers'
        Cookie cookie = new Cookie("website", "javapointers");
        //set the expiration time
        //1 hour = 60 seconds x 60 minutes
        cookie.setMaxAge(60 * 60);
        //add the cookie to the  response
        response.addCookie(cookie);

        return "home";
    }

    @RequestMapping(value = "/cookie", method = RequestMethod.GET)
    public String readCookie(HttpServletRequest request){
        //get all cookies
        Cookie[] cookies = request.getCookies();
        //iterate each cookie
        for (Cookie cookie : cookies) {
            //display only the cookie with the name 'website'
            if (cookie.getName().equals("website")) {
                System.out.println(cookie.getValue());
            }
        }
        return "home";
    }

    @ResponseBody
    @GetMapping("/cep")
    public ResponseEntity<AddressEntity> consultaCep(@RequestParam(value = "q")String q){

        AddressEntity addressEntity =         studentService.consultaCep(q);



        return new ResponseEntity(addressEntity, HttpStatus.OK);

    }

}
