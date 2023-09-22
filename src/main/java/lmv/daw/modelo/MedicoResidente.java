package lmv.daw.modelo;

public class MedicoResidente extends Medico{
	
	private short anoInicio;
	private String matricula;
	
	public short getAnoInicio() {
		return anoInicio;
	}
	
	public void setAnoInicio(short anoInicio) {
		this.anoInicio = anoInicio;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}
