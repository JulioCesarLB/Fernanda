package br.com.anhembi.bradesco.validaCnpj.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="empresas_validadoras")
public class EmpresaValidadora extends AbstractEntity<Long>{

	private static final long serialVersionUID = 2328645662273810129L;
	
	@Column(name = "nome_empresa_validadora", nullable = false)
	private String nome;
	
	@Column(name = "cnpj_empresa_validadora", nullable = false)
	private String cnpj;


}
