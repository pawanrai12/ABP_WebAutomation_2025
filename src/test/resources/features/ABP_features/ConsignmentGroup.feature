Feature: Consignment Group and Storage Area Management

  @LaunchCG
  Scenario Outline: Vessel Planning and Adding a New Vessel Visit
    Given Launch the Application using "<tc_id>"
    Given the user has launched the Immingham Pre Prod application
    And the user has logged in with valid credentials
    Then the CommTrac application home page should be displayed

    #Manage Consignment Group
    #Step 14
    When the user navigates to Cargo Consignment Group
    Then the Consignment Group Grid should be displayed

    #Step 16
    When the user clicks Add under ConsignmentGroup
    And enters the Commodity details:
    And clicks on Save in consignmentGroup page
    Then the Consignment Group details page should be displayed

    #Step 17
    When the user clicks Add under Consignment Stocks in CGpage
    Then the Add to Consignment Group window should appear CG

    #Step 18
    When the user selects Consignment Stock in CG
    And clicks on Save Btn in CG
    Then the stock should be added to Consignment Stock Group CSV

    #Step 19
    When the user clicks RSave on Consignment Stock
    Then the Consignment Stock Group details should be saved

#    When the user selects a Consignment Stock
#    And  clicks Save
#    Then the Consignment Group details with the newly added Consignment Stock should be displayed
#    When the user clicks Save
#    Then the Consignment Group details should be saved
#
#    When the user enters commoditysname in the search box
#    Then the Consignment Group Grid with search results should be displayed
#    When the user performs an action based on the search result
#
#    Then the Consignment Group details should be displayed
#
#
#
#    #Update Vessel Progress
#    When the user navigates to Vessel Visit
#    Then the Vessel Visit Grid should be displayed
#    When the user selects a vessel
#    And  clicks Edit
#    Then the Edit Vessel Visit screen should be displayed
#    When the user changes the Progress from Booked to Working
#    And  clicks Save
#    Then the Progress should be changed from Booked to Working

#        #Manage Storage Area
#    When the user navigates to Cargo > Storage Areas
#    Then the Storage Area Grid should be displayed
#    When the user selects a location "<location>" and clicks View Stockyard
#    Then the Location Shed Storage Area should be displayed
#    When the user clicks Add Stock Area and draws a stock area
#    Then a Stock Area should be drawn in the location shed and displayed
#    When the user clicks Save
#    Then the Stock Area should be saved
#    When the user clicks the newly created area and selects Area Management
#    Then the Area Management window should be displayed
#    When the user enters and saves the area details:
#    Then the Area Name should be changed and saved

    Examples:
      | tc_id      |
      | LoginTest1 |
