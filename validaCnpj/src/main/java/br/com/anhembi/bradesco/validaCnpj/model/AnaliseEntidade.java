package br.com.anhembi.bradesco.validaCnpj.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "analises_entidades")
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseEntidade extends AbstractEntity<Long>  {
	
	
	@Column(name = "atraso_em_pagamentos", nullable = false)
	private Boolean atrasoEmPagamentos;

	@Column(name = "dividas", nullable = false)
	private Boolean possuiDividas;
	
	@Column(name = "cpf_cnpj_valido", nullable = false)
	private Boolean cpfCnpjValido;
	
	@Column(name = "endereco_real", nullable = false)
	private Boolean enderecoReal;
	
	@Column(name = "atividade_economica_justificada", nullable = false)
	private Boolean atividadeEconomicaJustificada;
	
	@Column(name = "processo_aberto", nullable = false)
	private Boolean possuiProcessoAberto;
	
	@Column(name = "reclamacoes", nullable = false)
	private Boolean muitasReclamacoes;
	
	@Column(name = "boa_reputacao", nullable = false)
	private Boolean boaReputacao;
	
	@Column(name = "informacoes_claras_taxacoes", nullable = false)
	private Boolean possuiInformacoesClarasSobreTaxacoes;
	
	@Column(name = "bom_score_credito", nullable = false)
	private Boolean possuiBomScoreDeCredito;
	
	@ManyToOne
	@JoinColumn(name = "analise_credito_fk", nullable = true)
	private AnaliseCredito credito;
	
	@ManyToOne
	@JoinColumn(name = "empresa_validadora_fk", nullable = true)
	private EmpresaValidadora empresaValidadora;
	
	
	

}
