package br.com.techx.aula2.controller;

import br.com.techx.aula2.dto.AlunoRequest;
import br.com.techx.aula2.dto.AlunoResponse;
import br.com.techx.aula2.model.Aluno;
import br.com.techx.aula2.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Camada Controller: recebe as requisicoes HTTP e devolve as respostas.
 * Usa DTOs (AlunoRequest / AlunoResponse) para nao expor a Entity Aluno.
 */
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // CREATE -> POST /alunos -> 201 Created
    @PostMapping
    public ResponseEntity<AlunoResponse> create(@RequestBody AlunoRequest request) {
        // converte o DTO em Entity usando o construtor vazio + setters (Lombok)
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setRa(request.getRa());
        aluno.setIdade(request.getIdade());
        aluno.setCurso(request.getCurso());

        Aluno salvo = alunoService.salvar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(converterParaResponse(salvo)); // 201
    }

    // READ (por id) -> GET /alunos/{id} -> 200 OK ou 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorId(id);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            return ResponseEntity.ok(converterParaResponse(aluno)); // 200
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    // UPDATE -> PUT /alunos/{id} -> 204 No Content
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AlunoRequest request) {
        alunoService.update(id, request);
        return ResponseEntity.noContent().build(); // 204
    }

    // DELETE -> DELETE /alunos/{id} -> 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    // LISTAR TODOS -> GET /alunos -> 200 OK
    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarTodos() {
        return ResponseEntity.ok(converterLista(alunoService.listarTodos())); // 200
    }

    // BUSCAR POR NOME -> GET /alunos/buscar?nome=... (@RequestParam) -> 200 OK
    @GetMapping("/buscar")
    public ResponseEntity<List<AlunoResponse>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(converterLista(alunoService.buscarPorNome(nome))); // 200
    }

    // DESAFIO -> GET /alunos/ra
    @GetMapping("/ra")
    public String meuRa() {
        String nome = "MATHEUS VITOR CARNEIRO LIRA";
        int ra = 194035;
        return "O RA do aluno " + nome + " é " + ra;
    }

    // ---- conversao Entity -> DTO (sem lambda) ----

    private AlunoResponse converterParaResponse(Aluno aluno) {
        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getRa(),
                aluno.getIdade(),
                aluno.getCurso());
    }

    private List<AlunoResponse> converterLista(List<Aluno> alunos) {
        List<AlunoResponse> responses = new ArrayList<>();
        for (Aluno aluno : alunos) {
            responses.add(converterParaResponse(aluno));
        }
        return responses;
    }
}
