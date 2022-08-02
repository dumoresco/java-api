package br.com.forttiori.mongodb.service;


import br.com.forttiori.mongodb.model.Students;
import br.com.forttiori.mongodb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Students> findAll(){
        return this.studentRepository.findAll();
    }

    public List<Integer> findStudentsByAge(int age){
        return this.findStudentsByAge(age).stream().map( s -> s.intValue() ).collect(Collectors.toList());
    }

    public Students getStudentsById(String id){

        return this.studentRepository
          .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não existe"));
    }

    public Students create(Students students){
        return studentRepository.save(students);
    }

    public ResponseEntity<Object> deleteById(String id){
        Optional<Students> studentsOptional = studentRepository.findById(id);

        if(studentsOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        studentRepository.delete(studentsOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted suceffuly");
    }

    public void deleteAllById(List<String> ids){

        studentRepository.deleteAllById(ids);
    }

    public ResponseEntity<Object> update(String id, Students students){
        Optional<Students> studentsOptional = studentRepository.findById(id);

        if(studentsOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
        var studentModel = studentsOptional.get();
        studentModel.setName(students.getName());
        studentModel.setSubjects(students.getSubjects());
        studentModel.setAge(students.getAge());
        studentModel.setBirthDate(students.getBirthDate());
        studentRepository.save(studentModel);
        return ResponseEntity.status(HttpStatus.OK).body("Student updated");
    }

}
