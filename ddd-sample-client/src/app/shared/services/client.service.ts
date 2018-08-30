import { Router, ActivatedRoute } from '@angular/router';
import { StoreService } from './../store/store.service';
import { Client } from './../models/client.model';
import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  constructor(
    private http: HttpClient,
    @Inject('BACKEND_URL') private baseUrl: string,
    private store: StoreService,
    private router: Router
  ) {}

  cachedCliend: Client;

  authenticate(clientId: string): Observable<any> {
    return this.store.sideEffects.getClient(clientId);
  }

  getClient() {
    return this.store.getClientState();
  }

  proceedToClientPage(client: Client) {
    localStorage.setItem('currentUser', JSON.stringify(client));
    this.store.updateClientState(client);
    this.router.navigateByUrl(`/atm-client/${client.atmClientId}`);
  }

  logout() {
    this.store.purgeClientState();
    this.router.navigateByUrl('/login');
  }

  updateClient(clientId: string) {
    this.store.getClientState().subscribe(this.checkClientCache(clientId));
  }

  changePin(pin: string) {
    return this.store.sideEffects.changePin(pin);
  }

  private checkClientCache(clientId: string): (value: Client) => void {
    return (client: Client) => {
      client && client.atmClientId
        ? this.hidrateClientCache(client)
        : this.getClientIdFromRoute(clientId);
    };
  }

  private hidrateClientCache(client: Client) {
    this.cachedCliend = client;
    if (!this.cachedCliend) this.store.updateClientState(client);
  }

  private getClientIdFromRoute(clientId: string) {
    this.store.sideEffects
      .getClient(clientId)
      .subscribe((client: Client) => this.store.updateClientState(client));
  }
}
