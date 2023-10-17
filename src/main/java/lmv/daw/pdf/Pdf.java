package lmv.daw.pdf;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import lmv.daw.enums.TipoExame;
import lmv.daw.modelo.Exame;

public class Pdf {
	
	public static void criarPdf(Exame exame) {
		StringBuilder conteudo = obterConteudo(exame);
			
		try {
			Image image = new Image(ImageDataFactory.create(obterImagem(exame.getTipoExame())));
		
			PdfWriter pdfWriter = new PdfWriter(String.format("C:\\Users\\luizm\\Documents\\"
					+ "Java\\TP\\TP-SistemaLaudo\\pdf\\exame_%s.pdf", exame.getPaciente().getCpf()));
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument);
			
			document.add(new Paragraph(conteudo.toString()));
			document.add(image);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String obterImagem(TipoExame tipoExame) {
		if (tipoExame == TipoExame.ECOCARDIOGRAMA)
			return String.format("C:\\Users\\luizm\\Documents\\Java\\TP\\TP-SistemaLaudo\\src\\main"
						+ "\\webapp\\assets\\imagens\\ecocardiograma\\eco%d.jpeg", obterNumeroAleatorio());
		
		return String.format("C:\\Users\\luizm\\Documents\\Java\\TP\\TP-SistemaLaudo\\src\\main"
				+ "\\webapp\\assets\\imagens\\eletrocardiograma\\eletro%d.jpeg", obterNumeroAleatorio());
	}

	private static int obterNumeroAleatorio() {
		Random random = new Random();
		
		return random.nextInt(10) + 1;
	}

	private static StringBuilder obterConteudo(Exame exame) {
		StringBuilder conteudo = new StringBuilder();
		
		conteudo.append(String.format("Exame Nº %d - %s", exame.getId(), exame.getTipoExame().getTipoExame()));
		conteudo.append(String.format("\n\n\nCPF: %s\t\t\t\tNome:%s", exame.getPaciente().getCpf(), exame.getPaciente().getNome()));
		conteudo.append(String.format("\n\n\nData Realização: %s\t\t\t\t", exame.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		conteudo.append(String.format("Hora Realização: %s", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))));
		
		return conteudo;
	}
}
