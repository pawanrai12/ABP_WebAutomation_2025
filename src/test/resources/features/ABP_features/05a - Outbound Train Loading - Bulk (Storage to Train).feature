Feature: 05a - Outbound Train Loading - Bulk (Storage to Train)

  @Launch_5A
  Scenario Outline: 5A Outbound Train Loading
#    Given Use the "<tc_id>"
    Given Launch this App using "<tc_id>"
    When the user has launched the app
    And the user has logged in with valid cred
    Then the CommTrac app home page should be displayed

    # Step2
    #Scenario: Display Delivery Order screen
    When I navigate to Cargo> Booking > Delivery Orders
    And the user clicks on Add delivery order
    Then the delivery order screen should be displayed

    # Step3
      #  Scenario: Create Truck Intake with Working progress
    When the user enters Delivery order Name
    And the user enters consignor
    And the user clicks on SaveD
    Then the Delivery order screen with Consignment section should be displayed

    #Step4
    #Scenario: Add Consignment
    When user clicks on Add Consignment
    And enters all the required mandatory fields
    And the user clicks Next on Product Selection page
    Then Order Quantity page should be displayed
    When user enters the total weight
    And the user clicks save on order quantity page
    Then Delivery Order page should be updated with consignment

    #Step5
    #Scenario: Add Sub Delivery Order
    When user clicks on Add Sub Delivery Order
    And user enters the consignment
    And user clicks Next on sub-delivery order page
    Then Consignment details page should be displayed
    When user changes visit type to Train
    And enters the Order total weight
    And the user clicks save on sub delivery order consignment page

    #Step6
    #Scenario: Add Rail Visit
    When User click on Trains>Trains
    And the user clicks on Add Rail visit
    Then the Rail visit page should be displayed

    #Step6
    #Scenario: Enter Rail Id and mandatory details
    When User enters the Train Id
    And enters all the required  fields
    And the users click save on Rail visit page
    And user clicks on Arrive at the bottom of the page
    And the users click save on Rail visit page
    Then the Rail visit page should be displayed

    #Step7
    #Scenario: Add Outgoing Manifest
    When user clicks on Outgoing Manifest
    And clicks on Add sub delivery order under consignments
    And user enters the required fields
    And user clicks Save on sub-delivery order page
    Then the sub-delivery order details screen should be displayed

    #Step8
    #Scenario: Load Stock to the Rail visit
    When user clicks on Load Stock
    And enters the details for all the required field
    And User clicks Next on load from storage page
    And enters the data to all the fields
    And user clicks save on load from storage page
    And the Rail visit page should be displayed
    And the users click save on Rail visit page
    Then the Rail visit page should be displayed

    #Step9
    #Scenario: Depart the loaded Train
    When user selects the train and click on edit
    And user clicks on Depart at the bottom of the screen
    And the users click save on Depart Rail visit page
    Then the Rail visit page should be displayed

    #Step10
    #Scenario: Review completed Train
    When user click on Completed Trains
    And search for the recent completed Train
    Then the completed train should be present


    Examples:
      | tc_id |
      | 5A    |