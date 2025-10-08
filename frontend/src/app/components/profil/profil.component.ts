import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService, User } from '../../services/user.service';

@Component({
  selector: 'app-profil',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profil.component.html',
})
export class ProfilComponent implements OnInit {
  user: User | null = null;
  error: string | null = null;
  loading = true;

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.userService.getUser().subscribe({
      next: (data) => {
        this.user = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.status === 403
          ? 'Accès refusé : token invalide ou expiré.'
          : 'Erreur lors du chargement du profil.';
        this.loading = false;
      }
    });
  }
}
