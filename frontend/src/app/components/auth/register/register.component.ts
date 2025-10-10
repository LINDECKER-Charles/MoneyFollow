import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  email = '';
  username = '';
  password = '';
  passwordConfirm = '';
  passwordMismatch = false;
  showPassword = false;
  showConfirm = false;
  errorMessage = '';
  

  constructor(private authService: AuthService, private router: Router) {}
 
  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
  toggleConfirmVisibility() {
    this.showConfirm = !this.showConfirm;
  }

  onSubmit(): void {
    if (this.password !== this.passwordConfirm) {
      this.passwordMismatch = true;
      return;
    }
    this.passwordMismatch = false;
    this.authService.register(this.email, this.password, this.username).subscribe({
      next: () => {
        console.log('✅ Inscription réussie');
        this.router.navigate(['/profil']);
      },
      error: (err) => {
        console.error('❌ Erreur lors de l\'inscription :', err);
        this.errorMessage = 'Erreur lors de l\'inscription : email peut-être déjà utilisé ou serveur injoignable';
      }
    });
  }
}
