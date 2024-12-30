import {Component} from '@angular/core';
import {DebtDto} from '../../model/debt-dto';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {MatButton} from '@angular/material/button';
import {MatInput} from '@angular/material/input';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-new-debt',
  imports: [
    MatDialogContent,
    ReactiveFormsModule,
    MatError,
    MatLabel,
    MatFormField,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatDatepicker,
    MatButton,
    MatDialogActions,
    MatDialogTitle,
    MatInput,
    NgIf
  ],
  templateUrl: './new-debt.component.html',
  standalone: true,
  styleUrl: './new-debt.component.scss'
})
export class NewDebtComponent {
  debtForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    protected dialogRef: MatDialogRef<NewDebtComponent>
  ) {
    this.debtForm = this.fb.group({
      amount: ['', [Validators.required, Validators.min(1)]],
      dueDate: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.debtForm.invalid) {
      return;
    }
    const newDebt: DebtDto = this.debtForm.value;
    this.dialogRef.close(newDebt);
  }
}
