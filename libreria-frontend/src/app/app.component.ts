import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CategoriaService } from './services/categoria.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  template: `
    <div class="loading-tab" *ngIf="loading">Cargando...</div>
    <main>
      <router-outlet></router-outlet>
    </main>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  loading = false; // Implement loading logic as needed

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit(): void {
    // Ensure there are some default categories in the system. If none exist, create a few.
    this.categoriaService.listar().subscribe({
      next: (cats: any[]) => {
        if (!cats || cats.length === 0) {
          console.log('No categories found — creating defaults');
          const defaults = ['General', 'Sin categoría', 'Historia', 'Ciencia'];
          defaults.forEach(name => {
            this.categoriaService.guardar({ nombre: name }).subscribe({
              next: (res) => console.log('Categoria creada:', res),
              error: (err) => console.error('Error creando categoría', name, err)
            });
          });
        }
      },
      error: (err) => {
        console.error('Error al listar categorías en init:', err);
      }
    });
  }
}
