package br.com.anhembi.bradesco.validaCnpj.dto;

import br.com.anhembi.bradesco.validaCnpj.model.EmpresaValidadora;
import br.com.anhembi.bradesco.validaCnpj.model.AnaliseCredito;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseEntidadeDTO {
	private Boolean atrasoEmPagamentos;

	private Boolean possuiDividas;
	
	private Boolean cpfCnpjValido;
	
	private Boolean enderecoReal;
	
	private Boolean atividadeEconomicaJustificada;
	
	private Boolean possuiProcessoAberto;
	
	private Boolean muitasReclamacoes;
	
	private Boolean boaReputacao;
	
	private Boolean possuiInformacoesClarasSobreTaxacoes;
	
	private Boolean possuiBomScoreDeCredito;
	
	private AnaliseCredito entidade;

	private EmpresaValidadora empresaValidadora;
	
}
