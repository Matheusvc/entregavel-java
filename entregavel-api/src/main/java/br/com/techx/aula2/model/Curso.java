package br.com.techx.aula2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade JPA que representa a tabela "curso" no PostgreSQL.
 * O Hibernate cria/atualiza esta tabela automaticamente (ddl-auto=update).
 *
 * Segue o mesmo padrao da entidade Aluno.
 */
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String turno;

    // Construtor vazio exigido pelo JPA/Hibernate
    public Curso() {
    }

    public Curso(String descricao, String turno) {
        this.descricao = descricao;
        this.turno = turno;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
