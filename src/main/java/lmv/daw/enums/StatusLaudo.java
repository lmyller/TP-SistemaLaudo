package lmv.daw.enums;

public enum StatusLaudo {
	PROVISORIO("Provisorio"),
	DEFINITIVO("Definitivo");
	
	private String statusLaudo;

	private StatusLaudo(String statusLaudo) {
		this.statusLaudo = statusLaudo;
	}

	public String getStatusLaudo() {
		return statusLaudo;
	}
	
	public static StatusLaudo obterStatusLaudo(String status) {
		return status.equalsIgnoreCase(PROVISORIO.getStatusLaudo()) ? PROVISORIO : DEFINITIVO;
	}
}
