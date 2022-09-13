package br.com.forttiori.mongodb.v1.persistence.repository;

import br.com.forttiori.mongodb.v1.persistence.entity.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCustomRepository {

     List<StudentEntity> find(String firstName, String lastName);

}
