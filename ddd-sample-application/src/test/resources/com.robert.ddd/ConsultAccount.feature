@ConsultAccount
Feature: Consult my bank account details

As a Client
In order to check my account state
I want to be able to see all the details from my bank account

 Scenario: Consulting the account details
    Given my user account id is "andrei" and my account number is "A1234567"
    When I perform the consult action on my account
    Then the following details are retrieved:
        | accountId         | A1234567    |
        | iban              | RO12345     |
        | accountCurrency   | EUR         |
        | accountBalance    | 100         |
