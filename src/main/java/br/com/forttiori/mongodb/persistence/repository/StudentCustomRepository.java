package br.com.forttiori.mongodb.persistence.repository;

import br.com.forttiori.mongodb.persistence.entity.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCustomRepository {

    public List<StudentEntity> find(String firstName, String lastName);

}
