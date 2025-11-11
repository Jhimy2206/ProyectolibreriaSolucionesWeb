import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  usuario = {
    nombre: '',
    correo: '',
    contrasena: ''
  };
  error = '';
  loading = false;
  successMessage = '';

  constructor(private auth: AuthService, private router: Router) {}

  registrar(): void {
    this.error = '';
    this.successMessage = '';
    // Trim inputs
    this.usuario.nombre = (this.usuario.nombre || '').trim();
    this.usuario.correo = (this.usuario.correo || '').trim();

    if (this.usuario.contrasena.length < 6) {
      this.error = 'La contraseña debe tener al menos 6 caracteres';
      return;
    }

    this.loading = true;
    console.log('Registrando usuario:', this.usuario);
    this.auth.register(this.usuario).pipe(
      finalize(() => this.loading = false)
    ).subscribe({
      next: (res: any) => {
        console.log('Usuario registrado exitosamente:', res);
        this.successMessage = 'Usuario registrado exitosamente. Redirigiendo al login...';
        setTimeout(() => this.router.navigate(['/auth/login']), 2000);
      },
      error: (err: any) => {
        console.error('Registro error:', err);
        if (err && err.status) {
          const serverMsg = err.error?.message || err.error || JSON.stringify(err.error || {});
          this.error = `Error ${err.status}: ${serverMsg}`;
        } else {
          this.error = 'Error al registrar usuario. Por favor intente más tarde.';
        }
      }
    });
  }
}
