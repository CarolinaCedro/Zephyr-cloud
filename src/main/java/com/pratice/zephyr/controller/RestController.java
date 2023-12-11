package com.pratice.zephyr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface RestController<T> {

    @GetMapping("/{id}")
    Mono<ResponseEntity<T>> getById(@PathVariable String id);

    @GetMapping
    Mono<ResponseEntity<List<T>>> getAll();

    @PostMapping
    Mono<ResponseEntity<T>> create(@RequestBody T model);

    @GetMapping("/period")
    Mono<ResponseEntity<List<T>>> getAllPeriod(@RequestParam LocalDate dtIni, @RequestParam LocalDate dtFim);


    @PutMapping("/{id}")
    Mono<ResponseEntity<T>> update(@PathVariable String id, @RequestBody T model);

    @DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> deleteById(@PathVariable String id);

    @GetMapping("/paginated")
    Flux<ResponseEntity<List<T>>> getAllPaginated(@RequestParam int page, @RequestParam int size);
}
