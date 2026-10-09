package br.com.techx.aula2.service;

import br.com.techx.aula2.model.Aluno;
import br.com.techx.aula2.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de regras de negocio.
 * O Controller passa por aqui para falar com o Repository.
 *
 * Injecao de dependencia via construtor: o Spring injeta o AlunoRepository.
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

    public boolean existePorId(Long id) {
        return alunoRepository.existsById(id);
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }
}
