package br.com.techx.aula2.service;

import br.com.techx.aula2.dto.AlunoRequest;
import br.com.techx.aula2.model.Aluno;
import br.com.techx.aula2.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de regras de negocio.
 * Injecao de dependencia via construtor (o Spring injeta o AlunoRepository).
 */
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public List<Aluno> buscarPorNome(String nome) {
        return alunoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    /**
     * Atualiza um aluno existente.
     * Usa findById(id).orElseThrow(...) para buscar e, em uma unica linha,
     * lancar excecao automaticamente caso o ID nao exista.
     */
    public void update(Long id, AlunoRequest request) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno nao encontrado com id " + id));

        aluno.setNome(request.getNome());
        aluno.setRa(request.getRa());
        aluno.setIdade(request.getIdade());
        aluno.setCurso(request.getCurso());

        alunoRepository.save(aluno);
    }

    /**
     * Remove um aluno pelo ID.
     * Verifica a existencia com existsById antes de remover (sem lambda no if).
     */
    public void deleteById(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new RuntimeException("Aluno nao encontrado com id " + id);
        }
        alunoRepository.deleteById(id);
    }
}
