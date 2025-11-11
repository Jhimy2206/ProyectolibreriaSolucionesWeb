import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  correo = '';
  contrasena = '';
  error = '';

  constructor(private auth: AuthService, private router: Router) {
    // Si ya está logueado, redirigir a inicio
    if (this.auth.getCurrentUser()) {
      this.router.navigate(['/']);
    }
  }

  login(): void {
    this.error = '';
    this.auth.login({ correo: this.correo, contrasena: this.contrasena }).subscribe({
      next: () => {
        // on successful login navigate to home — the service already stores the user
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.error = err.error?.message || 'Credenciales incorrectas';
      }
    });
  }

  irARegistro(): void {
    this.router.navigate(['/auth/register']);
  }
}
