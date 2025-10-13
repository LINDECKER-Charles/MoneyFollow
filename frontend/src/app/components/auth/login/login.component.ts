import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { UserRequestService } from 'src/app/services/request/user-request.service';

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

  constructor(private authService: AuthService, private router: Router, private userService: UserRequestService) {}

  onSubmit(): void {
    this.authService.login(this.email, this.password).subscribe({
      next: () => {
        console.log('Authentifié avec succès');
          this.userService.isVerified().subscribe({
            next: (response) => {
              if(response === false) {
                this.router.navigate(['/check-email']);
                return;
              }
            },
            error: (err) => {
              console.error('Erreur lors de la vérification :', err);
              this.router.navigate(['/check-email']);
              return;
            }
          })
        this.router.navigate(['/profil']); // redirige après connexion réussie
      },
      error: (err) => {
        console.error('❌ Erreur de connexion :', err);
        this.errorMessage = 'Impossible de vous connecter. Vérifiez vos identifiants et réessayez.';
      }
    });
  }
}
