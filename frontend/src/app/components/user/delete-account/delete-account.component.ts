import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { User, UserRequestService } from 'src/app/services/request/user-request.service';

@Component({
  selector: 'app-delete-account',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './delete-account.component.html',
  styleUrl: './delete-account.component.scss'
})
export class DeleteAccountComponent {

  public confirmationText: string = '';
  public password: string = '';
  public user: User | null = null;
  
  public error: string | null = null;
  public showPasswordModal: boolean = false;
  public showPassword: boolean = false;

  constructor(private userRequest: UserRequestService, private auth: AuthService, private router: Router) { }
 
  openPasswordModal() {
    this.showPasswordModal = true;
  }

  closePasswordModal() {
    this.showPasswordModal = false;
    this.password = '';
    this.error = null;
  }

  confirmDeletion() {
    this.userRequest.getUser().subscribe({
      next: (user) => {
        this.user = user;
        this.auth.login(this.user.email, this.password).subscribe({
          next: () => {
            this.userRequest.deleteUser().subscribe({
              next: () => {
                this.auth.logout();
                this.router.navigate(['/login']);
              },
              error: (err) => {
                console.log(err);
                this.error = 'Erreur lors de la suppression du compte.';
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
        this.error = 'Erreur lors de la récupération des informations utilisateur.';
      }
    })
  }
}
