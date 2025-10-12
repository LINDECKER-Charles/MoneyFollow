import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';
import { UserRequestService } from '../services/request/user-request.service';

export const noVerifiedGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const userService = inject(UserRequestService);

  if(!authService.isLoggedIn()) {
    router.navigate(['/login']);
    return false;
  }
  userService.isVerified().subscribe({
    next: (response) => {
      if(response === true) {
        router.navigate(['/profil']);
        return false;
      } else {
        return true;
      }
    },
    error: (err) => {
      console.error('Erreur lors de la vérification :', err);
      router.navigate(['/profil']);
      return false;
    }
  })
  return true;
};
