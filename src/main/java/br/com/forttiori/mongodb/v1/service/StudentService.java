package br.com.forttiori.mongodb.v1.service;

import br.com.forttiori.mongodb.v1.exceptions.EntityNotFoundException;
import br.com.forttiori.mongodb.v1.integration.AddressIntegration;
import br.com.forttiori.mongodb.v1.model.Student.StudentRequest;
import br.com.forttiori.mongodb.v1.model.Student.StudentResponse;
import br.com.forttiori.mongodb.v1.model.Student.mapper.RequestMapper;
import br.com.forttiori.mongodb.v1.model.Student.mapper.ResponseMapper;
import br.com.forttiori.mongodb.v1.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.v1.persistence.entity.StudentEntity;
import br.com.forttiori.mongodb.v1.persistence.entity.StudentQuery;
import br.com.forttiori.mongodb.v1.persistence.repository.StudentRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;


@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  AddressIntegration addressIntegration;


  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  // Refatorar para retornar StudentResponse
  public List<StudentResponse> getAll() {
      return studentRepository.findAll().stream().map(ResponseMapper::createResponse).toList();
  }
  public Page<StudentResponse> getStudentsByPage(Pageable pageable){
    return studentRepository.findAll(pageable).map(ResponseMapper::createResponse);
  }
  public StudentResponse getStudentsById(String id) {
    var getById =
        studentRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(" Id not found: " + id));
    return ResponseMapper.createResponse(getById);
  }
  public void createStudent(@Valid  StudentRequest studentRequest) {
    AddressEntity addressEntity = addressIntegration.consultaCep(studentRequest.getCep());

    StudentEntity students = RequestMapper.createEntity(studentRequest, addressEntity);

    studentRepository.save(students);
  }
  public List<StudentResponse> findStudentByName(@NotNull StudentQuery studentQuery){
    String firstName = studentQuery.getFirstName();
    String lastName = studentQuery.getLastName();


    return studentRepository.find(firstName, lastName).stream().map(ResponseMapper::createResponse).toList();
  }


  public void deleteAll(List<String> id) {
    if (id.isEmpty()) {
      studentRepository.deleteAll();
    } else {
      for (String i : id) {
        getStudentsById(i);
    }
      studentRepository.deleteAllById(id);
    }
  }
  // Método para atualizar um estudante
  public void updateStudent(String id, StudentRequest request) {
    StudentEntity oldStudentEntity =
        studentRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(" Id not found: " + id));

    StudentEntity newStudentEntity =
        RequestMapper.createEntity(request, addressIntegration.consultaCep(request.getCep()));

    newStudentEntity.setId(oldStudentEntity.getId());

    ResponseMapper.createResponse(studentRepository.save(newStudentEntity));


  }

  // Esse método vai buscar o cep na api e retornar a classe com os dados injetados

}
