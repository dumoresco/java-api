package br.com.forttiori.mongodb.repository;

import br.com.forttiori.mongodb.model.Students;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Students, String> {

    Optional<Students> findStudentsByAgeGreaterThan(int age);
}
