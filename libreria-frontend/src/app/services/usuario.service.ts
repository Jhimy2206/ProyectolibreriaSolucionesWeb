import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class UsuarioService {
  getUsuario(): any {
    const u = localStorage.getItem('usuario');
    return u ? JSON.parse(u) : null;
  }
  setUsuario(u: any): void {
    localStorage.setItem('usuario', JSON.stringify(u));
  }
  clear(): void {
    localStorage.removeItem('usuario');
  }
}
