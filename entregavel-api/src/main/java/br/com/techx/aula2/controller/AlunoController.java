package br.com.techx.aula2.controller;

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

import java.util.List;

/**
 * Camada Controller: recebe as requisições HTTP e devolve as respostas.
 * Fluxo: Cliente -> Controller -> Service -> Repository -> PostgreSQL
 *
 * Injeção de dependência via construtor: o Spring injeta o AlunoService.
 */
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // GET /alunos  -> lista todos os alunos
    @GetMapping
    public List<Aluno> listarTodos() {
        return alunoService.listarTodos();
    }

    // GET /alunos/{id}  -> busca um aluno pelo id (@PathVariable)
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /alunos/buscar?nome=Matheus  -> busca por nome (@RequestParam)
    @GetMapping("/buscar")
    public List<Aluno> buscarPorNome(@RequestParam String nome) {
        return alunoService.buscarPorNome(nome);
    }

    // POST /alunos  -> cria um novo aluno (@RequestBody com JSON)
    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        Aluno salvo = alunoService.salvar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // PUT /alunos/{id}  -> atualiza um aluno existente
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        return ResponseEntity.ok(alunoService.atualizar(id, aluno));
    }

    // DELETE /alunos/{id}  -> remove um aluno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // ==========================================================
    // DESAFIO PRÁTICO
    // GET /alunos/ra  -> devolve a frase com o nome e o RA do aluno
    // ==========================================================
    @GetMapping("/ra")
    public String meuRa() {
        String nome = "MATHEUS VITOR CARNEIRO LIRA";
        int ra = 194035;
        return "O RA do aluno " + nome + " é " + ra;
    }
}
