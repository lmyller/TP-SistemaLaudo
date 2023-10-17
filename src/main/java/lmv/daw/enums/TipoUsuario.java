package lmv.daw.enums;

public enum TipoUsuario {
	ADMIN("Admin"),
	MEDICO("Medico"),
	MEDICO_DOCENTE("Medico-Docente"),
	MEDICO_RESIDENTE("Medico-Residente");
	
	private String tipoUsuario;

	private TipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public static TipoUsuario obterTipoUsuario(String tipo) {
		if (tipo.equalsIgnoreCase(ADMIN.getTipoUsuario()))
			return ADMIN;
		if (tipo.equalsIgnoreCase(MEDICO.getTipoUsuario()))
			return MEDICO;
		if (tipo.equalsIgnoreCase(MEDICO_DOCENTE.getTipoUsuario()))
			return MEDICO_DOCENTE;
		
		return MEDICO_RESIDENTE;
	}
}
