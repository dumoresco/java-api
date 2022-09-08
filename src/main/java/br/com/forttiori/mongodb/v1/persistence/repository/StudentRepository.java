package br.com.forttiori.mongodb.v1.persistence.repository;

import br.com.forttiori.mongodb.v1.model.Student.StudentResponse;
import br.com.forttiori.mongodb.v1.persistence.entity.Gender;
import br.com.forttiori.mongodb.v1.persistence.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<StudentEntity, String>, StudentCustomRepository {

//    List<StudentResponse> findByFirstNameContains(String name);
    List<StudentResponse> findAllByAgeIs(int age);
    List<StudentResponse> findAllByGender(Gender gender);

}
