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
import {ActivatedRoute} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {NewDebtComponent} from './new-debt/new-debt.component';
import {NewPaymentComponent} from './new-payment/new-payment.component';
import {PaymentDto} from '../model/payment-dto';
import {PaymentService} from '../services/payment.service';

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
  currentUserId: any;
  debts: DebtDto[] = [];
  displayedColumns: string[] = ['id', 'amount', 'dueDate','status', 'actions'];

  constructor(private debtService: DebtService,
              private paymentService: PaymentService,
              private route: ActivatedRoute,
              private dialog: MatDialog) {
    this.currentUserId = this.route.snapshot.paramMap.get('id');
  }

  ngOnInit() {
    this.loadDebts();
  }

  loadDebts() {
    this.debtService.findDebtsByUserId(this.currentUserId).subscribe((data: DebtDto[]) => {
      this.debts = data;
    });
  }

  makePayment(debt: DebtDto) {
    const dialogRef = this.dialog.open(NewPaymentComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result: PaymentDto) => {
      result.debtId = debt.id!;
      result.paymentDate = new Date().toISOString();
      console.log(result);
      this.paymentService.createPayment(result).subscribe((response: any) => console.log(response));
    });
  }

  createNewDebt() {
    this.openDebtDialog();
  }

  openDebtDialog(): void {
    const dialogRef = this.dialog.open(NewDebtComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe((result: DebtDto) => {
      result.userId = this.currentUserId;
      result.status = "Due";
      this.debtService.createDebt(result).subscribe(response => console.log(response));
      this.debts.push(result);
    });
  }
}
