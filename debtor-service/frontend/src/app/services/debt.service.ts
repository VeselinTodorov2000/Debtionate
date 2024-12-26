import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {DebtDto} from '../model/debt-dto';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DebtService {
  private apiUrl = 'http://localhost:8080/api/debts';  // Използваме бекенд адреса

  constructor(private http: HttpClient) { }

  public findDebtsByUserId(userId: number): Observable<Array<DebtDto>> {
    return this.http.get<Array<DebtDto>>(this.apiUrl + '/user/' + userId);
  }
}
