package br.com.forttiori.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class Controller {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Students> getAll(){

        return this.studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Students getStudentsById(@PathVariable String id){

        return this.studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não existe"));
    }

    @PostMapping
    public Students create(@RequestBody Students students){
        return studentRepository.save(students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id){
        Optional<Students> studentsOptional = studentRepository.findById(id);

        if(!studentsOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        studentRepository.delete(studentsOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted suceffuly");
    }

    @DeleteMapping
    public void deleteAllById(@RequestParam List<String> ids){

            studentRepository.deleteAllById(ids);


    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable String id, @RequestBody Students students){
        Optional<Students> studentsOptional = studentRepository.findById(id);

        if(!studentsOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        var studentModel = studentsOptional.get();
        studentModel.setName(students.getName());
        studentRepository.save(studentModel);
        return ResponseEntity.status(HttpStatus.OK).body("Student updated");
    }


}
