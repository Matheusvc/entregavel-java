package br.com.techx.aula2.repository;

import br.com.techx.aula2.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository do Aluno.
 * Ao estender JpaRepository, herda automaticamente os métodos de banco de dados
 * (save, findAll, findById, deleteById, etc.), sem precisar escrever SQL.
 *
 * <Aluno, Long> = entidade gerenciada e o tipo da chave primária (id).
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    // Query method: o Spring Data implementa sozinho a busca por nome.
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
