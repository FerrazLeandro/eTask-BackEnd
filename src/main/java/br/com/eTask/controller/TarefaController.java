package br.com.eTask.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.eTask.domain.Tarefa;
import br.com.eTask.repository.TarefaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {
	
	@Autowired
	TarefaRepository tarefaRepository;
	
	@GetMapping
	public ResponseEntity<List<Tarefa>> buscar() {
		return ResponseEntity.ok(tarefaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		if (!tarefa.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tarefa.get());
	}
	
	@PostMapping
	public ResponseEntity<Tarefa> inserir(@Valid @RequestBody Tarefa Tarefa) {
		Tarefa = tarefaRepository.save(Tarefa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Tarefa.getId())
				.toUri();
		return ResponseEntity.created(uri).body(Tarefa);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa Tarefa) {
		Optional<Tarefa> TarefaBanco = tarefaRepository.findById(id);
		if (!TarefaBanco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Tarefa.setId(id);
		Tarefa = tarefaRepository.save(Tarefa);
		return ResponseEntity.ok(Tarefa);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Optional<Tarefa> TarefaBanco = tarefaRepository.findById(id);
		if (!TarefaBanco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		tarefaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
