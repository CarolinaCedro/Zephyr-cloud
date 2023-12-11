package com.pratice.zephyr.controller;

import com.pratice.zephyr.service.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractRestController<T> implements RestController<T> {

    protected abstract Rest<T> getService();

    protected abstract Class<T> getEntityClass();

    @Override
    public Mono<ResponseEntity<T>> getById(@PathVariable String id) {
        return getService().getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<List<T>>> getAll() {
        return getService().getAll()
                .collectList()
                .map(ResponseEntity::ok);
    }


    @Override
    public Mono<ResponseEntity<T>> create(@RequestBody T model) {
        return getService().create(model)
                .map(savedModel -> ResponseEntity.ok().body(savedModel));
    }

    @Override
    public Mono<ResponseEntity<T>> update(@PathVariable String id, @RequestBody T model) {
        return getService().update(id, model)
                .map(updatedModel -> ResponseEntity.ok().body(updatedModel))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id) {
        return getService().deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Flux<ResponseEntity<List<T>>> getAllPaginated(@PathVariable int page, @PathVariable int size) {
        return getService().getAllPaginated(page, size)
                .collectList()
                .map(ResponseEntity::ok)
                .flux();
    }

    @Override
    public Mono<ResponseEntity<List<T>>> getAllPeriod(LocalDate dtIni, LocalDate dtFim) {
        String collectionName = this.getEntityClass().getSimpleName().toLowerCase();
        return getService().getAllForPeriod(collectionName, dtIni, dtFim)
                .collectList()
                .map(ResponseEntity::ok);
    }
}

