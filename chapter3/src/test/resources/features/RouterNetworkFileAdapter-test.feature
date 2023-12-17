Feature: RouterNetworkFileAdapterTest
  Scenario: RouterNetworkFileAdapterTest
    Given I provide a router ID and the network details
    When I found the router
    And The network address is valid and doesn't already exists
    Given The CIDR is valid
    Then Add the network to the router