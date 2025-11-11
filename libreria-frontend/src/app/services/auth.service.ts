import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api'; // Backend base URL

  constructor(private http: HttpClient) {}

  // NOTE: backend exposes user endpoints under /api/usuarios (registrar/login).
  login(credentials: {correo: string, contrasena: string}): Observable<any> {
    // The backend likely returns the user object (or a token). We store whatever is returned.
    return this.http.post(`${this.apiUrl}/usuarios/login`, credentials)
      .pipe(
        tap(user => {
          if (user) {
            localStorage.setItem('usuario', JSON.stringify(user));
          }
        })
      );
  }

  register(user: {nombre: string, correo: string, contrasena: string}): Observable<any> {
    return this.http.post(`${this.apiUrl}/usuarios/registrar`, user);
  }

  getCurrentUser(): any {
    const user = localStorage.getItem('usuario');
    return user ? JSON.parse(user) : null;
  }

  logout(): void {
    localStorage.removeItem('usuario');
  }
}
