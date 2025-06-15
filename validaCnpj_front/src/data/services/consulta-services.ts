import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ConsultaRequest } from '../types/consultaRequest';
import { Observable } from 'rxjs';
import { ConsultaResponse } from '../types/consultaResponse';
import { CadastroRequest } from '../types/cadastroRequest';
import { CadastroResponse } from '../types/cadastroResponse';

@Injectable({
  providedIn: 'root'
})
export class ConsultaServices {
  private http = inject(HttpClient)
  constructor() { }

  consulta(request:ConsultaRequest){
    return this.http.post('http://localhost:8080/consulta',request) as Observable<ConsultaResponse>
  }
   cadastro(request:CadastroRequest){
    return this.http.post('http://localhost:8080/',request) as Observable<CadastroResponse>
  }
}
