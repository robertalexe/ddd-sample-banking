@ViewAllClients
Feature: View All Bank's Clients

As a Supervisor
In order to have a report of all the ATM's Clients
I want to be able to view the list of active clients

 Scenario: Viewing the ATM Client's list
    Given I am an administrator of the application
    And the application has 2 registered clients
    When I request the list of the active clients
    Then I receive a list containing 2 clients
