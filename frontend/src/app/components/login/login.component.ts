import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true, // composant standalone
  imports: [CommonModule, FormsModule, RouterModule], // autorise ngModel, ngIf, ngForm, etc.
  templateUrl: './login.component.html'
})
export class LoginComponent {
  email = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    this.authService.login(this.email, this.password).subscribe({
      next: () => {
        console.log('✅ Authentifié avec succès');
        this.router.navigate(['/home']); // redirige après connexion réussie
      },
      error: (err) => {
        console.error('❌ Erreur de connexion :', err);
        this.errorMessage = 'Identifiants invalides ou serveur injoignable';
      }
    });
  }
}
