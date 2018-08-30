import { Client } from './../../../shared/models/client.model';
import { ClientService } from './../../../shared/services/client.service';
import { TransactionType } from './../../../shared/models/transaction.model';
import { ModalContainerComponent } from './../../../shared/components/modal-container/modal-container.component';
import { TransactionService } from './../../../shared/services/transaction.service';
import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  ViewChild
} from '@angular/core';
import { Account } from '../../../shared/models/account.model';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.scss']
})
export class AccountDetailsComponent implements OnInit {
  @Input()
  accounts: Array<Account>;
  @Output()
  selectAccount: EventEmitter<Account> = new EventEmitter();
  @ViewChild('modal')
  modal: ModalContainerComponent;
  selectedAccount: Account;
  showModal: boolean;
  action: TransactionType;

  constructor(private transactionService: TransactionService) {}

  ngOnInit() {}

  onSelectAccount(account: Account) {
    this.selectedAccount = account;
    this.selectAccount.emit(account);
  }

  openModal(action: TransactionType, account: Account) {
    this.selectedAccount = account;
    this.action = action;
    this.showModal = true;
    this.modal.open(action);
  }

  closeModal() {
    this.showModal = false;
  }

  submitTransaction(action: string) {
    this.transactionService[action](this.selectedAccount).subscribe(() => {
      this.modal.close();
      this.showModal = false;
    });
  }
}
