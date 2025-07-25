Feature: 09b –Internal Shunt (Storage to Storage) - Consignment (Review)

  @Launch_9B
  Scenario Outline: Internal Shunt (Storage to Storage) - Consignment (Review)
    #Given Use the "<tc_id>"
    Given Launch the Application using "<tc_id>"
    When the user has launched the Immingham Pre Prod application
    And the user has logged in with valid credentials
    Then the CommTrac application home page should be displayed

    # Step 40 --Verifying the AREA Storage before
    When the user navigates to Cargo Storage Area
    And selects the Consignment Area
    Then all discharged products should be visible in Storage Areas

    #Create and Manage Shunt Order
    #Step 29
    When the user navigates to Cargo Booking Shunt Order
    Then the Shunt Orders Grid should be displayed

    #Step 30
    When the user click Add
    Then the new Shunt Order pages should be displayed

    #Step 31
    When the user enters shunt order details for 9B:
    And click on Save
    Then success notification should be displayed

    #Under Consignments ##
    #Step 32
    When the user click Add under Consignments Shunt
    Then the Product Selections page should be displayed

    #Step 33  ***Pass Storage value from Excel --Change Here ****
    When the user selects product and a Consignment Stock for 9B
    And click Next
    Then the shunt Order Quantity pages should be displayed

    #Step 34 ##***Change the ARea and Weight ****
    When the user enters shunt order quantity details for 9B:
    And click on Save
    Then the order detail should be saved

   #****"Shunt Truck Stock Move"*****# skipping 35 and 36 steps from shunt feature

    #//Step 38 --Truck>>Onsite
    When the user navigates to Trucks On Site
    And click on Add Shunt Truck
    Then the Book in Shunt Truck pages should appear

    # Step 40 --Verifying After
    When the user navigates to Cargo Storage Area
    And selects the Consignment Area
    Then all discharged products should be visible in Storage Areas

    Examples:
      | tc_id      |
      | LoginTest2 |