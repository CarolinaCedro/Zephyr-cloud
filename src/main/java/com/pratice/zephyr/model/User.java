package com.pratice.zephyr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
public class User {

    @Transient
    @JsonIgnore
    public static final String COLLECTION_USER = "user";


    @Id
    private String id;
    private String name;
    private String role;
    private String office;

    @CreatedDate
    private LocalDateTime createdAt;

    public User() {
        this.createdAt = LocalDateTime.now();
    }

    public User(String name, String role, String office) {
        this.name = name;
        this.role = role;
        this.office = office;
        this.createdAt = LocalDateTime.now();
    }

    public String getNameCollection() {
        return COLLECTION_USER;
    }
}
