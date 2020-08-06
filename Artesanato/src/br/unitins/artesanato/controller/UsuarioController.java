package br.unitins.artesanato.controller;

import java.io.Serializable;
import java.time.LocalDate;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.artesanato.application.Util;
import br.unitins.artesanato.dao.UsuarioDAO;
import br.unitins.artesanato.model.TipoUsuario;
import br.unitins.artesanato.model.Usuario;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8291709229552433526L;

	public UsuarioController() {
		super(new UsuarioDAO());
	}

	public TipoUsuario[] getListaTipoUsuario() {
		return TipoUsuario.values();
	}

	public void pesquisaUsuario() {
		UsuarioDAO dao = new UsuarioDAO();
		dao.findAll();
	}
	
	public String cadastroRedirect() {
		return "cadastrousuario.xhtml?faces-redirect=true";
	}

	@Override
	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		return entity;
	}
	
	public boolean validarDados() {
		if (entity.getNome().isBlank() || entity.getEmail().isBlank() 
				|| entity.getSenha().isBlank()) {
			Util.addErrorMessage("Todos os campos são obrigatórios");
			return false;
		}
		String senha = Util.hashSHA256(getEntity().getSenha());
		getEntity().setSenha(senha);

		// adiciona o hash no localdate convertido em string
		String datanascimento = Util.hashSHA256(getEntity().getDatanascimento().toString());
		// converte a string datanascimento em um localdate
		LocalDate date = LocalDate.parse(datanascimento);
		getEntity().setDatanascimento(date);
		return true;
	}
	
	
}
