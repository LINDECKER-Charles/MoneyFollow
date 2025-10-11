import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserRequestService, User } from 'src/app/services/request/user-request.service';

@Component({
  selector: 'app-edit-email',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './edit-email.component.html',
  styleUrl: './edit-email.component.scss'
})
export class EditEmailComponent {

  public newEmail: string = '';
  public confirmPassword: string = '';
  public user : User | null = null;

  public checking: boolean = true;
  private checkTimeout: any = null;
  public emailTaken: boolean = false;

  public showConfirmModal: boolean = false;
  public showSuccessModal: boolean = false;

  

  public error: string | null = null;

  constructor(private userRequest: UserRequestService, private router: Router, private auth: AuthService) {}

  checkEmailAvailability(): void {
    this.checking = true;

    if (this.checkTimeout) {
      clearTimeout(this.checkTimeout);
    }

    this.checkTimeout = setTimeout(() => {
      this.userRequest.getEmailAvailability(this.newEmail).subscribe({
        next: (response) => {
          response ? this.emailTaken = true : this.emailTaken = false;
          this.checking = false;
        },
        error: (err) => {
          console.log(err);
          this.error = 'Erreur lors de la vérification de l\'email.';
          this.checking = false;
        }
      });
    }, 250);
  }


  openConfirmModal() {
    this.showConfirmModal = true;
  }

  closeConfirmModal() {
    this.showConfirmModal = false;
  }

  closeSuccessModal() {
    this.showSuccessModal = false;
    this.router.navigate(['/profil']);
  }

  confirmEmailChange(): void {
    this.userRequest.getUser().subscribe({
      next: (response) => {
        this.user = response;
        this.auth.login(this.user.email, this.confirmPassword).subscribe({
          next: () => {
            this.userRequest.patchUserEmail(this.newEmail).subscribe({
              next: () => {
                this.showConfirmModal = false;
                this.auth.login(this.newEmail, this.confirmPassword).subscribe({
                  next: () => {
                    this.showSuccessModal = true;
                  },
                  error: (err) => {
                    console.log(err);
                    this.error = 'Erreur lors de la reconnexion avec le nouvel email.';
                  }
                })
              },
              error: (err) => {
                console.log(err);
                this.error = 'Erreur lors de la mise à jour de l\'email.';
              }
            })
          },
          error: (err) => {
            console.log(err);
            this.error = 'Mot de passe incorrect.';
          }
        })
      },
      error: (err) => {
        console.log(err);
        this.router.navigate(['/login']);
      }
    })
  }
}
