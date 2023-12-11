package com.pratice.zephyr.repository;

import com.pratice.zephyr.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    @Query("{}")
    Flux<User> findAllPaginated(Pageable pageable);
}
