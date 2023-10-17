package lmv.daw.validacao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Valida {
	public static String obterHashSenha(String senha) {
		MessageDigest digest;
		byte[] hash;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			return null;
		}
		return Base64.getEncoder().encodeToString(hash);
	}
	
	public static boolean validaCpf(String cpf) {
		int dig_10, dig_11;
		
		if(cpf.matches("[0-9]{11}")) {
			if(verificarNumerosIguais(cpf))
				return false;
			
			dig_10 = verificaDigito(cpf, 10); 
			dig_11 = verificaDigito(cpf, 11);
			
			if(dig_10 == Character.getNumericValue(cpf.charAt(9)) && dig_11 == Character.getNumericValue(cpf.charAt(10)))
				return true;
			
			else 
				return false;
		}
		return false;
	}
	
	private static boolean verificarNumerosIguais(String cpf) {
		int[] numerosCpf = converterCpfEmInteiro(cpf);
		
		Arrays.sort(numerosCpf);
		
		if(numerosCpf[0] == numerosCpf[numerosCpf.length - 1])
			return true;
		
		return false;
	}

	private static int[] converterCpfEmInteiro(String cpf) {
		int[] numerosCpf = new int[11];
		
		for(int indice = 0; indice < cpf.length(); indice++)
			numerosCpf[indice] = Character.getNumericValue(cpf.charAt(indice));
		
		return numerosCpf;
	}

	private static int verificaDigito(String cpf, int numPeso) {
		int resto, soma = 0, maxIndice = numPeso - 1;

		for(int indice = 0;indice < maxIndice;indice++) {
			soma += Character.getNumericValue(cpf.charAt(indice)) * numPeso;
			numPeso--;
		}
		
		resto = 11 - (soma % 11);
		
        if (resto == 10 || resto == 11)
            return 0;
        
        else 
        	return resto;
	}
}
