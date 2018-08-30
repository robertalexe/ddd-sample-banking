import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutComponent } from './layout.component';
import { AtmClientComponent } from './atm-client/atm-client.component';
import { AccountDetailsComponent } from './atm-client/account-details/account-details.component';
import { ClientDetailsComponent } from './atm-client/client-details/client-details.component';
import { TransactionDetailsComponent } from './atm-client/transaction-details/transaction-details.component';
import { ModalContainerComponent } from '../shared/components/modal-container/modal-container.component';
import { AccountActionComponent } from './atm-client/account-action/account-action.component';
import { ExchangeRateComponent } from './atm-client/exchange-rate/exchange-rate.component';

@NgModule({
  imports: [CommonModule, ReactiveFormsModule],
  declarations: [
    LayoutComponent,
    AtmClientComponent,
    AccountDetailsComponent,
    ClientDetailsComponent,
    TransactionDetailsComponent,
    ModalContainerComponent,
    AccountActionComponent,
    ExchangeRateComponent
  ],
  exports: [LayoutComponent]
})
export class LayoutModule {}
