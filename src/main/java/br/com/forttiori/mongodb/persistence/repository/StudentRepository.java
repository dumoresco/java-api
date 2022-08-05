package br.com.forttiori.mongodb.persistence.repository;

import br.com.forttiori.mongodb.model.StudentResponse;
import br.com.forttiori.mongodb.persistence.entity.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Students, String> {

    List<StudentResponse> findAllByAgeIs(int age);
    List<StudentResponse>  findAllByNameContaining(String name);

}
