import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserRequestService } from 'src/app/services/request/user-request.service';
import { ValidationService } from 'src/app/services/utils/validation.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  public email: string = '';
  public username: string = '';
  public password: string = '';
  public passwordConfirm: string = '';

  public showPassword: boolean = false;
  public showConfirm: boolean = false;
  
  public errorMessage: string = '';
  

  constructor(private authService: AuthService, private router: Router, private UserRequestService: UserRequestService, private validate: ValidationService) {}
 
  public togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
  public toggleConfirmVisibility() {
    this.showConfirm = !this.showConfirm;
  }

  public onSubmit(): void {
    if(!this.validate.validEmail(this.email)){
      this.errorMessage = "Email invalide.";
      return;
    }
    if(!this.validate.validPassword(this.password)){
      this.errorMessage = "Mot de passe trop faible.";
      return;
    }

    this.authService.register(this.email, this.password, this.username).subscribe({
      next: () => {
        console.log('✅ Inscription réussie');
        this.router.navigate(['/check-email']);
      },
      error: (err) => {
        console.error('❌ Erreur lors de l\'inscription :', err);
        this.errorMessage = 'Email déjà pris';
      }
    });
  }

  private notValidPassword(password: string): boolean{
    const strongPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;
    if(!strongPasswordRegex.test(password)){
      this.errorMessage = 'Le mot de passe doit contenir au minimum 8 caractères, avec une majuscule, une minuscule, un chiffre et un symbole.';
      return true;
    }
    return false;
  }

  private notValidEmail(email: string): boolean{
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
    if(!emailRegex.test(email)){
      this.errorMessage = "L'adresse e-mail n'est pas valide.";
      return true;
    }
    return false;
  }
}
