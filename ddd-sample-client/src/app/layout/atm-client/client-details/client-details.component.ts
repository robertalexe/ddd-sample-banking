import { FormControl, Validators } from '@angular/forms';
import { ClientService } from './../../../shared/services/client.service';
import { Component, OnInit, Input } from '@angular/core';
import { Client } from '../../../shared/models/client.model';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.scss']
})
export class ClientDetailsComponent implements OnInit {
  @Input()
  client: Client;

  pin: FormControl;
  successMessage: string = '';

  constructor(private clientService: ClientService) {}

  ngOnInit() {
    this.pin = new FormControl('', Validators.required);
  }

  changePin() {
    this.clientService.changePin(this.pin.value).subscribe(() => {
      this.pin.reset();
      this.updateSuccessMessage();
    });
  }

  updateSuccessMessage() {
    this.successMessage = 'Pin changed successfully';
    setTimeout(() => this.successMessage = '', 1500);
  }

}
