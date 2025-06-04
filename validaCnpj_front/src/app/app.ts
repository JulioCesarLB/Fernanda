import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [CommonModule,RouterOutlet, ReactiveFormsModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  form: FormGroup;
  nota: number | null = null;
  showResults = false;

  perguntas = [
    'A conta bancária para depósito está no nome de pessoa física?',
    'A oferta é muito boa para ser verdade (ex: juros muito baixos)?',
    'A empresa consultou órgãos como Serasa/SPC?',
    'Ela está cadastrada no Banco Central?',
    'O contato foi feito por canais suspeitos (WhatsApp, link no e-mail, redes sociais)?'
  ];

  respostas: string[] = [];

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      cnpj: ['', Validators.required],
      valor: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.form.invalid) return;

    this.nota = Math.floor(Math.random() * 101); // Simulação
    this.respostas = [
      'Sim',
      'Não',
      'Sim',
      'Sim',
      'Não'
    ];
    this.showResults = true;
  }
}