package lmv.daw.modelo;

import lmv.daw.enums.TipoExame;

public class Exame {
	private Long id;
	private TipoExame tipoExame;
	private Medico medico;
	private Paciente paciente;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoExame getTipoExame() {
		return tipoExame;
	}
	
	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}
	
	public Medico getMedico() {
		return medico;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
