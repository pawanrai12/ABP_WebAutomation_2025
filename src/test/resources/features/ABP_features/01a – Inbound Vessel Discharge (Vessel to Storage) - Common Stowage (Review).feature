Feature: 01a – Inbound Vessel Discharge (Vessel to Storage) - Common Stowage (Review)

  @Launch_1A
  Scenario Outline: Vessel Planning and Adding a New Vessel Visit (Vessel to Storage) - Common Stowage (Review)
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

    #Steps from Common Stowage Sheet
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
    When the user selects Consignment Stock for OneA
    And clicks on Save Btn
    Then the stock should be added to Common Stowage Group CSV

    #Step 19
    When the user clicks RSave on Common Stowage
    Then the Common Stowage Group details should be saved


    #Create and Manage Shunt Order
    #Step 29
    When the user navigates to Cargo Booking Shunt Orders
    Then the Shunt Order Grid should be displayed

    #Step 30
    When the user clicks Add
    Then the new Shunt Order page should be displayed

    #Step 31
    When the user enters shunt order details:

    And clicks on Save
    Then a success notification should be displayed

    #Under Consignment#

    #Step 32
    When the user clicks Add under Consignments Shunt
    Then the Product Selection page should be displayed

    #Step 33
    When the user selects product and a Consignment Stock
    And clicks Next
    #commenting
    Then the shunt Order Quantity page should be displayed

    #Step 34
    When the user enters shunt order quantity details:
    And clicks Save
    Then the order details should be saved

   #"Shunt Truck Stock Move"#
    #Step 35
    When the user navigates to Trucks Add New Shunt
    #commenting as this line is failing
    Then the Book In Shunt Truck page should be displayed

    #Step 36
    #the Order number to check from manual
    When the user enters truck bookin details:
    And confirms vessel to storage location
    And clicks Save & Next
    Then the next Book In Shunt Truck page should appear

    #//Step 38
    When the user navigates to Trucks On Site
    And selects the truck
    And clicks Book Out Shunt
    Then the Truck Book Out page should appear

    # Step 39
    When the user enters Gross Weight
    Then Nett Weight is calculated automatically
    And clicks Save
    Then the shunt should be completed and Truck Grid updated

    #Verify Storage Area and Complete Vessel Visit

    # Step 40
    When the user navigates to Cargo Storage Areas
    Then all discharged products should be visible in Storage Area

    # Step 41 to 44 Steps
    When the user navigates to Vessel Visits
    #added vessel globally with OSR no.
    And selects the vessel and clicks Edit
    And the user updates Progress to Completed
    And the user enters Arrival Time Departure Time Operation Start Operation End
    Then the Vessel Visit should be updated and marked completed

    #Review OSD and Final Confirmation
    # Step 45
    When the user navigates to Vessel Visits
    And filters for Completed vessels
    And selects Incoming Manifest
    And Consignments OSD
    Then the OSD Data Report should be displayed

    # Step 46
    When the user reviews the data
    Then all numbers should match expectations


    Examples:
      | tc_id      |
      | LoginTest1 |




