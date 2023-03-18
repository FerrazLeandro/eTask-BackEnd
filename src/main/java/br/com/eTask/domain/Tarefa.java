package br.com.eTask.domain;

import java.time.LocalDate;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "titulo", length = 50)
	private String titulo;
	
	@NotNull
	@Column(name = "descricao", length = 200)
	private String descricao;
	
	@NotNull
	@Column(name = "criacao", length = 200)
	private LocalDate criacao;
	
	@NotNull
	@Column(name = "status", length = 50)
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario", nullable = false)
	private Usuario usuario;

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", criacao=" + criacao
				+ ", status=" + status + ", usuario=" + usuario + "]";
	}

	public Tarefa() {
		super();
	}

	public Tarefa(Long id, String titulo, String descricao, LocalDate criacao, String status, Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.criacao = criacao;
		this.status = status;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getCriacao() {
		return criacao;
	}

	public void setCriacao(LocalDate criacao) {
		this.criacao = criacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
