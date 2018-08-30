@WithdrawMoney
Feature: Withdraw money from account

As a Client
In order to make operations in my account
I want to be able to withdraw money from my account

 Scenario: Withdraw money from my account
    Given my user account id is "andrei" and my base account number is "A1234567" and the current account balance is "100"
    When I withdraw 50 units
    Then my account is updated and the current balance is "50"
