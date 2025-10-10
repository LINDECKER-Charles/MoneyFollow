import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractRequestService {

  protected baseUrl = 'http://localhost:8000/api';  
  constructor(protected http : HttpClient, protected auth : AuthService, protected router : Router) { }

  protected request<T>(method: string, url: string, body?: any): Observable<T> {
    if(this.auth.isTokenExpired()){
      this.auth.logout();
      this.router.navigate(['/login']);
    }
    const token = this.auth.getToken();
    const headers = new HttpHeaders({
      'Authorization': token ? `Bearer ${token}` : '',
      'Content-Type': 'application/json'
    });
    return this.http.request<T>(method, url, { body, headers });
  }
}
