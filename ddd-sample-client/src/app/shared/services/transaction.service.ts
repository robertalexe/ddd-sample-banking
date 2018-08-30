import { Account } from './../models/account.model';
import { Client } from './../models/client.model';
import { Injectable } from '@angular/core';
import { StoreService } from '../store/store.service';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  constructor(private store: StoreService) {}

  withdraw(account: Account) {
    return this.store.sideEffects.withdraw(account).pipe(
      map(this.updateClientState())
    );
  }

  deposit(account: Account) {
    return this.store.sideEffects.deposit(account).pipe(
      map(this.updateClientState())
    );
  }
  
  transfer(account: Account) {
    return this.store.sideEffects.transfer(account).pipe(
      map(this.updateClientState())
    );
  }

  private updateClientState(): (value: Client, index: number) => void {
    return (client: Client) => {
      this.store.updateClientState(client);
    };
  }
}
