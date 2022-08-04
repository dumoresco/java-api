package br.com.forttiori.mongodb.service;


import br.com.forttiori.mongodb.exceptions.EntityNotFoundException;
import br.com.forttiori.mongodb.model.StudentRequest;
import br.com.forttiori.mongodb.model.StudentResponse;
import br.com.forttiori.mongodb.persistence.entity.Students;
import br.com.forttiori.mongodb.persistence.repository.StudentRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final ModelMapper mapper;

    public StudentService(StudentRepository studentRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    public List<Students> find(Integer age){
        if(age == null) return this.studentRepository.findAll();
        return studentRepository.findAll().stream().filter(students -> students.getAge().equals(age)).collect(Collectors.toList());

    }




    public StudentResponse getStudentsById(String id){
     var getById = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" Id not found: " + id));
     return createResponse(getById);
    }

    public StudentResponse createStudent(StudentRequest studentRequest){
            return createResponse(studentRepository.save(createRequest(studentRequest)));
    }

    // Coverte minha classe Estudante para uma "EstudanteDTO"
    public StudentResponse createResponse(Students students){
        return mapper.map(students, StudentResponse.class);
    }
    // Coverte EstudanteDTO para classe Estudante
    public Students createRequest(StudentRequest request){
        return mapper.map(request, Students.class);
    }


    public void deleteAll(@Validated List<String> id){
        if(id == null) {
            studentRepository.deleteAll();
        }else {
            for (String i: id) {
                    studentRepository.findById(i).orElseThrow(() -> new EntityNotFoundException("Id not found: " + i));
                studentRepository.deleteById(i);
            }
        };
    }

    public StudentResponse update(String id, StudentRequest request){
        Optional<Students> studentsOptional = Optional.ofNullable(studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" Id not found: " + id)));

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
