package br.com.anhembi.bradesco.validaCnpj.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "analise_credito")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseCredito extends AbstractEntity<Long>  {
	@Column(name = "nome_analise_credito", nullable = false)
	private String nome;
	
	@Column(name = "cpfCnpj_analise_credito", nullable = false)
	private String cpfCnpj;
	
	@Column(name = "valor_analise_credito", nullable = false)
	private Float valor;

}
