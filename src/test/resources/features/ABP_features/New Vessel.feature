Feature: New Vessel Planning

  @Launch_New
  Scenario Outline: New Vessel Planning and Adding a New Vessel Visit
#    Given Use the "<tc_id>"
    Given Launch the App using "<tc_id>"
    When the user has launched the application
    And the user has logged in with Valid credentials
    Then the CommTrac application home page should displayed

    #Step 2
    When the user navigates to Vessel Visit
    Then the Vessel Visit screen should displayed

    #Step 3
    When user clicks Add on Vessel Visit
    Then Edit Visit screen should be displayed

    #Step 4
    And the user enters vessel visit details
    And clicks Save on vessel visit screen
    Then a confirmation message should be displayed on Edit Visit screen

    #Step 5
    Given the Vessel Visit screen is displayed
    When the user selects Incoming Manifest
    Then the Incoming Manifest should be displayed




    Examples:
      | tc_id      |
      | LoginTest3 |