package lmv.daw.enums;

public enum Titulacao {
	DOUTOR("Doutor"),
	ASSISTENTE("Assistente"),
	LIVRE_DOCENTE("Livre-docente"),
	TITULAR("Titular");
	
	private String titulacao;

	private Titulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public String getTitulacao() {
		return titulacao;
	}
	
	public static Titulacao obterTitulacao(String tipoTitulacao) {
		if (Titulacao.ASSISTENTE.getTitulacao().equalsIgnoreCase(tipoTitulacao))
			return Titulacao.ASSISTENTE;
		
		if (Titulacao.DOUTOR.getTitulacao().equalsIgnoreCase(tipoTitulacao))
			return Titulacao.DOUTOR;
		
		if (Titulacao.LIVRE_DOCENTE.getTitulacao().equalsIgnoreCase(tipoTitulacao))
			return Titulacao.LIVRE_DOCENTE;
		
		return Titulacao.TITULAR;
	}
}
