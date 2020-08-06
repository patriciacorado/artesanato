package br.unitins.artesanato.model;

public enum TipoProduto {

	NA0_DEFINIDO(0,"Escolha um tipo..."),
	BIJUTERIA(1, "Bijuteria"),
	SEMI_JOIA(2, "Semi-jóia"),
	DIVERSOS(3, "Diversos");
	
	private int id;
	private String label;
	
	private TipoProduto(int id, String label ) {
		this.id =id;
		this.label=label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

}
