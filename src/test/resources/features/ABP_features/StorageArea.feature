Feature: New Storage Area Creation

  @Launch_Storage
  Scenario Outline: Creation of Storage Area
#    Given Use the "<tc_id>"
    Given Launch the Application using "<tc_id>"
    When the user has launched the Immingham Pre Prod application
    And the user has logged in with valid credentials
    Then the CommTrac application home page should be displayed

#    When User navigates to Cargo Storage Area
#    And create new Storage Area
#    And I Drag and Drop to create a box at coordinates
#    #And I drag and drop to create a box from coordinates {int}, {int} to {int}, {int}
#    Then a new stock box should be displayed





    Examples:
      | tc_id      |
      | LoginTest1 |