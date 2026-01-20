Feature: SIM Card Activation
  As a telecom system
  I want to activate SIM cards through an external actuator
  So that valid SIMs are activated and invalid ones are rejected

  Scenario: Successful SIM card activation
    Given the SIM activation service is running
    When I submit an activation request with ICCID "1255789453849037777"
    Then the activation should be successful
    And the activation status for record ID 1 should be "ACTIVATED"

  Scenario: Failed SIM card activation
    Given the SIM activation service is running
    When I submit an activation request with ICCID "8944500102198304826"
    Then the activation should fail
    And the activation status for record ID 2 should be "FAILED"
