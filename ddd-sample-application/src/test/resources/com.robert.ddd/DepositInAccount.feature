@DepositMoney
Feature: Deposit money in account

As a Client
In order to make operations in my account
I want to be able to deposit money in my account

 Scenario: Deposit money in my account
    Given my user account id is "andrei" and my base account number is "A1234567" and the account balance is "100"
    When I deposit 200 units
    Then my account is updated and the balance is "300"

