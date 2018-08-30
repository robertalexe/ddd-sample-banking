import { Account } from './account.model';

export interface Client {
  accounts: Array<Account>;
  atmClientId: string;
  branchId: string;
  branchName: string;
  clientType: ClientType;
  creationDate: string;
  lastLogin: string;
  pin: string;
  userName: string;
}

export enum ClientType {
  SILVER = 'SILVER',
  GOLD = 'GOLD',
  PLATINUM = 'PLATINUM'
}
