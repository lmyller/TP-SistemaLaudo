package lmv.daw.email;

import java.time.format.DateTimeFormatter;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import lmv.daw.modelo.Exame;
import lmv.daw.modelo.Medico;
import lmv.daw.modelo.Paciente;

public class Email {

	private static final String HOSTNAME = "smtp.googlemail.com"; 
	
	private static final int PORTA = 465;
	
	public static void criarEmail(Exame exame, String assunto, String status) {
		StringBuffer buffer = new StringBuffer();
		Paciente paciente = exame.getPaciente();
		Medico medico = exame.getMedico();

		buffer.append("Sistema de exames\n");
		buffer.append(String.format("Status exame: %s", status));
		buffer.append("\n\nPaciente----------------------------------------------------------------------------------");
		buffer.append(String.format("\nNome: %s\t\tCPF: %s\nSexo: %c\nIdade: %d\t\t\tData de Nascimento: %s\n",
					                paciente.getNome(), paciente.getCpf(), paciente.getSexo(), paciente.getIdade(),
					                paciente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		buffer.append("\nMédico--------------------------------------------------------------------------------------");
		buffer.append(String.format("\nCRM: %s\t\tNome: %s\n", medico.getCrm(), medico.getNome()));
		buffer.append("\nExane---------------------------------------------------------------------------------------");
		buffer.append(String.format("\nTipo do exame: %s\n", exame.getTipoExame().getTipoExame()));
		buffer.append(String.format("Recomendações: %s", exame.getRecomendacao()));
		buffer.append(String.format("\nData exame: %s", exame.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		
		Email.enviarEmail("myller42@gmail.com", assunto, buffer);
	}
	
	public static void enviarEmail(String destinatario, String assunto, StringBuffer mensagem) {
		String remetente = "myllerteste917@gmail.com";
		String senha = "daql uzrh uwtz zfbo";
		
		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName(HOSTNAME);
			email.setSmtpPort(PORTA);
			email.setAuthenticator(new DefaultAuthenticator(remetente, senha));
			email.setTLS(true);
			email.setSSLOnConnect(true);
			email.setFrom(remetente);
			email.setSubject(assunto);
			email.setMsg(new StringBuilder(mensagem).toString().toString());
			email.addTo(destinatario);
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
