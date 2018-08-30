import { StoreService } from './../store/store.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ExchangeRateService {
  constructor(private store: StoreService) {}

  getExchangeRate() {
    return this.store.sideEffects.getExchangeRate();
  }
}
