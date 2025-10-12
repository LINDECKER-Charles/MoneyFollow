import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { UserRequestService } from 'src/app/services/request/user-request.service';

@Component({
  selector: 'app-edit-name',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './edit-name.component.html',
  styleUrl: './edit-name.component.scss'
})
export class EditNameComponent {

  public error: string | null = null;
  public success: string | null = null;
  public newName: string = '';

  constructor(private userService: UserRequestService, private router: Router) { }

  updateName(): void {
    this.userService.patchUserName(this.newName).subscribe({
      next: (response) => {
        console.log(response);
        this.success = "Nom mis à jour avec succès.";
        this.error = null;
        this.router.navigate(['/profil']);
      },
      error: (err) => {
        this.error = err;
        this.success = null;
      }
    })
  }

}
