import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { SecurityRequestService } from 'src/app/services/request/security-request.service';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.scss'
})
export class ResetPasswordComponent implements OnInit{

  public errorMessage: string = "";
  public successMessage: string = "";
  public passwordMismatch: boolean = false;

  public password: string = "";
  public confirmPassword: string = "";

  constructor(private securityService: SecurityRequestService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    const token = this.route.snapshot.queryParamMap.get('token');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
  }

  onSumbit(){
    const token: string | null = this.route.snapshot.queryParamMap.get('token');
    if (!token) {
      this.errorMessage = "Aucun token fourni.";
      return;
    }
    if(this.password !== this.confirmPassword){
      this.passwordMismatch = true;
      return;
    }else{
      this.passwordMismatch = false;
    }
    this.securityService.changePassword(token, this.password).subscribe({
      next: (response) => {
        console.log(response);
        this.successMessage = "Changement de mot de passe réussi";
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = "Une erreur c'est produite pendant le changement de mot de passe";
      },
    })
  }
}
