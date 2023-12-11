package com.pratice.zephyr.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;

public interface Rest<T> {

    Flux<T> getAll();

    Flux<T> getAllForPeriod(String document, LocalDate dtIni, LocalDate dtFim);

    Flux<T> getAllPaginated(int page, int size);

    Flux<T> getByParameters(Map<String, String> parameters);

    Mono<Boolean> existsById(String id);

    Mono<Long> count();

    Mono<T> getById(String id);

    Mono<T> getByName(String name);

    Mono<T> create(T model);

    Mono<T> update(String id, T model);

    Mono<Void> deleteById(String id);

}
