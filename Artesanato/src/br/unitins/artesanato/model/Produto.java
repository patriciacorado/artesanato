package br.unitins.artesanato.model;

import javax.validation.constraints.NotEmpty;

public class Produto extends Entity<Produto>{

		@NotEmpty(message="Campo obrigatório")
		private String descricao;
		@NotEmpty(message="Campo obrigatório")
		private double preco;
		@NotEmpty(message="Campo obrigatório")
		private TipoProduto tipoProduto;
		
		
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public double getPreco() {
			return preco;
		}
		public void setPreco(double preco) {
			this.preco = preco;
		}
		public TipoProduto getTipoProduto() {
			return tipoProduto;
		}
		public void setTipoProduto(TipoProduto tipoProduto) {
			this.tipoProduto = tipoProduto;
		}
		
}
