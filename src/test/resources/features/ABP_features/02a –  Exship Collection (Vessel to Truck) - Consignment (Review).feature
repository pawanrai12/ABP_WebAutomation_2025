Feature: 02_A –  Exship Collection (Vessel to Truck) - Consignment (Review)

  @Launch_2A
  Scenario Outline: Vessel Planning and Adding a New Vessel Visit
#    Given Use the "<tc_id>"
    Given Launch the Application using "<tc_id>"
    When the user has launched the Immingham Pre Prod application
    And the user has logged in with valid credentials
    Then the CommTrac application home page should be displayed

    #Step 2
    When the user navigates to Vessel - Visit
    Then the Vessel Visit screen should be displayed
    #Step 3
    When the user clicks Add on Vessel Visit
    Then the Edit Visit screen should be displayed
    #Step 4
    And the user enters vessel visit details
    And clicks Save on vessel visit screen
    Then a confirmation message should be displayed on Edit Visit screen

    #Step 5
    Given the Vessel Visit screen is displayed
    When the user selects Incoming Manifest
    Then the Incoming Manifest should be displayed
    #Step 6
    When the user clicks Add under Consignments
    Then the Consignment page should be displayed
    #Step 7
    When the user enters consignment details:
    And clicks Save
    Then a confirmation message should be displayed

    #Adding Consignment Stock
    #Step 8
    Given the Consignment page is displayed
    When the user clicks Add Consignment Stock
    Then the Product selection page should be displayed
    #Step 9
    When the user enters consignment stock details:
    And clicks Save on CS
    Then the Consignment page should be displayed with confirmation
    #Step 10
    When the user clicks Return
    Then the Incoming Manifest page should be displayed

    #Assign Cargo to Holds
    #Step 11
    Given the Incoming Manifest page is displayed
    When the user clicks Add under Cargo in Holds
    Then the Cargo In Holds - Consignment Selection popup should appear

    #Step 12
    When the user selects a Consignment Number
    And clicks Next
    Then the Assign Cargo in Hold page should be displayed

   #Step 13
    When the user selects Hold
    And enters Weight
    And clicks on Hold page Save
    Then the Cargo should be visible on Cargo In Holds Table and Vessel Visual

    Examples:
      | tc_id      |
      | LoginTest1 |
