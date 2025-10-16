import { Injectable } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const noAuthGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Si l'utilisateur a un token valide, il est déjà connecté
  if (authService.isLoggedIn()) {
    router.navigate(['/home']); // redirige vers la page principale
    return false; // bloque l'accès à /login ou /register
  }

  return true; // autorise la navigation si non connecté
};
