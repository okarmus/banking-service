package org.okarmus.repository;

import org.okarmus.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mateusz on 17.11.16.
 */
public interface UserRepository extends MongoRepository<User, String> { //TODO there is not need to have so many methods
}
