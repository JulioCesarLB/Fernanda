package br.com.anhembi.bradesco.validaCnpj.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.anhembi.bradesco.validaCnpj.dto.AnaliseEntidadeDTO;
import br.com.anhembi.bradesco.validaCnpj.dto.CadastraEntidadeRequest;
import br.com.anhembi.bradesco.validaCnpj.dto.CadastraEntidadeResponse;
import br.com.anhembi.bradesco.validaCnpj.dto.ConsultaCpfCnpjRequest;
import br.com.anhembi.bradesco.validaCnpj.dto.ConsultaCpfCnpjResponse;
import br.com.anhembi.bradesco.validaCnpj.dto.EntidadeConsultaDTO;
import br.com.anhembi.bradesco.validaCnpj.model.AnaliseCredito;
import br.com.anhembi.bradesco.validaCnpj.model.AnaliseEntidade;
import br.com.anhembi.bradesco.validaCnpj.model.EmpresaValidadora;
import br.com.anhembi.bradesco.validaCnpj.repository.AnaliseEntidadeRepository;
import br.com.anhembi.bradesco.validaCnpj.repository.CreditoRepository;
import br.com.anhembi.bradesco.validaCnpj.repository.EmpresaValidadoraRepository;
import br.com.anhembi.bradesco.validaCnpj.utils.MapperUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ValidaCnpjController {
	
	@Autowired	
	private CreditoRepository creditoRepository;
	@Autowired	
	private AnaliseEntidadeRepository analiseRepository;
	@Autowired	
	private EmpresaValidadoraRepository validadoraRepository;
	
	private Random random = new Random();
	
	
	@PostMapping
	public ResponseEntity<CadastraEntidadeResponse> cadastraEntidade(@RequestBody CadastraEntidadeRequest request) throws Exception{
	
			
			CadastraEntidadeResponse response = new CadastraEntidadeResponse();
			
			EntidadeConsultaDTO dto=MapperUtils.convertToDTO(creditoRepository.save(new AnaliseCredito(request.getNome(),request.getCpfCnpj(), request.getValor())));
			
			this.cadastraAnalises(dto);
			
			response.setResponse(dto);   
			return ResponseEntity.ok(response);
		
	}
	
	private void cadastraAnalises(EntidadeConsultaDTO dto) {
		AnaliseCredito credito = MapperUtils.convertToModel(dto.getId(), dto);
		List<EmpresaValidadora> empresas = validadoraRepository.findAll();
		
		empresas.forEach(empresa->{
			System.out.println(empresa);
			analiseRepository.save(
					new AnaliseEntidade(
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
	
	@PostMapping("consulta")
	public ResponseEntity<ConsultaCpfCnpjResponse> consultaCredito(@RequestBody ConsultaCpfCnpjRequest request) throws Exception{
		
		ConsultaCpfCnpjResponse response = new ConsultaCpfCnpjResponse();
		
		List<ConsultaCpfCnpjResponse> analisesParaFila = new ArrayList<>();
		
		List<AnaliseCredito> solicitacoesDeCredito = creditoRepository.findAll();
		
		for (AnaliseCredito analiseCredito : solicitacoesDeCredito) {
			ConsultaCpfCnpjResponse analiseDeCreditoDaEmpresa = new ConsultaCpfCnpjResponse();			
			analiseDeCreditoDaEmpresa.setResponse(MapperUtils.convertToDTO(analiseRepository.findAllByCredito(analiseCredito))); 
			analisesParaFila.add(analiseDeCreditoDaEmpresa);
		}
		
		analisesParaFila.sort((a, b) -> Float.compare(b.getScoreFinal(), a.getScoreFinal()));
		
		int posicaoFila = encontrarIndexPorIdAnaliseCredito(analisesParaFila, request.getRequest().getId());
			
		response.setResponse(MapperUtils.convertToDTO(analiseRepository.findAllByCredito(MapperUtils.convertToModel(request.getRequest().getId(), request.getRequest()))));   
		response.setFila(posicaoFila);
		return ResponseEntity.ok(response);
	
	}
	private int encontrarIndexPorIdAnaliseCredito(List<ConsultaCpfCnpjResponse> listaOrdenada, Long idBuscado) {
	    for (int i = 0; i < listaOrdenada.size(); i++) {
	        ConsultaCpfCnpjResponse item = listaOrdenada.get(i);
	        
	        for (AnaliseEntidadeDTO analise : item.getResponse()) {
	            if (analise.getCredito() != null && analise.getCredito().getId().equals(idBuscado)) {
	                return i+1;
	            }
	        }
	    }
	    return -1; 
	}
}
