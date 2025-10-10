import { Injectable } from '@angular/core';
import { AbstractRequestService } from './abstract-request.service';
import { Observable } from 'rxjs/internal/Observable';

export interface User {
  name: string,
  email: string,
  createdAt: string,
  verified: boolean
}

@Injectable({
  providedIn: 'root'
})
export class UserRequestService extends AbstractRequestService {
  
    private url = this.baseUrl + '/user';

  getUser(): Observable<User> {
    return this.request<User>('GET', this.url);
  }

  patchUserPassword(password: string): Observable<User> {
    return this.request<User>('PATCH', this.url, { "password" : password });
  }

  patchUserEmail(email: string): Observable<User> {
    return this.request<User>('PATCH', this.url, { "email" : email });
  }

  getEmailAvailability(email: string): Observable<{ available: boolean }> {
    return this.request<{ available: boolean }>('GET', this.url + '/search/email?value=' + encodeURIComponent(email));
  }


}
