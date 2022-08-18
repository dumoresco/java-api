package br.com.forttiori.mongodb.service;


import br.com.forttiori.mongodb.exceptions.EntityNotFoundException;
import br.com.forttiori.mongodb.model.Student.StudentRequest;
import br.com.forttiori.mongodb.model.Student.StudentResponse;
import br.com.forttiori.mongodb.model.Student.mapper.RequestMapper;
import br.com.forttiori.mongodb.model.Student.mapper.ResponseMapper;
import br.com.forttiori.mongodb.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.persistence.entity.StudentEntity;
import br.com.forttiori.mongodb.persistence.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    // Método para retornar todos os estudantes ou retornar por idade
    public List<StudentResponse> find(Integer age, String gender){
        if(age == null & gender == null){
            return studentRepository.findAll().stream().map(ResponseMapper::createResponse).collect(Collectors.toList());
        }else if(gender == null){
            return studentRepository.findAllByAgeIs(age);
        }else{
            return studentRepository.findAllByGender(gender.toUpperCase(Locale.ROOT)).stream().collect(Collectors.toList());
        }
    }



    // Método para retornar um estudante pelo id
    public StudentResponse getStudentsById(String id){

     var getById = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" Id not found: " + id ));

     return ResponseMapper.createResponse(getById);
    }


    // Método para criar um estudante
    public void createStudent(StudentRequest studentRequest){
        AddressEntity addressEntity = consultaCep(studentRequest.getCep());

        StudentEntity students = RequestMapper.createEntity(studentRequest,addressEntity);
        studentRepository.save(students);
//        return ResponseMapper.createResponse(students);
    }


    // Método para deletar uma lista de ids ou caso não receber nenhum id deletar todo o banco;
    public void deleteAll( List<String> id){
        if(id == null){
            studentRepository.deleteAll();
        }else{
            for ( String i: id ) {
                studentRepository.deleteById(studentRepository.findById(i).orElseThrow(() -> new EntityNotFoundException("Id not found: " + i +  " | ID not deleted!")).getId());
            }
        }
    }



    // Método para atualizar um estudante
    public void updateStudent(String id, StudentRequest request){


        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" Id not found: " + id));

        studentEntity.setFirstName(request.getFirstName());
        studentEntity.setLastName(request.getLastName());
        studentEntity.setSubjects(request.getSubjects());
        studentEntity.setAge(request.getAge());
        studentEntity.setAddress(consultaCep(request.getCep()));
        studentEntity.setGender(request.getGender());
        studentEntity.setEmail(request.getEmail());

        studentRepository.save(studentEntity);
    }


    // Esse método vai buscar o cep na api e retornar a classe com os dados injetados
    public AddressEntity consultaCep( String cep){
        return new RestTemplate().getForObject("https://viacep.com.br/ws/"+cep+"/json/", AddressEntity.class);

    }

}
