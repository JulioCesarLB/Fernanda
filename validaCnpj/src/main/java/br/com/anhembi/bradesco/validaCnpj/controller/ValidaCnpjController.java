package br.com.anhembi.bradesco.validaCnpj.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.anhembi.bradesco.validaCnpj.dto.CadastraEntidadeRequest;
import br.com.anhembi.bradesco.validaCnpj.dto.CadastraEntidadeResponse;
import br.com.anhembi.bradesco.validaCnpj.dto.ConsultaCpfCnpjRequest;
import br.com.anhembi.bradesco.validaCnpj.dto.ConsultaCpfCnpjResponse;
import br.com.anhembi.bradesco.validaCnpj.dto.EntidadeConsultaDTO;
import br.com.anhembi.bradesco.validaCnpj.model.AnaliseEntidade;
import br.com.anhembi.bradesco.validaCnpj.model.EmpresaValidadora;
import br.com.anhembi.bradesco.validaCnpj.model.AnaliseCredito;
import br.com.anhembi.bradesco.validaCnpj.repository.AnaliseEntidadeRepository;
import br.com.anhembi.bradesco.validaCnpj.repository.EmpresaValidadoraRepository;
import br.com.anhembi.bradesco.validaCnpj.repository.EntidadeConsultadaRepository;
import br.com.anhembi.bradesco.validaCnpj.utils.MapperUtils;

@RestController
public class ValidaCnpjController {
	
	@Autowired	
	private EntidadeConsultadaRepository entidadeRepository;
	@Autowired	
	private AnaliseEntidadeRepository analiseRepository;
	@Autowired	
	private EmpresaValidadoraRepository validadoraRepository;
	
	private Random random = new Random();

  
	
	@PostMapping
	public ResponseEntity<ConsultaCpfCnpjResponse> consultaCpfCnpj(@RequestBody ConsultaCpfCnpjRequest request) throws Exception {
		if(request.isCpfCnpjValido()) {
			ConsultaCpfCnpjResponse response = new ConsultaCpfCnpjResponse();
			List<AnaliseCredito> creditos = entidadeRepository.findByCpfCnpj(request.getCpfCnpj());
			
			if(creditos.size()>0) {
				response.setResponse(MapperUtils.convertToDTO(analiseRepository.findAllByEntidade(creditos.get(0))));   
				return ResponseEntity.ok(response);
			}
			
			throw new Exception("Cpf não cadastrado");
		}
		 throw new Exception("Cpf invalido");
	}
	
	@PostMapping("cadastro")
	public ResponseEntity<CadastraEntidadeResponse> cadastraEntidade(@RequestBody CadastraEntidadeRequest request) throws Exception{
		if(entidadeRepository.findByCpfCnpj(request.getCpfCnpj()).size()==0) {
			
			CadastraEntidadeResponse response = new CadastraEntidadeResponse();
			response.setResponse(MapperUtils.convertToDTO(entidadeRepository.save(new AnaliseCredito(request.getNome(),request.getCpfCnpj(), request.getValor())))); 
			
			this.cadastraAnalises(response.getResponse());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		throw new Exception("Entidade ja cadastrada");
	}
	
	private void cadastraAnalises(EntidadeConsultaDTO dto) {
		AnaliseCredito credito = MapperUtils.convertToModel(dto.getId(), dto);
		List<EmpresaValidadora> empresas = validadoraRepository.findAll();
		empresas.forEach(empresa->{
			analiseRepository.save(
					new AnaliseEntidade(
							random.nextBoolean(),
							random.nextBoolean(),
							random.nextBoolean(),
							random.nextBoolean(),
							random.nextBoolean(),
							random.nextBoolean(),
							random.nextBoolean(),
							random.nextBoolean(),
							random.nextBoolean(),
							random.nextBoolean(),
							credito,
							empresa
							)
					);
			});
	}
}
