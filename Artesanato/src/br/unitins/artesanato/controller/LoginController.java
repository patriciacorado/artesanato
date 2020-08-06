package br.unitins.artesanato.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.artesanato.application.Session;
import br.unitins.artesanato.application.Util;
import br.unitins.artesanato.dao.UsuarioDAO;
import br.unitins.artesanato.model.Usuario;

@Named
@RequestScoped
public class LoginController {
	private Usuario usuario;
	
	public String logar() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = dao.verificarLoginSenha(getUsuario().getEmail(),
				Util.hashSHA256(getUsuario().getSenha()));
		
		//verifica se o usuário não é nulo e adiciona o usuário à sessão criada na classe Session
		if (user != null) {
			// adicionando um usuario na sessao
			Session.getInstance().setAttribute("usuarioLogado", user);
			// redirecionando para o template
			return "index.xhtml?faces-redirect=true";
		}
		
		Util.addErrorMessage("Login ou Senha inválido.");
		return "";
	}
	
	public void limpar() {
		usuario = null;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
