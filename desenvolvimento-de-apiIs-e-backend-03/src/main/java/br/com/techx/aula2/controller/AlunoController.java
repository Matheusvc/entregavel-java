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
 * Fluxo: Cliente -> Controller -> Service -> Repository -> PostgreSQL
 *
 * Usa DTOs (AlunoRequest / AlunoResponse) para NAO expor a Entity Aluno
 * diretamente na API, e trata o Optional com if/else (sem lambda).
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
        // converte o DTO de entrada em uma nova Entity
        Aluno aluno = new Aluno(request.getNome(), request.getRa(), request.getIdade(), request.getCurso());
        Aluno salvo = alunoService.salvar(aluno);
        AlunoResponse response = converterParaResponse(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201
    }

    // READ (por id) -> GET /alunos/{id} -> 200 OK ou 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorId(id);
        // tratamento explicito do Optional, sem lambda
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            AlunoResponse response = converterParaResponse(aluno);
            return ResponseEntity.ok(response); // 200
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    // UPDATE -> PUT /alunos/{id} -> 204 No Content (ou 404 se nao existir)
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AlunoRequest request) {
        Optional<Aluno> alunoOptional = alunoService.buscarPorId(id);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            // atualiza os campos da Entity com os dados do DTO de entrada
            aluno.setNome(request.getNome());
            aluno.setRa(request.getRa());
            aluno.setIdade(request.getIdade());
            aluno.setCurso(request.getCurso());
            alunoService.salvar(aluno);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    // DELETE -> DELETE /alunos/{id} -> 204 No Content (verifica existencia antes)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (alunoService.existePorId(id)) {
            alunoService.deletar(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    // LISTAR TODOS -> GET /alunos -> 200 OK (lista de DTOs de saida)
    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarTodos() {
        List<Aluno> alunos = alunoService.listarTodos();
        return ResponseEntity.ok(converterLista(alunos)); // 200
    }

    // BUSCAR POR NOME -> GET /alunos/buscar?nome=... (@RequestParam) -> 200 OK
    @GetMapping("/buscar")
    public ResponseEntity<List<AlunoResponse>> buscarPorNome(@RequestParam String nome) {
        List<Aluno> alunos = alunoService.buscarPorNome(nome);
        return ResponseEntity.ok(converterLista(alunos)); // 200
    }

    // DESAFIO -> GET /alunos/ra -> frase com o nome e o RA do aluno
    @GetMapping("/ra")
    public String meuRa() {
        String nome = "MATHEUS VITOR CARNEIRO LIRA";
        int ra = 194035;
        return "O RA do aluno " + nome + " é " + ra;
    }

    // ---- metodos auxiliares de conversao Entity -> DTO (sem lambda) ----

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
