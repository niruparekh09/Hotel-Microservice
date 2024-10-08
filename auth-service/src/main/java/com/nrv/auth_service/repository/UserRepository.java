package com.nrv.auth_service.repository;

import com.nrv.auth_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
