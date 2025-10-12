import { Injectable } from '@angular/core';
import { AbstractRequestService } from './abstract-request.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SecurityRequestService extends AbstractRequestService {

  private url = this.baseUrl + '/auth';

  sendVerificationEmail(): Observable<{ message: string }> {
    return this.request<{ message: string }>('GET', this.url + '/send-verify');
  }

  verifyEmail(token: string): Observable<{ message: string }> {
    return this.request<{ message: string }>('GET', this.url + '/verify?token=' + encodeURIComponent(token));
  }
}
