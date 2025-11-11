import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { 
    path: 'auth/login',
    loadComponent: () => import('./auth/login.component').then(m => m.LoginComponent)
  },
  { 
    path: 'auth/register',
    loadComponent: () => import('./auth/register.component').then(m => m.RegisterComponent)
  },
  {
    path: '',
    canActivate: [authGuard],
    children: [
      {
        path: '',
        loadComponent: () => import('./components/listar-libros/listar-libros.component')
          .then(m => m.ListarLibrosComponent)
      },
      {
        path: 'crear',
        loadComponent: () => import('./components/crear-libro/crear-libro.component')
          .then(m => m.CrearLibroComponent)
      },
      {
        path: 'categorias',
        loadComponent: () => import('./components/listar-categorias/listar-categorias.component')
          .then(m => m.ListarCategoriasComponent)
      },
      {
        path: 'editar/:id',
        loadComponent: () => import('./components/editar-libro/editar-libro.component')
          .then(m => m.EditarLibroComponent)
      }
    ]
  },
  { path: '**', redirectTo: 'auth/login' }
];
