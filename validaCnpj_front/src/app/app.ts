import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RouterModule, RouterOutlet } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { ConsultaServices } from '../data/services/consulta-services';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';
import { Router } from '@angular/router';
import { request } from 'http';
@Component({
  selector: 'app-root',
  imports: [CommonModule,RouterOutlet, ReactiveFormsModule,NgxMaskDirective ],
  templateUrl: './app.html',
  styleUrl: './app.scss',
  providers:[provideNgxMask()]
})
export class App {
  private consultaService = inject(ConsultaServices)
  form: FormGroup;
  nota: number = 0;
  fila: number=0;
  valor:number =0;
  showResults = false;

  perguntas = [
    {pergunta:'Empresa validadora',id:'empresa'},
    {pergunta:'A conta bancária para depósito está no nome de pessoa física?',id:'atrasoEmPagamentos'},
    {pergunta:'A oferta é muito boa para ser verdade (ex: juros muito baixos)?',id:'possuiDividas'},
    {pergunta:'A empresa consultou órgãos como Serasa/SPC?',id:'enderecoReal'},
    {pergunta:'Ela está cadastrada no Banco Central?',id:'atividadeEconomicaJustificada'},
    {pergunta:'Possui algum processo aberto?',id:'possuiProcessoAberto'},
    {pergunta:'Tem bom histórico de pagamento?',id:'bomHistoricoDePagamento'},
    {pergunta:'Possui um bom Score de credito em bancos?',id:'possuiBomScoreDeCredito'},
    {pergunta:'SCORE',id:'score'}
  ];

  respostas: {
    empresa:string;
    atrasoEmPagamentos:boolean;
	  possuiDividas:boolean;
	  enderecoReal:boolean;
	  atividadeEconomicaJustificada:boolean;
	  possuiProcessoAberto:boolean;
	  bomHistoricoDePagamento:boolean;
	  possuiBomScoreDeCredito:boolean;
    score:number;
  }[] = [{
    empresa:'',
    atrasoEmPagamentos:false,
	  possuiDividas:false,
	  enderecoReal:false,
	  atividadeEconomicaJustificada:false,
	  possuiProcessoAberto:false,
	  bomHistoricoDePagamento:false,
	  possuiBomScoreDeCredito:false,
    score:0}];

  constructor(private fb: FormBuilder, private router: Router) {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      cnpj: ['', Validators.required],
      valor: ['', Validators.required]
    });


  }

  onSubmit() {
    if (this.form.invalid) return;
  
    this.consultaService.cadastro({
      nome:this.form.get('nome')?.value,
      cpfCnpj:this.form.get('cnpj')?.value,
      valor:this.form.get('valor')?.value
    }).subscribe(dadoCadastrado=>{
      this.consultaService.consulta({
        request:{
          id:dadoCadastrado.response.id,
          nome:dadoCadastrado.response.nome,
          cpfCnpj:dadoCadastrado.response.cpfCnpj,
          valor:dadoCadastrado.response.valor

        }
      }).subscribe(dado=>{
        this.nota=dado.scoreFinal;
        this.fila = dado.fila;
        this.valor= dadoCadastrado.response.valor;
        this.respostas = dado.response.map((analise)=>({
                  empresa:analise.empresaValidadora.nome,
                  atrasoEmPagamentos:analise.atrasoEmPagamentos,
                  possuiDividas:analise.possuiDividas,
                  enderecoReal:analise.enderecoReal,
                  atividadeEconomicaJustificada:analise.atividadeEconomicaJustificada,
                  possuiProcessoAberto:analise.possuiProcessoAberto,
                  bomHistoricoDePagamento:analise.bomHistoricoDePagamento,
                  possuiBomScoreDeCredito:analise.possuiBomScoreDeCredito,
                  score:analise.score}
        ))
      })
    })


      
    this.form.reset()
    this.showResults = true;
    const section = document.getElementById('resultados');
      if (section) {
        section.scrollIntoView({ behavior: 'smooth' });
      }
  }

  obterValorDados(resposta:any,chavePergunta:string){
    if(!chavePergunta) return'';
    if(chavePergunta.includes('score') || chavePergunta.includes('empresa')){
      return resposta[chavePergunta as keyof object]
    }
    return resposta[chavePergunta as keyof object]==true?"Sim":"Não"
  }

  get frase(){

    if(this.nota>=70){
      return 'Seu pedido passou na nossa análise inicial e será processado em breve.✅✅😁'
    }else if(this.nota<40){
      return'Não foi possível continuar com seu pedido. Detectamos inconsistências na sua solicitação. Por segurança, ela foi encerrada. Se quiser, entre em contato com nosso suporte.❌❌😭'
    }
    return 'Seu pedido está em análise. Estamos validando suas informações de forma segura🤔🤔😊'
  }
}