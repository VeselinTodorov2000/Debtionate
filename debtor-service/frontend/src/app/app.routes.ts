import { Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {DebtsComponent} from './debts/debts.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'debts/user/:id', component: DebtsComponent },
];

