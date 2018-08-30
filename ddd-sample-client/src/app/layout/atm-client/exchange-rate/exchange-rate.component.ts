import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-exchange-rate',
  templateUrl: './exchange-rate.component.html',
  styleUrls: ['./exchange-rate.component.scss']
})
export class ExchangeRateComponent implements OnInit {
  @Input()
  exchangeRate: number;
  constructor() {}

  ngOnInit() {}
}
