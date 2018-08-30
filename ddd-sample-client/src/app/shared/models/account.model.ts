import { Transaction } from './transaction.model';
export interface Account {
  balance: string;
  currency: string;
  iban: string;
  transactions: Array<Transaction>;
  uniqueId: string;
}
