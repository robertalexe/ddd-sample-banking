import { Client } from './../../../shared/models/client.model';
import { Account } from './../../../shared/models/account.model';
import { ClientService } from './../../../shared/services/client.service';
import { Transaction } from './../../../shared/models/transaction.model';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-transaction-details',
  templateUrl: './transaction-details.component.html',
  styleUrls: ['./transaction-details.component.scss']
})
export class TransactionDetailsComponent implements OnInit {
  @Input()
  account: Account;
  successMessage = '';

  constructor(private clientService: ClientService) {}

  ngOnInit() {
    this.getUpdatedListOfTransactions();
  }

  private getUpdatedListOfTransactions() {
    this.clientService.getClient().subscribe((client: Client) => {
      if (this.account)
        this.account = client.accounts.find(
          (account: Account) => account.uniqueId === this.account.uniqueId
        );
    });
  }
}
