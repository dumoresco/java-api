package br.com.forttiori.mongodb.service;

import br.com.forttiori.mongodb.ApplicationConfigTest;

import br.com.forttiori.mongodb.util.StudentCreator;
import br.com.forttiori.mongodb.v1.config.resttemplate.RestTemplateConfiguration;
import br.com.forttiori.mongodb.v1.model.Student.StudentResponse;
import br.com.forttiori.mongodb.v1.persistence.entity.AddressEntity;

import br.com.forttiori.mongodb.v1.persistence.entity.StudentQuery;
import br.com.forttiori.mongodb.v1.persistence.repository.StudentRepository;
import br.com.forttiori.mongodb.v1.service.StudentService;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;



@DisplayName("StudentServiceTest")
public class StudentServiceTest extends ApplicationConfigTest {


    @InjectMocks //Quando quer testar a classe em si, por exemplo a studentService
    StudentService studentService ;

    @Mock //Mock do comportamento, vamos definir o comportamento do studentService
    StudentRepository studentRepository;

        @Mock
        RestTemplate restTemplate;
    @Mock
    RestTemplateConfiguration restTemplateConfiguration;

    @Test
    @DisplayName("Deve criar um estudante")
    public void shouldCreateAnStudent(){

        // Given
        Mockito.when(studentRepository.save(Mockito.any())).thenReturn(StudentCreator.studentEntityStub());
        Mockito.when(restTemplate.getForObject("/ws/91740840/json/", AddressEntity.class)).thenReturn(StudentCreator.addressEntityStub());

        // When
         studentService.createStudent(StudentCreator.studentRequestStub());

        // Then
        Assertions.assertEquals("Eduardo", StudentCreator.studentEntityStub().getFirstName());

    }

    @Test
    @DisplayName("Deve retornar uma lista de estudantes")
    public void shouldReturnListOfStudents(){

        // Given
        Mockito.when(studentRepository.findAll()).thenReturn(List.of(StudentCreator.studentEntityStub(), StudentCreator.studentEntityStub2(), StudentCreator.studentEntityStub1()));

        // When
        List<StudentResponse> studentResponseList = studentService.getAll();

        // Then
        Assertions.assertNotNull(studentResponseList);
        Assertions.assertEquals(3, studentResponseList.size());
        Assertions.assertEquals(StudentResponse.class, studentResponseList.get(0).getClass());

    }

    @Test
    @DisplayName("Deve buscar um estudante pelo id e retorna-lo")
    public void shouldReceiveAnIdThenReturnCorrespondentUser(){

        // Given
        Mockito.when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(StudentCreator.studentEntityStub()));
        // When
        StudentResponse studentResponse = studentService.getStudentsById("id");
        // Then
        Assertions.assertNotNull(studentResponse);
        Assertions.assertEquals(StudentResponse.class, studentResponse.getClass());
        Assertions.assertEquals(StudentCreator.studentEntityStub().getId(), studentResponse.getId());
    }
    @Test
    @DisplayName("Deve deletar a lista de usuários(Ids) especificadas.")
    void shouldReceiveAListOfIdsAndDeleteSpecifiedUsers() {
//      GIVEN
        Mockito.when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable((StudentCreator.studentEntityStub())));
        Mockito.doNothing().when(studentRepository).deleteAll(Mockito.anyList());
//      WHEN
        studentService.deleteAll(List.of(StudentCreator.studentEntityStub().getId(),StudentCreator.studentEntityStub2().getId()));
//      THEN
        Mockito.verify(studentRepository, Mockito.times(1)).deleteAllById(List.of(StudentCreator.studentEntityStub().getId(), StudentCreator.studentEntityStub2().getId()));
    }

    @Test
    @DisplayName("Deve deletar todos os estudantes")
    void shouldReceiveAnEmptyListAndDeleteAllStudents() {
//      GIVEN
        Mockito.doNothing().when(studentRepository).deleteAll();
//      WHEN
        studentService.deleteAll(List.of());
//      THEN
        Mockito.verify(studentRepository, Mockito.times(1)).deleteAll();
    }
    @Test
    @DisplayName("Deve fazer o update do usuário especificado.")
    void shoulUpdateAnSpecifiedUser() {
//      GIVEN
        Mockito.when(studentRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(StudentCreator.studentEntityStub()));
        Mockito.when(restTemplate.getForObject("/ws/91740840/json/", AddressEntity.class)).thenReturn(StudentCreator.addressEntityStub());
        Mockito.when(studentRepository.save(Mockito.any())).thenReturn(StudentCreator.studentEntityStub());



//      WHEN
        studentService.updateStudent(StudentCreator.studentEntityStub().getId(), StudentCreator.studentRequestStub());
//      THEN
        Assertions.assertNotNull(StudentCreator.studentEntityStub());


    }

    @Test
    @DisplayName("Deve retornar lista de estudantes filtrados")
    public void ShoudReturnAnListOfStudentsFiltered(){

        StudentQuery studentQuery = new StudentQuery();

        Mockito.when(studentRepository.find(studentQuery.getFirstName(), studentQuery.getLastName())).thenReturn(List.of(StudentCreator.studentEntityStub1(), StudentCreator.studentEntityStub2()));
        studentService.findStudentByName(studentQuery);
        Assertions.assertEquals(studentQuery.getFirstName(), "");
    }
}
