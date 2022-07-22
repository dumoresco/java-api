package br.com.forttiori.mongodb.persistence.repository;

import br.com.forttiori.mongodb.persistence.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {



}
