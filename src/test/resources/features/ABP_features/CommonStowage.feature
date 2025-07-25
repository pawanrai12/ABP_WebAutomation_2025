Feature: We Common Stowage Group Management

  @Launch2
  Scenario Outline: We Common stowage
    Given Launch the Application using "<tc_id>"
    Given the user has launched the Immingham Pre Prod application
    And the user has logged in with valid credentials
    Then the CommTrac application home page should be displayed

    #Step 14
    When the user navigates to Cargo Common Stowage
    Then the Common Stowage Grid should be displayed

    #Step 16
    When the user clicks on Add in Common Stowage
    And enters the Commudity details:
    And clicks on Save in common stowage
    Then the Common Stowage Group details page should be displayed

    #Step 17
    When the user clicks Add under Consignment Stocks
    Then the Add to Common Stowage window should appear

    #Step 18
    When the user selects Consignment Stock
    And clicks on Save Btn
    Then the stock should be added to Common Stowage Group CSV

    #Step 19
    When the user clicks RSave on Common Stowage
    Then the Common Stowage Group details should be saved

    # Extra
#    When the user searches for commodity
#    Then filtered Common Stowage results should be displayed
#
#    When the user selects the commodity
#    And clicks on Edit
#    And if not found, adds a new commodity and saves
#    Then the Common Stowage Group details page should be displayed


    Examples:
      | tc_id      |
      | LoginTest1 |