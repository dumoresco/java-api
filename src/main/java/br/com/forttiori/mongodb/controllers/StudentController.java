package br.com.forttiori.mongodb.controllers;

import br.com.forttiori.mongodb.model.Student.StudentRequest;
import br.com.forttiori.mongodb.model.Student.StudentResponse;
import br.com.forttiori.mongodb.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.persistence.entity.StudentEntity;
import br.com.forttiori.mongodb.persistence.entity.StudentQuery;
import br.com.forttiori.mongodb.service.StudentService;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("v1/students")
@AllArgsConstructor
public class StudentController {
    StudentService studentService;

    // Get para retornar todos os estudantes ou por idade.
    @GetMapping
    public List<StudentResponse> getAll( ){
        return studentService.getAll();
    }

    @GetMapping("/search")
    public List<StudentResponse> getStudentByAge(@RequestParam(value = "age", required = false) Integer age){
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("/pages")
    public Page<StudentResponse> getStudentsByPage(Pageable pageable){
        return studentService.getStudentsByPage(pageable);
    }


    @GetMapping("/{id}")
    public StudentResponse getStudentsById(@PathVariable String id){
        return studentService.getStudentsById(id);
    }

    @GetMapping("/filter")
    public List<StudentResponse> findStudentByName(@RequestBody StudentQuery studentQuery){
        return studentService.findStudentByName(studentQuery);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody @Valid StudentRequest request) {
         studentService.createStudent(request);
    }


    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllByIds(@RequestParam(required = false ) List<String> id){
        this.studentService.deleteAll(id);
    }


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
