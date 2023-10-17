package lmv.daw.logica;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lmv.daw.dao.MedicoDAO;
import lmv.daw.dao.MedicoDocenteDAO;
import lmv.daw.dao.MedicoResidenteDAO;
import lmv.daw.enums.TipoUsuario;
import lmv.daw.enums.Titulacao;
import lmv.daw.modelo.Medico;
import lmv.daw.modelo.MedicoDocente;
import lmv.daw.modelo.MedicoResidente;

public class AdicionaMedico implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String crm = req.getParameter("crm");
		String nome = req.getParameter("nome");
		String tipo = req.getParameter("tipo");
		
		MedicoDAO dao = new MedicoDAO();
		
		if (tipo.equalsIgnoreCase(TipoUsuario.MEDICO_DOCENTE.getTipoUsuario())) {
			String titulacao = req.getParameter("titulacao");
			cadastrarMedicoDocente(dao, crm, nome, titulacao);
		}
		
		if (tipo.equalsIgnoreCase(TipoUsuario.MEDICO_RESIDENTE.getTipoUsuario())) {
			Short anoInicio = Short.parseShort(req.getParameter("ano-inicio"));
			cadastrarMedicoResidente(dao, crm, nome, anoInicio);
		}
		
		if (tipo.equalsIgnoreCase(TipoUsuario.MEDICO.getTipoUsuario()))
			cadastrarMedico(dao, crm, nome);
		
		req.setAttribute("tipo", tipo);
		
		return "adiciona-usuario.jsp";
	}

	private void cadastrarMedico(MedicoDAO dao, String crm, String nome) {
		Medico medico = criarMedico(crm, nome);
		
		try {
			dao.adiciona(medico);
		} catch (Exception e) {
		}
	}

	private void cadastrarMedicoResidente(MedicoDAO dao, String crm, String nome, Short anoInicio) {
		MedicoResidente residente = criarMedicoResidente(crm, nome, anoInicio);
		MedicoResidenteDAO residenteDAO = new MedicoResidenteDAO();
		
		try {
			dao.adiciona(residente);
			residenteDAO.adiciona(residente);
		} catch (Exception e) {
		}
	}

	private void cadastrarMedicoDocente(MedicoDAO dao, String crm, String nome, String titulacao) {
		MedicoDocente docente = criarMedicoDocente(crm, nome, titulacao);
		MedicoDocenteDAO docenteDAO = new MedicoDocenteDAO();
		
		try {
			dao.adiciona(docente);
			docenteDAO.adiciona(docente);
		} catch (Exception e) {
		}
	}
	
	private Medico criarMedico(String crm, String nome) {
		Medico medico = new Medico();
		medico.setCrm(crm);
		medico.setNome(nome);
		medico.setTitularHospital(true);
		
		return medico;
	}
	
	private MedicoResidente criarMedicoResidente(String crm, String nome, Short anoInicio) {
		MedicoResidente residente = new MedicoResidente();
		residente.setCrm(crm);
		residente.setNome(nome);
		residente.setTitularHospital(false);
		residente.setAnoInicio(anoInicio);
		residente.setMatricula(crm + String.valueOf(anoInicio));
		
		return residente;
	}
	
	private MedicoDocente criarMedicoDocente(String crm, String nome, String titulacao) {
		MedicoDocente docente = new MedicoDocente();
		docente.setCrm(crm);
		docente.setNome(nome);
		docente.setTitularHospital(false);
		docente.setTitulacao(Titulacao.obterTitulacao(titulacao));
		
		return docente;
	}
}
