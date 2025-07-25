Feature: Truck Intake Process

  @Launch_7
  Scenario Outline: 7
#    Given Use the "<tc_id>"
    Given Launch this App using "<tc_id>"
    When the user has launched the app
    And the user has logged in with valid cred
    Then the CommTrac app home page should be displayed

    #Step 2
    #Scenario: Display Truck Intake screen
    When I Navigate to Cargo>Booking>Intakes
    And the user clicks on Add in Truck Intake
    Then the Truck Intake screen should be displayed

    #Step 3
    #Scenario: Create Truck Intake with Working progress
    When the user enters Intake Name
    And the user changes progress to Working
    And the user clicks on SaveR
    Then the Truck Intake screen with Consignment section should be displayed

    #Step 4
    #Scenario: Add Consignment
    When the user clicks on Add under the Consignment section
    Then Consignment page should be displayed

    #Step 5
    #Scenario: Enter mandatory details in Consignment and save
    When the user enters the Consignor details field
    And the user clicks on SaveR
    Then a Saved successfully message should be displayed and the Consignment Stock option should be available

   #Step 6
   #Scenario: Add Consignment Stock
    When the user clicks on Add under Consignment Stock
    Then Product Selection page should be displayed

   #Step 7
   #Scenario: Enter Product details and create consignment stock
    When the user enters the product stock details
    And the user clicks on Next
    And user enters weight val
    Then Saved successfully message should be displayed and consignment stock should be created

  #Step 8
  #Scenario: Open Consignment Stock Product Menu
    When the user clicks on the product under Consignment Stock
    Then a product menu should be displayed

   #Step 9
  #Scenario: Open Preferred Location Window
    When the user clicks on Preferred Location
    Then the Preferred Location window should be displayed

  #Step 10
  #Scenario: Save Preferred Storage Area
    When the user selects a Storage Area to discharge stock
    And the user clicks on Save
    Then consignment page should displayed


    Examples:
      | tc_id      |
      | LoginTest1 |