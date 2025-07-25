Feature: 07D - Inbound Truck Delivery – In By Road - Bag (Truck to Storage) (Review)

  @Launch_7D
  Scenario Outline: 7D In By Road - Bag (Truck to Storage) (Review)
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
    When the user enters the product stock details for 7D
    And the user clicks on Next
    And enters the stock identifier details n num of bags weight details
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
    When the user selects Storage Area to discharge stock
    And the user clicks on Save
    Then consignment page should displayed

    #%%%%%%%%%%%%%%%%%Steps From Truck Onsite _Incoming Trucks%%%%%%%%%%%%%%%%%%%%%%%%%%%
     #//Step 17
    When the User navigates to Trucks On Site
    And selects the Add Incoming truck
    Then the Incoming Truck page should appear

#    #Step 18 to 22
    When the user enters truck details7D:
    And clicks on save
    And user select truck bookout
    Then the final report should appear

    #Step 23
    #Verify truck status is Completed after selecting Completed Trucks
    When I complete the truck processing
    And I navigate to the Completed Trucks section
    Then I should see the truck's status as Completed
#
#    #Step 24
#    #Verify Discharged Products in Storage Area
#    When I navigate to the Cargo>Storage Areas menu
#    And a truck has completed the discharge process & I view the designated storage area on the screen
#    Then I should see all the discharged products from the truck listed in the designated storage area


    Examples:
      | tc_id |
      | 7D |
