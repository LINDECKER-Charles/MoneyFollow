import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { SecurityRequestService } from 'src/app/services/request/security-request.service';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.scss'
})
export class ForgotPasswordComponent {

  public email: string = '';
  public cooldown: number = 0;
  private cooldownInterval: any;

  public successMessage: string = '';


  constructor(private securityService: SecurityRequestService, private router: Router) { }

  public onSubmit(): void {
    this.securityService.sendPasswordResetEmail(this.email).subscribe({
      next: (response) => {
        console.log(response);
        this.startCooldown(120);
      },
      error: (error) => {
        console.error(error);
        this.startCooldown(120);
      }
    })
    this.successMessage = "Le mail de réinitialisation a été envoyé !";
  }

  private startCooldown(seconds: number): void {
    this.cooldown = seconds;
    this.cooldownInterval = setInterval(() => {
      this.cooldown--;
      if (this.cooldown <= 0) {
        clearInterval(this.cooldownInterval);
        this.cooldownInterval = null;
      }
    }, 1000);
  }
}
