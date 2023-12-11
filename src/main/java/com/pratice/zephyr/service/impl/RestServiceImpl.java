package com.pratice.zephyr.service.impl;

import com.pratice.zephyr.service.Rest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public abstract class RestServiceImpl<T> implements Rest<T> {

    protected abstract ReactiveMongoRepository<T, String> getMongoTemplate();

    protected abstract Class<T> getEntityClass();


    @Override
    public Flux<T> getAll() {
        return this.getMongoTemplate().findAll();
    }

    //Fazer metodo com a paginação

    @Override
    public Flux<T> getAllPaginated(int page, int size) {
        return null;
    }


    @Override
    public Flux<T> getByParameters(Map<String, String> parameters) {
        return null;
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return this.getMongoTemplate().existsById(id);
    }

    @Override
    public Mono<Long> count() {
        return this.getMongoTemplate().count();
    }

    @Override
    public Mono<T> getById(String id) {
        return this.getMongoTemplate().findById(id);
    }

    @Override
    public Mono<T> create(T model) {
        return this.getMongoTemplate().save(model);
    }

    @Override
    public Mono<T> update(String id, T model) {
        return null;
    }


    @Override
    public Mono<Void> deleteById(String id) {
        return this.getMongoTemplate().deleteById(id);
    }
}
