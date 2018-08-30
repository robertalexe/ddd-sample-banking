@ConsultClient
Feature: Consult my bank profile

As a Client
In order to check my bank profile
I want to be able to see all the details regarding my profile

 Scenario: Consulting the profile details
    Given my user account id is "andrei"
    When I perform the consult action on my profile
    Then the following details are retrieved concerning my profile:
        | id                | andrei                     |
        | bankBranchName    | Bucharest Central Branch   |
        | accountCreation   | 2018-04-04T00:00           |
        | clientType        | GOLD                       |
