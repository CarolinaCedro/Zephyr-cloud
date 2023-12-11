package com.pratice.zephyr.service.impl;

import com.pratice.zephyr.model.User;
import com.pratice.zephyr.repository.UserRepository;
import com.pratice.zephyr.utils.mongo.MongoDBAggregationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@Service
public class UserServiceImpl extends RestServiceImpl<User> {

    private final UserRepository repository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    private final MongoDBAggregationUtils aggregation = new MongoDBAggregationUtils();

    @Autowired
    public UserServiceImpl(ReactiveMongoTemplate reactiveMongoTemplate, UserRepository repository) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.repository = repository;
    }

    @Override
    protected ReactiveMongoRepository<User, String> getMongoTemplate() {
        return this.repository;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Mono<User> update(String id, User model) {
        // Verificar se o usuário existe antes de atualizar
        return repository.findById(id)
                .flatMap(existingUser -> {
                    if (existingUser != null) {
                        model.setId(existingUser.getId());
                        return repository.save(model);
                    } else {
                        return Mono.empty(); // Usuário não encontrado
                    }
                });
    }


    public Flux<User> getAllForPeriod(String collectionName, LocalDate dtIni, LocalDate dtFim) {
        Aggregation aggregation = MongoDBAggregationUtils.ccaFilterByRageDate(collectionName, dtIni, dtFim);
        return reactiveMongoTemplate.aggregate(aggregation, collectionName, User.class);
    }


    @Override
    public Flux<User> getAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAllPaginated(pageable);
    }

    @Override
    public Mono<User> getByName(String name) {
        return null;
    }

    public Flux<User> getByName(String collection, String name) {
        Aggregation aggregation1 = MongoDBAggregationUtils.ccaFilterByKeyword(name);
        return reactiveMongoTemplate.aggregate(aggregation1, collection, User.class);
    }
}
