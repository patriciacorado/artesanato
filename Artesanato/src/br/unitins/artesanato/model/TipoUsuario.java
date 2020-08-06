package br.unitins.artesanato.model;

public enum TipoUsuario {
	
	ESCOLHA(0, "Selecione um tipo..."),
	SIM(1,"SIM"),
	NÃO(2,"NÃO");
	
	private int id;
	private String label;
	
	private TipoUsuario(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	//retorna o tipo de usuario conforme o Id
	public static TipoUsuario valueOf(int valor) {
		for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
			if (valor == tipoUsuario.getId())
				return tipoUsuario;
		} 
		return null;
	}
	
}
