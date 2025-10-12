import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SecurityRequestService } from 'src/app/services/request/security-request.service';
import { User, UserRequestService } from 'src/app/services/request/user-request.service';

@Component({
  selector: 'app-confirm-email',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './confirm-email.component.html',
  styleUrl: './confirm-email.component.scss'
})
export class ConfirmEmailComponent {

  public cooldown: number = 0;
  public cooldownInterval: any = null;
  public user: User | null = null;

  public successMessage: string | null = null;
  public errorMessage: string | null = null;

  constructor(private router: Router, private securityRequest: SecurityRequestService, private userRequest: UserRequestService) { }

  public resendEmail(): void {
    if (this.cooldown > 0) return;
    this.securityRequest.sendVerificationEmail().subscribe({
      next: (response) => {
        this.successMessage = response.message;
        this.errorMessage = null;
        this.cooldown = 30;
        this.cooldownTick();
      },
      error: (error) => {
        this.errorMessage = error.error?.message || 'Une erreur est survenue.';
        this.successMessage = null;
        this.cooldown = 10;
        this.cooldownTick();
      }
    })
  }

  private cooldownTick(): void {
    this.cooldownInterval = setInterval(() => {
      this.cooldown--;
      if (this.cooldown <= 0) {
        clearInterval(this.cooldownInterval);
        this.cooldownInterval = null;
      }
    }, 1000);
  }

}
