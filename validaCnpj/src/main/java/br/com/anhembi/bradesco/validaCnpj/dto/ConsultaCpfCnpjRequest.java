package br.com.anhembi.bradesco.validaCnpj.dto;

import lombok.Data;

@Data
public class ConsultaCpfCnpjRequest {
	private String cpfCnpj;
	
	
	
	  public boolean isCpfCnpjValido( ) {
	        if (this.cpfCnpj == null) return false;

	        String numeros = this.cpfCnpj.replaceAll("[^\\d]", "");

	        if (numeros.length() == 11) {
	            return isCpfValido(numeros);
	        } else if (numeros.length() == 14) {
	            return isCnpjValido(numeros);
	        }

	        return false;
	    }

	    private static boolean isCpfValido(String cpf) {
	        if (cpf.matches("(\\d)\\1{10}")) return false;

	        int soma1 = 0, soma2 = 0;
	        for (int i = 0; i < 9; i++) {
	            int digito = cpf.charAt(i) - '0';
	            soma1 += digito * (10 - i);
	            soma2 += digito * (11 - i);
	        }

	        int digito1 = 11 - (soma1 % 11);
	        digito1 = (digito1 >= 10) ? 0 : digito1;
	        soma2 += digito1 * 2;

	        int digito2 = 11 - (soma2 % 11);
	        digito2 = (digito2 >= 10) ? 0 : digito2;

	        return digito1 == (cpf.charAt(9) - '0') && digito2 == (cpf.charAt(10) - '0');
	    }

	    private static boolean isCnpjValido(String cnpj) {
	        if (cnpj.matches("(\\d)\\1{13}")) return false;

	        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	        int soma1 = 0, soma2 = 0;
	        for (int i = 0; i < 12; i++) {
	            int digito = cnpj.charAt(i) - '0';
	            soma1 += digito * peso1[i];
	            soma2 += digito * peso2[i];
	        }

	        int digito1 = 11 - (soma1 % 11);
	        digito1 = (digito1 >= 10) ? 0 : digito1;
	        soma2 += digito1 * peso2[12];

	        int digito2 = 11 - (soma2 % 11);
	        digito2 = (digito2 >= 10) ? 0 : digito2;

	        return digito1 == (cnpj.charAt(12) - '0') && digito2 == (cnpj.charAt(13) - '0');
	    }

}
