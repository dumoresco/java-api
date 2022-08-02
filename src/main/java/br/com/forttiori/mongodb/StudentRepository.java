package br.com.forttiori.mongodb;

import ch.qos.logback.core.net.server.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Students, String> {
}
