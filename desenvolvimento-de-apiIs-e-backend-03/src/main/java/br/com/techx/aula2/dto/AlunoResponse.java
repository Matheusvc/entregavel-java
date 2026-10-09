package br.com.techx.aula2.dto;

/**
 * DTO de SAIDA (Response).
 * Representa os dados que a API devolve ao cliente, incluindo o "id".
 * Isola a camada de exposicao (API) da camada de persistencia (Entity Aluno).
 */
public class AlunoResponse {

    private Long id;
    private String nome;
    private Integer ra;
    private Integer idade;
    private String curso;

    public AlunoResponse() {
    }

    public AlunoResponse(Long id, String nome, Integer ra, Integer idade, String curso) {
        this.id = id;
        this.nome = nome;
        this.ra = ra;
        this.idade = idade;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getRa() {
        return ra;
    }

    public void setRa(Integer ra) {
        this.ra = ra;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
