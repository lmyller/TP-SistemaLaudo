package lmv.daw.agendaexecucao.diaria;

import java.time.LocalDate;
import java.util.List;

import lmv.daw.dao.ExameDAO;
import lmv.daw.email.Email;
import lmv.daw.enums.StatusExame;
import lmv.daw.modelo.Exame;

public class CancelaExame implements Runnable {

	@Override
	public void run() {
		List<Exame> examesAguardando = obterExamesAguardandoLaudo();
		
		cancelarExames(examesAguardando);
	}

	private List<Exame> obterExamesAguardandoLaudo() {
		try {
			ExameDAO exameDAO = new ExameDAO();
			
			return exameDAO.listaExameAguardando();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void cancelarExames(List<Exame> examesAguardando) {
		ExameDAO exameDAO = new ExameDAO();

		for (Exame exame : examesAguardando) {
			if(exame.getData().isBefore(LocalDate.now())) {
				exame.setStatusExame(StatusExame.EXAME_CANCELADO);
				
				Email.criarEmail(exame, "Cancelamento do exame", "Cancelado");
				
				try {
					exameDAO.altera(exame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
