import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
  id: number;
  username: string;
  name: string | null;
  email: string;
  role: string;
  createdAt: string;
  verified: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8000/api/user';

  constructor(private http: HttpClient) {}

  getUser(): Observable<User> {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      'Authorization': token ? `Bearer ${token}` : '',
      'Content-Type': 'application/json'
    });

    return this.http.get<User>(this.apiUrl, { headers });
  }
}
