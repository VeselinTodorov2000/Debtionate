import { Component, OnInit } from '@angular/core';
import {
  MatCell,
  MatCellDef, MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef,
  MatTable,
} from '@angular/material/table';
import {DebtService} from '../services/debt.service';
import {DebtDto} from '../model/debt-dto';
import {CurrencyPipe} from '@angular/common';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-debts',
  templateUrl: './debts.component.html',
  standalone: true,
  imports: [
    MatTable,
    CurrencyPipe,
    MatHeaderCell,
    MatCell,
    MatCellDef,
    MatHeaderCellDef,
    MatColumnDef,
    MatButton,
    MatHeaderRow,
    MatHeaderRowDef,
    MatRowDef,
    MatRow
  ],
  styleUrls: ['./debts.component.scss']
})
export class DebtsComponent implements OnInit {
  debts: DebtDto[] = [];
  displayedColumns: string[] = ['debtName', 'amount', 'status', 'actions'];

  constructor(private debtService: DebtService) {}

  ngOnInit() {
    this.loadDebts();
  }

  loadDebts() {
    this.debtService.findDebtsByUserId(1).subscribe((data: DebtDto[]) => {
      this.debts = data;
    });
  }

  makePayment(debt: DebtDto) {
    // логика за извършване на плащане
    console.log('Плащане за дълг: ', debt);
  }

  createNewDebt() {
    // логика за създаване на нов дълг
    console.log('Създаване на нов дълг');
  }
}
