package lmv.daw.modelo;

public class Medico {
	private String nome, crm;
	private boolean titularHospital;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public boolean isTitularHospital() {
		return titularHospital;
	}

	public void setTitularHospital(boolean titularHospital) {
		this.titularHospital = titularHospital;
	}
}
