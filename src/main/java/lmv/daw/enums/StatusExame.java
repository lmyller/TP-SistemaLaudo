package lmv.daw.enums;

public enum StatusExame {
	AGUARDANDO_EXAME("Aguardando exame"),
	AGUARDANDO_LAUDO("Aguardando laudo"),
	LAUDO_REALIZADO("Laudo realizado"),
	EXAME_CANCELADO("Exame cancelado");
	
	private String status;

	private StatusExame(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	public static StatusExame obterStatusExame(String tipo) {
		if (tipo.equalsIgnoreCase(AGUARDANDO_EXAME.getStatus()))
			return AGUARDANDO_EXAME;
		if (tipo.equalsIgnoreCase(AGUARDANDO_LAUDO.getStatus()))
			return AGUARDANDO_LAUDO;
		if (tipo.equalsIgnoreCase(LAUDO_REALIZADO.getStatus()))
			return LAUDO_REALIZADO;
		
		return EXAME_CANCELADO;
	}
}
