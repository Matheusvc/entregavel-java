package br.com.techx.aula2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO de SAIDA (Response): dados que a API devolve ao cliente, incluindo o "id".
 * Isola a camada de exposicao (API) da camada de persistencia (Entity).
 * Lombok gera getters, setters e construtores (o @AllArgsConstructor cria o
 * construtor com id, nome, ra, idade, curso).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponse {

    private Long id;
    private String nome;
    private Integer ra;
    private Integer idade;
    private String curso;
}
