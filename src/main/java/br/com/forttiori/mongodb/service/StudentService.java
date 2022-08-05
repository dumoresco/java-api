package br.com.forttiori.mongodb.service;


import br.com.forttiori.mongodb.exceptions.EntityNotFoundException;
import br.com.forttiori.mongodb.model.StudentRequest;
import br.com.forttiori.mongodb.model.StudentResponse;
import br.com.forttiori.mongodb.model.mapper.RequestMapper;
import br.com.forttiori.mongodb.model.mapper.ResponseMapper;
import br.com.forttiori.mongodb.persistence.entity.Students;
import br.com.forttiori.mongodb.persistence.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    // Método para retornar todos os estudantes ou retornar por idade
    public List<StudentResponse> find(Integer age){

//        if(age == null) return this.studentRepository.findAll();
//        return studentRepository.findAll().stream().filter(students -> students.getAge().equals(age)).collect(Collectors.toList());
        if(age == null){
            return studentRepository.findAll().stream().map(ResponseMapper::createResponse).toList();
        }else{
            return studentRepository.findAll().stream().filter(s -> s.getAge().equals(age)).map(ResponseMapper::createResponse).toList();

        }
    }



    // Método para retornar um estudante pelo id
    public StudentResponse getStudentsById(String id){

     var getById = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" Id not found: " + id));

     return ResponseMapper.createResponse(getById);
    }


    // Método para criar um estudante
    public StudentResponse createStudent(StudentRequest studentRequest){
        Students students = RequestMapper.createEntity(studentRequest);
        studentRepository.save(students);

        return ResponseMapper.createResponse(students);
    }


    // Método para deletar uma lista de ids ou caso não receber nenhum id deletar todo o banco;
    public void deleteAll( List<String> id){

        if(id == null){
            studentRepository.deleteAll();
        }else{
            for ( String i: id ) {
                studentRepository.deleteById(studentRepository.findById(i).orElseThrow(() -> new EntityNotFoundException("Id not found: " + i)).getId());
            }
        }
    }



    // Método para atualizar um estudante
    public void updateStudent(String id, StudentRequest request){
        Students entity;
         entity = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" Id not found: " + id));

        entity.setName(request.getName());
        entity.setSubjects(request.getSubjects());
        entity.setAge(request.getAge());
        entity.setEmail(request.getEmail());

        studentRepository.save(entity);
    }

}
