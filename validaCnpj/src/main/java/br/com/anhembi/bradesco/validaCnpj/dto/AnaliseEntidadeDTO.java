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
		
	private Boolean enderecoReal;
	
	private Boolean atividadeEconomicaJustificada;
	
	private Boolean possuiProcessoAberto;
		
	private Boolean bomHistoricoDePagamento;
		
	private Boolean possuiBomScoreDeCredito;
	
	private AnaliseCredito credito;

	private EmpresaValidadora empresaValidadora;
	
	private Float score;
	
	
	public Float getScore(){
		Float scoreTotal =100.f;
		
		Float peso = scoreTotal/7;
		
		if(atrasoEmPagamentos) {
			scoreTotal-=peso;
		}
		
		if(possuiDividas) {
			scoreTotal-=peso;
		}
		
		if(!atividadeEconomicaJustificada) {
			scoreTotal-=peso;
		}
		
		if(possuiProcessoAberto) {
			scoreTotal-=peso;
		}
		
		if(!bomHistoricoDePagamento) {
			scoreTotal-=peso;
		}
		if(!possuiBomScoreDeCredito) {
			scoreTotal-=peso;
		}
		
		return scoreTotal;
	}
}
