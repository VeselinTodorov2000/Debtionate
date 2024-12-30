import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {DebtDto} from '../model/debt-dto';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DebtService {
  private apiUrl = 'http://localhost:8080/api/debts';

  constructor(private http: HttpClient) { }

  public createDebt(debt: DebtDto): Observable<DebtDto> {
    return this.http.post<DebtDto>(this.apiUrl + '/create', debt);
  }

  public getById(id: number): Observable<DebtDto> {
    return this.http.get<DebtDto>(this.apiUrl + "/" + id);
  }

  public findDebtsByUserId(userId: number): Observable<Array<DebtDto>> {
    return this.http.get<Array<DebtDto>>(this.apiUrl + '/user/' + userId);
  }

  public updateDebt(debt: DebtDto): Observable<DebtDto> {
    return this.http.put<DebtDto>(this.apiUrl, debt)
  }

  public deleteDebt(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.apiUrl + '/' + id)
  }
}
