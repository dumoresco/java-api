package br.com.forttiori.mongodb.v1.persistence.repository;

import br.com.forttiori.mongodb.v1.persistence.entity.StudentEntity;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;


import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Component
@AllArgsConstructor
public class StudentCustomRepositoryImpl implements StudentCustomRepository{


    private final MongoTemplate mongoTemplate;

    @Override
    public List<StudentEntity> find(String firstName, String lastName) {
        Query query = new Query();


        query.addCriteria(Criteria.where("firstName").regex(StringUtils.capitalize(firstName)).and("lastName").regex(StringUtils.capitalize(lastName)));
        return mongoTemplate.find(query, StudentEntity.class);

    }


}
