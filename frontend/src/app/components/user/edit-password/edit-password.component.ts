import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserRequestService } from 'src/app/services/request/user-request.service';

@Component({
  selector: 'app-edit-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-password.component.html',
  styleUrl: './edit-password.component.scss'
})
export class EditPasswordComponent {

  public password: string = '';
  public confirmPassword: string = '';
  public error: string | null = null;

  public showPassword: boolean = false;
  public showConfirm: boolean = false;

  constructor(private userRequest: UserRequestService, private authService: AuthService, private router: Router) { }

  public toggleShowPassword(): void {
    this.showPassword = !this.showPassword;
  }
  public toggleShowConfirmPassword(): void {
    this.showConfirm = !this.showConfirm
  }

  public onSubmit(): void {
    this.userRequest.patchUserPassword(this.password).subscribe({
      next: (response) => {
        this.router.navigate(['/profil']);
      },
      error: (err) => {
        this.error = err;
      }
    });
  }

}
