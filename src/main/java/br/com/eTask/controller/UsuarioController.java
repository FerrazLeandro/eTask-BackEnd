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

import br.com.eTask.domain.Usuario;
import br.com.eTask.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> buscar() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
		Optional<Usuario> Usuario = usuarioRepository.findByEmail(email);
		if (!Usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Usuario.get());
	}
	
	@PostMapping
	public ResponseEntity<Usuario> inserir(@Valid @RequestBody Usuario Usuario) {
		Usuario = usuarioRepository.save(Usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(Usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario Usuario) {
		Optional<Usuario> UsuarioBanco = usuarioRepository.findById(id);
		if (!UsuarioBanco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Usuario.setId(id);
		Usuario = usuarioRepository.save(Usuario);
		return ResponseEntity.ok(Usuario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Optional<Usuario> UsuarioBanco = usuarioRepository.findById(id);
		if (!UsuarioBanco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		usuarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

