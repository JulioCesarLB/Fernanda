package br.com.anhembi.bradesco.validaCnpj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntidadeConsultaDTO {
	private Long id;
	private String nome;
	private String cpfCnpj;
	private Float valor;
}
