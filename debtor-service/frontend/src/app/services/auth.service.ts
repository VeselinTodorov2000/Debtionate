import { Injectable } from '@angular/core';
import {AuthResponse} from '../model/auth-response';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/users';  // Използваме бекенд адреса

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<AuthResponse> {
    const loginRequest = { email, password };
    return this.http.post<AuthResponse>(this.apiUrl + '/login', loginRequest);
  }
}
