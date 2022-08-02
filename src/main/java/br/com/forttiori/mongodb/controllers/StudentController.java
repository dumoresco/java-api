package br.com.forttiori.mongodb.controllers;

import br.com.forttiori.mongodb.model.Students;
import br.com.forttiori.mongodb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Students> getAll(){

        return this.studentService.findAll();
    }

    @GetMapping("/")
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

    @GetMapping("/filter")
    public List<Students> getStudentsByAge(@RequestParam("age") int age){
        return studentService.findAll().stream().filter(students -> students.getAge() == age).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Students getStudentsById(@PathVariable String id){
        return studentService.getStudentsById(id);

    }

    @PostMapping
    public Students create(@RequestBody Students students){
        return studentService.create(students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id){
        return this.studentService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllById(@RequestParam List<String> ids){
            this.studentService.deleteAllById(ids);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable String id, @RequestBody Students students){
       return this.studentService.update(id, students);
    }


}
