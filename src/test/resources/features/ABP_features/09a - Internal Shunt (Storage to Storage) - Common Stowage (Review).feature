Feature: 09a –Internal Shunt (Storage to Storage) - Common Stowage (Review)

  @Launch_9A
  Scenario Outline: Internal Shunt (Storage to Storage) - Common Stowage (Review)
#    Given Use the "<tc_id>"
    Given User has Launch the App using "<tc_id>"
    When User has launched the Immingham application
    And User has logged in with valid credentials
    Then CommTrac application Home page should be displayed

    # Step 40 --Verifying Before
    When the user navigates to Cargo Storage Area
    And selects the Common Stowage Area
    Then all discharged products should be visible in Storage Areas

    #Create and Manage Shunt Order
    #Step 29
    When the user navigates to Cargo Booking Shunt Order
    Then the Shunt Orders Grid should be displayed

    #Step 30
    When the user click Add
    Then the new Shunt Order pages should be displayed

    #Step 31
    When the user enters shunt order details for 9A:
    And click on Save
    Then success notification should be displayed

    #Under Consignment#
    #Step 32
    When the user click Add under Consignments Shunt
    Then the Product Selections page should be displayed

    #Step 33 -//#Step 33 --USRN Value has to pass ***Pass Storage value ****
    When the user selects product and a Consignment Stock for 9A
    And click Next
    #commenting
    Then the shunt Order Quantity pages should be displayed

    #Step 34  ##***Change the ARea and Weight **** $$$
    When the user enters shunt order quantity details for 9A:
    And click on Save
    Then the order detail should be saved


   #****"Shunt Truck Stock Move"*****# skipping 35 and 36 steps from shunt feature

    #//Step 38 --Truck>>Onsite
    When the user navigates to Trucks On Site
    And click on Add Shunt Truck
    Then the Book in Shunt Truck page should appear

   # Step 40 --Verifying After
    When the user navigates to Cargo Storage Area
    And selects the Common Stowage Area
    Then all discharged products should be visible in Storage Areas

    # Step 41 --Verifying After Complete
    # Making Shunt Order Complete and verifying
    When the user navigates to Cargo Booking Shunt Order
    And the Shunt Orders Grid should be displayed
    Then complete the Shunt Order


    Examples:
      | tc_id |
      | 9A |