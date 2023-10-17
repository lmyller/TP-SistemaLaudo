package lmv.daw.enums;

public enum TipoExame {
	ECOCARDIOGRAMA("Ecocardiograma"),
	ELETROCARDIOGRAMA("Eletrocardiograma");
	
	private String tipoExame;

	private TipoExame(String tipoExame) {
		this.tipoExame = tipoExame;
	}

	public String getTipoExame() {
		return tipoExame;
	}
	
	public static TipoExame obterTipoExame(String exame) {
		return exame.equalsIgnoreCase(TipoExame.ECOCARDIOGRAMA.getTipoExame()) ? 
				TipoExame.ECOCARDIOGRAMA : TipoExame.ELETROCARDIOGRAMA; 
	}
}
