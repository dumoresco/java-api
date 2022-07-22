package br.com.forttiori.mongodb.controller;

import br.com.forttiori.mongodb.model.StudentRequest;
import br.com.forttiori.mongodb.model.StudentResponse;
import br.com.forttiori.mongodb.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody StudentRequest request){
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

}
