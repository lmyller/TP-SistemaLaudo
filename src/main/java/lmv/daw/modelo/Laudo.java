package lmv.daw.modelo;

import lmv.daw.enums.StatusLaudo;

public class Laudo {
	private Exame exame;
	private String descricao;
	private String conclusao;
	private StatusLaudo statusLaudo;
	
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getConclusao() {
		return conclusao;
	}
	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}
	public StatusLaudo getStatusLaudo() {
		return statusLaudo;
	}
	public void setStatusLaudo(StatusLaudo statusLaudo) {
		this.statusLaudo = statusLaudo;
	}
}
