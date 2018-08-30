import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ClientService } from '../shared/services/client.service';
import { Client } from '../shared/models/client.model';
import { Router } from '@angular/router';
import { StoreService } from '../shared/store/store.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  errorMessage = '';

  constructor(private clientService: ClientService) {
    localStorage.removeItem('currentUser');
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      accountId: new FormControl('', Validators.required)
    });
  }

  login() {
    this.clearErrorMessage();
    this.clientService
      .authenticate(this.loginForm.get('accountId').value)
      .subscribe(
        this.navigateToAccount(),
        this.displayErrorMessage('Client not found')
      );
  }

  private clearErrorMessage() {
    this.errorMessage = '';
  }

  private displayErrorMessage(message: string): (error: any) => void {
    return () => (this.errorMessage = message);
  }

  private navigateToAccount(): (value: any) => void {
    return (client: Client) => this.clientService.proceedToClientPage(client);
  }
}
