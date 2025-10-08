import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8000/auth';

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/authenticate`, { email, password })
      .pipe(
        tap((response: any) => {
          if (response.token) {
            // On stocke le token dans le localStorage
            localStorage.setItem('token', response.token);
          }
        })
      );
  }

  register(email: string, password: string, username: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, { email, password, username })
      .pipe(
        tap((response: any) => {
          if (response.token) {
            localStorage.setItem('token', response.token);
          }
        })
      );
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
