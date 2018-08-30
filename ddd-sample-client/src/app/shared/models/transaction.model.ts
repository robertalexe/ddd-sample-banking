export interface Transaction {
  amount: string;
  transactionDate: string;
  transactionId: string;
  transactionType: TransactionType;
}

export enum TransactionType {
  WITHDRAW = 'WITHDRAW',
  DEPOSIT = 'DEPOSIT',
  TRANSFER = 'TRANSFER'
}
