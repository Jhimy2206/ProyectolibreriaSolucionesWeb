import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LibroService } from '../../services/libro.service';
import { CategoriaService } from '../../services/categoria.service';

@Component({
  selector: 'app-editar-libro',
  templateUrl: './editar-libro.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class EditarLibroComponent implements OnInit {
  categorias: any[] = [];
  libroEditado: any = { titulo: '', autor: '', anio: null, categoria: { id: null } };
  id!: number;

  constructor(
    private route: ActivatedRoute,
    private libroService: LibroService,
    private categoriaService: CategoriaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.categoriaService.listar().subscribe({ next: c => this.categorias = c || [] });
    if (this.id) {
      this.libroService.obtenerPorId(this.id).subscribe({
        next: (l: any) => this.libroEditado = l || (l?.body || {}),
        error: () => {}
      });
    }
  }

  actualizarLibro(): void {
    this.libroService.actualizar(this.id, this.libroEditado).subscribe({ next: () => this.router.navigate(['/']) });
  }
}
