import { Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },  
  { path: 'login', component: LoginComponent },
  // { path: 'debts', component: DebtsComponent },
  // { path: 'debts/:id', component: DebtDetailsComponent },
  // { path: 'debts/:id/payment', component: PaymentComponent },
];

