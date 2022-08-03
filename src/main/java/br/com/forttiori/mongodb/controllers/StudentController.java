package br.com.forttiori.mongodb.controllers;

import br.com.forttiori.mongodb.model.StudentRequest;
import br.com.forttiori.mongodb.model.StudentResponse;
import br.com.forttiori.mongodb.persistence.entity.Students;
import br.com.forttiori.mongodb.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Students> getAll(@RequestParam(required = false, value = "age") Integer age){
        return this.studentService.find(age);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String cookie(HttpServletResponse response){
        //create a cookie with name 'website' and value 'javapointers'
        Cookie cookie = new Cookie("website", "javapointers");
        //set the expiration time
        //1 hour = 60 seconds x 60 minutes
        cookie.setMaxAge(60 * 60);
        //add the cookie to the  response
        response.addCookie(cookie);
        //return the jsp with the response
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

    @GetMapping("/{id}")
    public StudentResponse getStudentsById(@PathVariable String id, StudentRequest request){
        return studentService.getStudentsById(request, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse create(@RequestBody @Valid StudentRequest request){
        return studentService.create(request);
    }


    @DeleteMapping
    public void deleteAllById(@RequestParam(required = false, value = "ids" ) List<String> ids){
        this.studentService.deleteAll(ids);
    }


    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public StudentResponse updateStudent(@PathVariable String id, @RequestBody @Valid StudentRequest request){
        return this.studentService.update(id, request);
    }


}
