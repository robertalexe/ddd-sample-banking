import { Client } from './../../../shared/models/client.model';
import { TransactionType } from './../../../shared/models/transaction.model';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { Account } from '../../../shared/models/account.model';
import { StoreService } from '../../../shared/store/store.service';

@Component({
  selector: 'app-account-action',
  templateUrl: './account-action.component.html',
  styleUrls: ['./account-action.component.scss']
})
export class AccountActionComponent implements OnInit {
  @Input()
  actionType: TransactionType;
  @Input()
  account: Account;

  actionForm: FormGroup;
  transferAccounts: Array<Account> = [];

  constructor(private store: StoreService) {}

  ngOnInit() {
    this.initializeActionForm();
    this.validateAction();
    if (this.actionType.toLocaleUpperCase() === TransactionType.TRANSFER) {
      this.handleTransferAction();
    }
  }

  private initializeActionForm() {
    this.actionForm = new FormGroup({
      amount: new FormControl(null)
    });
  }

  private handleTransferAction() {
    this.store.getClientState().subscribe((client: Client) => {
      this.transferAccounts = client.accounts.filter(
        (account: Account) => account.uniqueId !== this.account.uniqueId
      );
      this.actionForm.addControl(
        'transferAccount',
        new FormControl(null, Validators.required)
      );
    });
  }

  private validateAction() {
    const accountBalance = parseInt(this.account.balance);

    this.actionForm.valueChanges.subscribe(() => {
      this.actionForm
        .get('amount')
        .setValidators([
          Validators.required,
          Validators.max(
            this.actionType.toUpperCase() !== TransactionType.DEPOSIT
              ? accountBalance
              : Infinity
          )
        ]);
      this.store.updateTransactionState(this.actionForm);
    });
  }
}
