package br.com.forttiori.mongodb.service;


import br.com.forttiori.mongodb.model.StudentRequest;
import br.com.forttiori.mongodb.model.StudentResponse;
import br.com.forttiori.mongodb.persistence.entity.Students;
import br.com.forttiori.mongodb.persistence.repository.StudentRepository;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private ModelMapper mapper;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Students> find(Integer age){
        if(age == null) return this.studentRepository.findAll();
        return studentRepository.findAll().stream().filter(students -> students.getAge() == age).collect(Collectors.toList());

    }




    public StudentResponse getStudentsById(StudentRequest request, String id){
     var getById = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Estudante não existe"));
     return createResponse(getById);
    }

    public StudentResponse create(@Valid StudentRequest studentRequest){
            Students students1 = new Students();
            students1.setEmail(studentRequest.getEmail());
            students1.setAge(studentRequest.getAge());
            students1.setStartDate(studentRequest.getStartDate());
            students1.setName(studentRequest.getName());
            studentRepository.save(students1);
            return createResponse(students1);
    }

    // Coverte minha classe Estudante para uma "EstudanteDTO"

    public StudentResponse createResponse(Students students){
        StudentResponse response = new StudentResponse();
        response.setId(students.getId());
        response.setEmail(students.getEmail());
        response.setAge(students.getAge());
        response.setStartDate(students.getStartDate());
        response.setName(students.getName());

        return response;
    }


    public void deleteAll(List<String> ids){
        if(ids == null)studentRepository.deleteAll();
        studentRepository.deleteAllById(ids);
    }

    public StudentResponse update(String id, StudentRequest request){
        Optional<Students> studentsOptional = studentRepository.findById(id);

        var studentModel = studentsOptional.get();
        studentModel.setName(request.getName());
        studentModel.setSubjects(request.getSubjects());
        studentModel.setAge(request.getAge());
        studentModel.setEmail(request.getEmail());
        studentModel.setStartDate(request.getStartDate());
        studentRepository.save(studentModel);
        return createResponse(studentModel);
    }

}
