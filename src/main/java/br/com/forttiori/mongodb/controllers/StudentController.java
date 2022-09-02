package br.com.forttiori.mongodb.controllers;

import br.com.forttiori.mongodb.model.Student.StudentRequest;
import br.com.forttiori.mongodb.model.Student.StudentResponse;
import br.com.forttiori.mongodb.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.persistence.entity.StudentEntity;
import br.com.forttiori.mongodb.persistence.entity.StudentQuery;
import br.com.forttiori.mongodb.service.StudentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @GetMapping
    @ApiOperation(value = "Get all students", notes= "Get a list of all students", response = StudentResponse.class)
    public List<StudentResponse> getAll(){
        return studentService.getAll();
    }

    @GetMapping("/pages" )
    @ApiIgnore
    public Page<StudentResponse> getStudentsByPage(Pageable pageable){
        return studentService.getStudentsByPage(pageable);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Get by id", notes = "Get student by id", response = StudentResponse.class)
    public StudentResponse getStudentsById(
            @ApiParam(name = "id", value = "List of Student by ID", example = "62f0f6b3973cb841c7817ab1")
            @PathVariable String id){
        return studentService.getStudentsById(id);
    }

    @GetMapping("/filter")
    @ApiOperation(value = "Filter student by first name or last name", notes = "Get all students by filter")
    public List<StudentResponse> findStudentByName(@RequestBody @ApiParam(name = "Student query body", value = "a JSON representing the query filter") StudentQuery studentQuery){
        return studentService.findStudentByName(studentQuery);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Create student", notes = "Create a new student")
    public void create(@RequestBody @Valid @ApiParam(name = "Student body", value = "a JSON representing the students with the respective fields") StudentRequest request) {
         studentService.createStudent(request);
    }


    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all students or by a list of ids")
    public void deleteAllByIds(
            @RequestParam(required = false )
            @ApiParam(name = "id", required = false, value = "Delete students by id" ) List<String> id){
        this.studentService.deleteAll(id);
    }


    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ApiOperation(value = "Update student", notes = "Update student by id")
    public void updateStudent(@PathVariable String id, @RequestBody @Valid StudentRequest request){
       this.studentService.updateStudent(id, request);
    }




    // Cookies
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiIgnore
    public String cookie(  HttpServletResponse response){
        //create a cookie with name 'website' and value 'javapointers'
        Cookie cookie = new Cookie("website", "javapointers");
        //set the expiration time

        cookie.setMaxAge(60 * 60);

        //add the cookie to the  response
        response.addCookie(cookie);

        return "home";
    }

    @RequestMapping(value = "/cookie", method = RequestMethod.GET)
    @ApiIgnore
    public String readCookie(  HttpServletRequest request){
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
    @ApiIgnore()
    public ResponseEntity<AddressEntity> consultaCep( @RequestParam(value = "q") String q){

        AddressEntity addressEntity =         studentService.consultaCep(q);



        return new ResponseEntity<AddressEntity>(addressEntity, HttpStatus.OK);

    }

}
