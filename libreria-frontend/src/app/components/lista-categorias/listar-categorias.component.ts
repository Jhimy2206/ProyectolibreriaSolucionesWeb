import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CategoriaService } from '../../services/categoria.service';

@Component({
  selector: 'app-listar-categorias',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './listar-categorias.component.html',
  styleUrls: ['./listar-categorias.component.css']
})
export class ListarCategoriasComponent implements OnInit {
  categorias: any[] = [];
  loading = false;
  error = '';
  nuevaNombre = '';
  saving = false;
  success = '';

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.loading = true;
    this.error = '';
    this.categoriaService.listar().subscribe({
      next: (data: any[]) => {
        this.categorias = data || [];
        this.loading = false;
      },
      error: (err: any) => {
        this.error = err?.error?.message || 'Error al cargar categorías';
        this.loading = false;
      }
    });
  }

  borrar(id: number): void {
    if (!confirm('¿Eliminar categoría?')) return;
    this.categoriaService.eliminar(id).subscribe({
      next: () => this.cargar(),
      error: (err: any) => this.error = err?.error?.message || 'Error al eliminar'
    });
  }

  crear(): void {
    this.error = '';
    this.success = '';
    const nombre = (this.nuevaNombre || '').trim();
    if (!nombre) {
      this.error = 'Nombre requerido';
      return;
    }
    this.saving = true;
    this.categoriaService.guardar({ nombre }).subscribe({
      next: (res: any) => {
        this.success = 'Categoría creada';
        this.nuevaNombre = '';
        this.saving = false;
        this.cargar();
      },
      error: (err: any) => {
        this.error = err?.error?.message || 'Error al crear categoría';
        this.saving = false;
      }
    });
  }
}
