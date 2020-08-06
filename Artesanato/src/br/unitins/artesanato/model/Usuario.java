package br.unitins.artesanato.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

public class Usuario extends Entity<Usuario> {

	private String nome;
	@NotEmpty(message="Não pode estar vazia.")
	private String senha;
	@Email(message="Email inválido.")
	private String email;
	@Past(message="Não pode estar no futuro")
	private LocalDate datanascimento;
	private TipoUsuario user;
	
	public LocalDate getDatanascimento() {
		return datanascimento;
	}
	public void setDatanascimento(LocalDate datanascimento) {
		this.datanascimento = datanascimento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TipoUsuario getUser() {
		return user;
	}
	public void setUser(TipoUsuario user) {
		this.user = user;
	}
	
	
}
