package lmv.daw.modelo;

import lmv.daw.enums.Titulacao;

public class MedicoDocente extends Medico{
	
	private Titulacao titulacao;

	public Titulacao getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(Titulacao titulacao) {
		this.titulacao = titulacao;
	}
}
