@ExchangeRate
Feature: Consult EUR to RON exchange rate

As a Client
In order to have information regarding the EUR to RON exchange rate
I want to be able to see the exchange rate of the current day

 Scenario: Consulting the EUR to RON exchange rate
    Given I am a client of the bank
    When I consult the EUR to RON exchange rate
    Then the exchange rate is "4.65"
