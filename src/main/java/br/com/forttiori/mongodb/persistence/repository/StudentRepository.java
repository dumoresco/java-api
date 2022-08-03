package br.com.forttiori.mongodb.persistence.repository;

import br.com.forttiori.mongodb.persistence.entity.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Students, String> {

    Optional<Students> findStudentsByAgeGreaterThan(int age);
    Optional<Students> findAllByAgeIs(int age);

}
