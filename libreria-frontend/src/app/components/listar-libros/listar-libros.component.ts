import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LibroService } from '../../services/libro.service';

@Component({
  selector: 'app-listar-libros',
  templateUrl: './listar-libros.component.html',
  standalone: true,
  imports: [CommonModule, RouterModule]
})
export class ListarLibrosComponent implements OnInit {
  libros: any[] = [];
  loading = false;
  error = '';

  constructor(private libroService: LibroService) {}

  ngOnInit(): void {
    this.cargarLibros();
  }

  cargarLibros(): void {
    this.loading = true;
    this.error = '';
    this.libroService.listar().subscribe({
      next: (data) => { this.libros = data || []; this.loading = false; },
      error: (err: any) => { this.error = err?.error?.message || 'Error al cargar libros'; this.loading = false; }
    });
  }

  borrarLibro(id: number): void {
    if (confirm('¿Estás seguro de eliminar este libro?')) {
      this.libroService.eliminar(id).subscribe({
        next: () => this.cargarLibros(),
        error: (err: any) => { this.error = err?.error?.message || 'Error al eliminar libro'; }
      });
    }
  }
}
