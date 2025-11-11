import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LibroService } from '../../services/libro.service';
import { CategoriaService } from '../../services/categoria.service';

@Component({
  selector: 'app-crear-libro',
  templateUrl: './crear-libro.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class CrearLibroComponent implements OnInit {
  currentYear = new Date().getFullYear();
  libro = {
    titulo: '',
    autor: '',
    anio: this.currentYear,
    categoria: { id: null }
  };
  categorias: any[] = [];

  constructor(
    private libroService: LibroService,
    private categoriaService: CategoriaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.categoriaService.listar().subscribe({
      next: (data) => this.categorias = data
    });
  }

  guardarLibro(): void {
    this.libroService.guardar(this.libro).subscribe({
      next: () => this.router.navigate(['/'])
    });
  }
}
