package br.com.anhembi.bradesco.validaCnpj.dto;

import lombok.Data;

@Data
public class CadastraEntidadeRequest {
	
	private String nome;
	private String cpfCnpj;
	private Float valor;
}
