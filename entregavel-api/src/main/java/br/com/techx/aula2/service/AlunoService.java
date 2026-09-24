package br.com.techx.aula2.service;

import br.com.techx.aula2.model.Aluno;
import br.com.techx.aula2.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de regras de negócio.
 * O Controller nunca fala direto com o Repository: ele passa pelo Service.
 *
 * Injeção de dependência via construtor (forma recomendada):
 * o Spring injeta automaticamente o AlunoRepository ao criar este Bean.
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

    public Aluno atualizar(Long id, Aluno dados) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id " + id));
        aluno.setNome(dados.getNome());
        aluno.setRa(dados.getRa());
        aluno.setIdade(dados.getIdade());
        aluno.setCurso(dados.getCurso());
        return alunoRepository.save(aluno);
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }
}
