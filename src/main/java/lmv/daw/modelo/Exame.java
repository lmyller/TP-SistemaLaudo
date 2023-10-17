package lmv.daw.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import lmv.daw.enums.StatusExame;
import lmv.daw.enums.TipoExame;

public class Exame {
	private Long id;
	private TipoExame tipoExame;
	private Medico medico;
	private Paciente paciente;
	private String recomendacao;
	private StatusExame statusExame;
	private LocalDate data;
	private LocalTime hora;
	
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

	public String getRecomendacao() {
		return recomendacao;
	}

	public void setRecomendacao(String recomendacao) {
		this.recomendacao = recomendacao;
	}

	public StatusExame getStatusExame() {
		return statusExame;
	}

	public void setStatusExame(StatusExame statusExame) {
		this.statusExame = statusExame;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
}
