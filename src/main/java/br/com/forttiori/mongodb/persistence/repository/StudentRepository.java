package br.com.forttiori.mongodb.persistence.repository;

import br.com.forttiori.mongodb.model.StudentResponse;
import br.com.forttiori.mongodb.persistence.entity.Gender;
import br.com.forttiori.mongodb.persistence.entity.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Students, String> {

    List<StudentResponse> findAllByAgeIs(int age);
    List<StudentResponse> findAllByGender(String gender);

}
