import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { SecurityRequestService } from 'src/app/services/request/security-request.service';

@Component({
  selector: 'app-verify-email',
  standalone: true,
  imports: [CommonModule, RouterModule, RouterModule],
  templateUrl: './verify-email.component.html',
  styleUrl: './verify-email.component.scss'
})
export class VerifyEmailComponent implements OnInit {

  public loading: boolean = true;
  public amountInterval: any = null;

  public errorMessage: string | null = null;
  public successMessage: string | null = null;

  constructor(private securityRequest: SecurityRequestService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    const token = this.route.snapshot.queryParamMap.get('token');
    if (!token) {
      this.loading = false;
      this.errorMessage = "Aucun token fourni.";
      return;
    }
    this.securityRequest.verifyEmail(token).subscribe({
      next: (response) => {
        this.loading = false;
        this.successMessage = response.message;
      },
      error: (error) => {
        this.loading = false;
        if (error.error && error.error.message) {
          this.errorMessage = error.error.message;
        } else {
          this.errorMessage = "Une erreur est survenue.";
        }
      }
    })
  }
}
