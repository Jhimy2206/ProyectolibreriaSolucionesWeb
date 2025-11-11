import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CategoriaService {
  // Use absolute backend API URL so requests go to the Spring backend on port 8080
  private base = 'http://localhost:8080/api/categorias';

  constructor(private http: HttpClient) {}

  listar(): Observable<any[]> {
    return this.http.get<any[]>(this.base);
  }

  guardar(cat: any) {
    return this.http.post(this.base, cat);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.base}/${id}`);
  }

  crearCategoria(cat: any) {
    return this.guardar(cat);
  }
}
