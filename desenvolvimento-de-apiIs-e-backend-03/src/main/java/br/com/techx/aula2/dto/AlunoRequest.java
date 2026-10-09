package br.com.techx.aula2.dto;

/**
 * DTO de ENTRADA (Request).
 * Representa os dados que o cliente envia no corpo da requisicao (POST/PUT).
 * Nao expoe a Entity Aluno diretamente e nao recebe o "id" (gerado pelo banco).
 */
public class AlunoRequest {

    private String nome;
    private Integer ra;
    private Integer idade;
    private String curso;

    public AlunoRequest() {
    }

    public AlunoRequest(String nome, Integer ra, Integer idade, String curso) {
        this.nome = nome;
        this.ra = ra;
        this.idade = idade;
        this.curso = curso;
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
