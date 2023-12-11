package com.pratice.zephyr.utils.mongo;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MongoDBAggregationUtils {

    public static Aggregation createCustomAggregation(String nome) {
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.match(Criteria.where("nome").is(nome)));

        return Aggregation.newAggregation(operations);
    }

    public static Aggregation ccaFilterByRageDate(String document, LocalDate dataInicial, LocalDate dataFinal) {
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.match(Criteria.where("createdAt")
                .gte(dataInicial.atStartOfDay())
                .lt(dataFinal.plusDays(1).atStartOfDay())));
        return Aggregation.newAggregation(operations);
    }

    public static Aggregation ccaFilterByKeyword(String keyword) {
        List<AggregationOperation> operations = new ArrayList<>();

        if (StringUtils.hasText(keyword)) {
            operations.add(Aggregation.match(Criteria.where("name").is(keyword)));
        }
        return Aggregation.newAggregation(operations);
    }

}
