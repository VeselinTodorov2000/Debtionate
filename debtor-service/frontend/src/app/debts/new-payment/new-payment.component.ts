import {Component} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatDialogActions, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {MatButton} from '@angular/material/button';
import {MatInput} from '@angular/material/input';
import {PaymentDto} from '../../model/payment-dto';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-new-payment',
  imports: [
    MatDialogContent,
    ReactiveFormsModule,
    MatFormField,
    MatDialogTitle,
    MatDialogActions,
    MatButton,
    MatLabel,
    MatInput,
    MatError,
    NgIf
  ],
  templateUrl: './new-payment.component.html',
  standalone: true,
  styleUrl: './new-payment.component.scss'
})
export class NewPaymentComponent {
  paymentForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    protected dialogRef: MatDialogRef<NewPaymentComponent>
  ) {
    this.paymentForm = this.fb.group({
      amount: ['', [Validators.required, Validators.min(1)]],
    });
  }

  onSubmit() {
    if (this.paymentForm.invalid) {
      return;
    }
    const newPayment: PaymentDto = this.paymentForm.value;
    this.dialogRef.close(newPayment);
  }
}
