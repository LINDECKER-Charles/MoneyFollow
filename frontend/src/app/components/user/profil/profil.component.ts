import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { UserRequestService, User } from 'src/app/services/request/user-request.service';


@Component({
  selector: 'app-profil',
  standalone: true,
  imports: [CommonModule,],
  templateUrl: './profil.component.html',
  styleUrl: './profil.component.scss'
})
export class ProfilComponent implements OnInit {

  public loading: boolean = true;
  public error: any = null;
  public user : User | null = null;

  constructor(private userRequest: UserRequestService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.userRequest.getUser().subscribe({
      next: (response) => {
        this.user = response;
        this.loading = false;
      },
      error: (err) => {
        this.error = err;
        this.loading = false;
      }
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  changePassword(): void {
    this.router.navigate(['/profil/password-change']);
  }

  changeEmail(): void {
    this.router.navigate(['/profil/email-change']);
  }
}
