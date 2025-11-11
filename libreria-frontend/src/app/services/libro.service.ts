import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class LibroService {
  // Use absolute backend API URL so requests target the backend running on port 8080
  private base = 'http://localhost:8080/api/libros';

  constructor(private http: HttpClient) {}

  listar(): Observable<any[]> {
    return this.http.get<any[]>(this.base);
  }

  guardar(libro: any) {
    return this.http.post(this.base, libro);
  }

  obtenerPorId(id: number) {
    return this.http.get(`${this.base}/${id}`);
  }

  actualizar(id: number, libro: any) {
    return this.http.put(`${this.base}/${id}`, libro);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.base}/${id}`);
  }
}
