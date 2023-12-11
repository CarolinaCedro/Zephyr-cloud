package com.pratice.zephyr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Colaborador {


    @Id
    private String id;
    private String nome;
    private String cargo;
    private Integer idade;
    private String endereco;
    private String email;
    private String telefone;

}




