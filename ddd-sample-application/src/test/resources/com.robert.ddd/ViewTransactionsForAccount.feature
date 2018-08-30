@ViewTransactions
Feature: Withdraw money from account

As a Client
In order to make operations in my account
I want to be able to withdraw money from my account

 Scenario: Withdraw money from my account
    Given my user account id is "andrei" and my accountId is "A1234567"
    When I consult my transactions list
    Then I can see the following transaction:
        | transactionType       | DEPOSIT           |
        | amount                | 100               |
        | transactionDate       | 2018-04-04T00:00  |