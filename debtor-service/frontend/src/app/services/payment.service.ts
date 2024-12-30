import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PaymentDto} from '../model/payment-dto';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private apiUrl = 'http://localhost:8080/api/payments';

  constructor(private http: HttpClient) { }

  public createPayment(paymentDto: PaymentDto): Observable<PaymentDto> {
    return this.http.post<PaymentDto>(this.apiUrl + '/create', paymentDto);
  }
}
