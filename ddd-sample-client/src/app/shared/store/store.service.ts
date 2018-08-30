import { Account } from './../models/account.model';
import { FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Client } from './../models/client.model';
import { Injectable, Inject } from '@angular/core';
import { Subject, BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StoreService {
  private clientState: BehaviorSubject<Client>;
  private transactionState: Subject<FormGroup>;
  private client: Client;
  action: any;

  constructor(
    private http: HttpClient,
    @Inject('BACKEND_URL') private baseUrl: string
  ) {
    this.initializeStore();
    this.hidrateClientInStore();
    this.hidrateTransactionAmount();
  }

  updateClientState(client: Client): void {
    this.clientState.next(client);
  }

  getClientState(): Subject<Client> {
    return this.clientState;
  }

  purgeClientState() {
    this.clientState.next(null);
  }

  updateTransactionState(actionForm: FormGroup) {
    this.transactionState.next(actionForm);
  }

  getTransactionState() {
    return this.transactionState;
  }

  sideEffects = {
    getClient: (accountId: string): Observable<any> => {
      return this.http.get(`${this.baseUrl}/consult/client/${accountId}`);
    },
    getExchangeRate: (): Observable<any> => {
      return this.http.get(`${this.baseUrl}/consult/exchange-rate`);
    },
    withdraw: (account: Account): Observable<any> => {
      return this.http.put(
        `${this.baseUrl}/operations/withdraw/${this.client.atmClientId}/${
          account.uniqueId
        }/${this.action.amount}`,
        {}
      );
    },
    deposit: (account: Account): Observable<any> => {
      return this.http.put(
        `${this.baseUrl}/operations/deposit/${this.client.atmClientId}/${
          account.uniqueId
        }/${this.action.amount}`,
        {}
      );
    },
    transfer: (account: Account): Observable<any> => {
      return this.http.put(
        `${this.baseUrl}/operations/transfer/${this.client.atmClientId}/${
          account.uniqueId
        }/${this.client.atmClientId}/${this.action.transferAccount}/${
          this.action.amount
        }`,
        {}
      );
    },
    changePin: (pin: string): Observable<any> => {
      return this.http.put(
        `${this.baseUrl}/changepin/${this.client.atmClientId}/${pin}`,
        {}
      );
    }
  };

  private initializeStore() {
    this.clientState = new BehaviorSubject(null);
    this.transactionState = new Subject();
  }

  private hidrateClientInStore() {
    this.clientState.subscribe((client: Client) => (this.client = client));
  }

  private hidrateTransactionAmount() {
    this.transactionState.subscribe(
      (actionForm: FormGroup) => (this.action = actionForm.value)
    );
  }
}
