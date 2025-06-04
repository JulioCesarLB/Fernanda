package br.com.anhembi.bradesco.validaCnpj.dto;

import java.util.List;

import lombok.Data;

@Data
public class ConsultaCpfCnpjResponse {
	private List<AnaliseEntidadeDTO> response;
}
