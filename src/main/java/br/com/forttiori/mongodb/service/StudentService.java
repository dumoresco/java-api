package br.com.forttiori.mongodb.service;

import br.com.forttiori.mongodb.config.resttemplate.RestTemplateConfiguration;
import br.com.forttiori.mongodb.exceptions.EntityNotFoundException;
import br.com.forttiori.mongodb.model.Student.StudentRequest;
import br.com.forttiori.mongodb.model.Student.StudentResponse;
import br.com.forttiori.mongodb.model.Student.mapper.RequestMapper;
import br.com.forttiori.mongodb.model.Student.mapper.ResponseMapper;
import br.com.forttiori.mongodb.persistence.entity.AddressEntity;
import br.com.forttiori.mongodb.persistence.entity.StudentEntity;
import br.com.forttiori.mongodb.persistence.entity.StudentQuery;
import br.com.forttiori.mongodb.persistence.repository.StudentRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

  private final StudentRepository studentRepository;
  private final RestTemplateConfiguration restTemplateConfiguration;
  private final MongoTemplate mongoTemplate;

  public StudentService(StudentRepository studentRepository, RestTemplateConfiguration restTemplateConfiguration, MongoTemplate mongoTemplate) {
    this.studentRepository = studentRepository;
    this.restTemplateConfiguration = restTemplateConfiguration;
    this.mongoTemplate = mongoTemplate;
  }

  // Refatorar para retornar StudentResponse
  public List<StudentResponse> getAll() {
    return studentRepository.findAll().stream().map(ResponseMapper::createResponse).collect(Collectors.toList());
  }

  public Page<StudentResponse> getStudentsByPage(Pageable pageable){
    return studentRepository.findAll(pageable).map(ResponseMapper::createResponse);
  }

  public List<StudentResponse> getStudentsByAge(Integer age){
    Query query = new Query();
    query.addCriteria(Criteria.where("age").is(age));
    List<StudentEntity> students = mongoTemplate.find(query, StudentEntity.class);

    return students.stream()
            .map(ResponseMapper::createResponse)
            .collect(Collectors.toList());
  }

  public StudentResponse getStudentsById(String id) {
    var getById =
        studentRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(" Id not found: " + id));
    return ResponseMapper.createResponse(getById);
  }

  public void createStudent(@NotNull StudentRequest studentRequest) {
    AddressEntity addressEntity = consultaCep(studentRequest.getCep());

    StudentEntity students = RequestMapper.createEntity(studentRequest, addressEntity);
    studentRepository.save(students);
  }

  public List<StudentResponse> findStudentByName(StudentQuery studentQuery){
    String firstName = studentQuery.getFirstName();
    String lastName = studentQuery.getLastName();


    return studentRepository.find(firstName, lastName).stream().map(ResponseMapper::createResponse).collect(Collectors.toList());
  }

  // Método para deletar uma lista de ids ou caso não receber nenhum id deletar todo o banco;

  public void deleteAll(List<String> id) {
    if (id == null) {
      studentRepository.deleteAll();
    } else {
      for (String i : id) {
        studentRepository.deleteById(
            studentRepository
                .findById(i)
                .orElseThrow(
                    () -> new EntityNotFoundException("Id not found: " + i + " | ID not deleted!"))
                .getId());
      }
    }
  }

  // Método para atualizar um estudante
  public void updateStudent(String id, StudentRequest request) {


    StudentEntity oldStudentEntity =
        studentRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(" Id not found: " + id));

    StudentEntity newStudentEntity =
        RequestMapper.createEntity(request, consultaCep(request.getCep()));

    newStudentEntity.setId(oldStudentEntity.getId());

    ResponseMapper.createResponse(studentRepository.save(newStudentEntity));


  }

  // Esse método vai buscar o cep na api e retornar a classe com os dados injetados
  public AddressEntity consultaCep(String cep) {
    return restTemplateConfiguration.cepRestTemplate()
        .getForObject("/ws/" + cep + "/json/", AddressEntity.class);
  }
}
