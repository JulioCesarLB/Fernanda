package br.com.anhembi.bradesco.validaCnpj.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lombok.Data;

@Data
public class ConsultaCpfCnpjResponse {
	private List<AnaliseEntidadeDTO> response;
	private Float scoreFinal;
	private int fila;
	
	
	public Float getScoreFinal(){
		Float score=0.f;
		for (var analise : response) {
	        score += analise.getScore();
	    }
		
		if(response.size()>0) {
			 BigDecimal bd = new BigDecimal((score/response.size()));
		        bd = bd.setScale(2, RoundingMode.HALF_UP);
		        return bd.floatValue();
		}
		return 0.f;
	}
}
