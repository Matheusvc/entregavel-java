package br.com.techx.aula2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO de ENTRADA (Request): dados que o cliente envia no corpo (POST/PUT).
 * Nao expoe a Entity Aluno e nao recebe o "id" (gerado pelo banco).
 * Lombok gera getters, setters e construtores.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRequest {

    private String nome;
    private Integer ra;
    private Integer idade;
    private String curso;
}
