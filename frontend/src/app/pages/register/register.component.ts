import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  name = '';
  email = '';
  password = '';
  message = '';

  constructor(private http: HttpClient) {}

  register() {
    const body = { name: this.name, email: this.email, password: this.password };
    this.http.post('http://localhost:8000/auth/register', body, { responseType: 'text' })
      .subscribe({
        next: res => this.message = res,
        error: err => this.message = `Erreur: ${err.status} - ${err.error}`
      });
  }
}
