package br.unitins.artesanato.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.artesanato.application.Util;
import br.unitins.artesanato.dao.DAO;
import br.unitins.artesanato.dao.ProdutoDAO;
import br.unitins.artesanato.model.Produto;
import br.unitins.artesanato.model.TipoProduto;

@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 511811915254852774L;

	
	public ProdutoController(DAO<Produto> dao) {
		super(new ProdutoDAO());
	}
	
	@Override
	public Produto getEntity() {
		if(entity == null)
			entity = new Produto();
		return entity;
	}

	//pesquisa por produtos 
	public void procuraProduto() {
		ProdutoDAO dao = new ProdutoDAO();
		dao.findAll();
	}
	
	public TipoProduto[] getListaProduto() {
		return TipoProduto.values();
	}
	
	public String carrinhoRedirect() {
		return "carrinho.xhtml?faces-redirect=true";
	}

	@Override
	boolean validarDados() {
		if (entity.getDescricao().isBlank()){
			Util.addErrorMessage("Todos os campos são obrigatórios");
			return false;
		}
		return true;
	}
}
