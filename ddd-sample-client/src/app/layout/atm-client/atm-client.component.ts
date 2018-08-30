import { ExchangeRateService } from './../../shared/services/exchange-rate.service';
import { Client } from '../../shared/models/client.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { StoreService } from '../../shared/store/store.service';
import { ClientService } from '../../shared/services/client.service';
import { Account } from '../../shared/models/account.model';

@Component({
  selector: 'app-atm-client',
  templateUrl: './atm-client.component.html',
  styleUrls: ['./atm-client.component.scss']
})
export class AtmClientComponent implements OnInit {
  clientId: string;
  client: Client;
  selectedAccount: Account;
  exchangeRate: number;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private exchangeRateService: ExchangeRateService
  ) {}

  ngOnInit() {
    this.initializeClient();
    this.initializeExchangeRate();
  }

  onSelectAccount(account: Account) {
    this.selectedAccount = account;
  }

  logout() {
    this.clientService.logout();
  }

  private initializeClient() {
    this.clientService.updateClient(this.route.snapshot.params.id);
    this.clientService.getClient().subscribe((client: Client) => {
      this.client = client;
    });
  }

  private initializeExchangeRate() {
    this.exchangeRateService
      .getExchangeRate()
      .subscribe((exchangeRate: number) => (this.exchangeRate = exchangeRate));
  }
}
