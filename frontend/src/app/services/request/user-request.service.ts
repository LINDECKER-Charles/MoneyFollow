import { Injectable } from '@angular/core';
import { AbstractRequestService } from './abstract-request.service';
import { Observable } from 'rxjs/internal/Observable';

export interface User {
  name: string,
  email: string,
  createdAt: string,
  isVerified: boolean
}

@Injectable({
  providedIn: 'root'
})
export class UserRequestService extends AbstractRequestService {
  
  private url = this.baseUrl + '/api/user';

  public getUser(): Observable<User> {
    return this.request<User>('GET', this.url);
  }

  public isVerified(): Observable<boolean> {
    return this.request<boolean>('GET', this.url + '/isVerified');
  }

  public patchUserPassword(password: string): Observable<User> {
    return this.request<User>('PATCH', this.url, { "password" : password });
  }

  public patchUserEmail(email: string): Observable<User> {
    return this.request<User>('PATCH', this.url, { "email" : email });
  }

  public patchUserName(name: string): Observable<User> {
    return this.request<User>('PATCH', this.url, { "name" : name });
  }

  public getEmailAvailability(email: string): Observable<{ available: boolean }> {
    return this.request<{ available: boolean }>('GET', this.url + '/search/email?value=' + encodeURIComponent(email));
  }

  public deleteUser(): Observable<void> {
    return this.request<void>('DELETE', this.url);
  }


}
