import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-nombre-componente',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './nombre-componente.component.html',
  styleUrls: ['./nombre-componente.component.css']
})
export class NombreComponenteComponent {}
