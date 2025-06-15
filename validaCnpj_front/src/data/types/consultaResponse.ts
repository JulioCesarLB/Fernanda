export type ConsultaResponse = {
    response:[{
      atrasoEmPagamentos:boolean;
	  possuiDividas:boolean;
	  enderecoReal:boolean;
	  atividadeEconomicaJustificada:boolean;
	  possuiProcessoAberto:boolean;
	  bomHistoricoDePagamento:boolean;
	  possuiBomScoreDeCredito:boolean;
	  credito:{
        id:number;
        nome:string;
	    cpfCnpj:string;
	    valor:number;
     };
	  empresaValidadora:{
        id:number;
        nome:string;
	    cnpj:string;
     };
	  score:number;
	
    }]
    scoreFinal:number;
	fila:number;
}