import { Component } from '@angular/core';
import { CategoriaService } from '../../services/categoria.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-crear-categoria',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './crear-categoria.component.html',
  styleUrls: ['./crear-categoria.component.css']
})
export class CrearCategoriaComponent {
  nombre: string = '';

  constructor(private categoriaService: CategoriaService) {}

  crearCategoria() {
    if (this.nombre.trim()) {
      this.categoriaService.crearCategoria({ nombre: this.nombre }).subscribe(
        (response: any) => {
          console.log('Categoría creada:', response);
          this.nombre = '';
        },
        (error: any) => {
          console.error('Error al crear categoría:', error);
        }
      );
    }
  }
}
