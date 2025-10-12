import { Injectable } from '@angular/core';
import { AbstractRequestService } from './abstract-request.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SecurityRequestService extends AbstractRequestService {

  private url = this.baseUrl + '/auth';

  sendVerificationEmail(): Observable<{ message: string }> {
    return this.request<{ message: string }>('POST', this.url + '/send-verify');
  }

  sendPasswordResetEmail(email: string): Observable<{ message: string }> {
    return this.request<{ message: string }>('POST', this.url + '/send-reset-password?email='+ encodeURIComponent(email), {}, false);
  }

  changePassword(token: string, newPassword: string): Observable<{ message: string }>{
    return this.request<{ message: string }>('POST', this.url + '/reset-password?token=' + encodeURIComponent(token) + '&newPassword=' + encodeURIComponent(newPassword), {}, false);
  }

  verifyEmail(token: string): Observable<{ message: string }> {
    return this.request<{ message: string }>('GET', this.url + '/verify?token=' + encodeURIComponent(token), {}, false);
  }
}
