@ChangePin
Feature: Change the PIN of a ATM Client Account

As a Client
In order to have a secure account
I want to be able to change my PIN easily

 Scenario: Changing the PIN of my account
    Given my user account id is "andrei" and my current pin is 1234
    When I perform the change PIN action and my new entered PIN is 4321
    Then my account is updated and the new PIN is 4321
