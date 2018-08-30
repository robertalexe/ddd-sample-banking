@TransferBetweenAccounts
Feature: Transfer money between 2 accounts

As a Client
In order to make operations in my account
I want to be able to transfer money from one account to another

 Scenario: Transfer money between 2 accounts
    Given my user account id is "andrei" and my base account number is "A1234567" and the current account balance is "100" and the second account is "A1234678" and balance is "200"
    When I transfer 50 units from base account to second account
    Then the base account's balance is "50" and the second account's balance is "250"
