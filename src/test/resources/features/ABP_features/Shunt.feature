Feature: Shunt Orders

#  @Launchr
#  Scenario Outline: Vessel Planning and Adding a New Vessel Visit
#    Given Launch the Application using "<tc_id>"
#    Given the user has launched the Immingham Pre Prod application
#    And the user has logged in with valid credentials
#    Then the CommTrac application home page should be displayed
#
#    #Create and Manage Shunt Order
#    #Step 29
#    When the user navigates to Cargo Booking Shunt Orders
#    Then the Shunt Order Grid should be displayed
#
#    #Step 30
#    When the user clicks Add
#    Then the new Shunt Order page should be displayed
#
#    #Step 31
#    When the user enters shunt order details:
#    And clicks on Save
#    Then a success notification should be displayed
#
#    #Under Consignment#
#
#    #Step 32
#    When the user clicks Add under Consignments Shunt
#    Then the Product Selection page should be displayed
#
#    #Step 33 --have some changes
#    When the user selects product and a Consignment Stock
#    And clicks Next
#    #commenting
#    Then the shunt Order Quantity page should be displayed
#
#    #Step 34
#    When the user enters shunt order quantity details:
#    And clicks Save
#    Then the order details should be saved
#
#   #"Shunt Truck Stock Move"#
#    #Step 35
#    When the user navigates to Trucks Add New Shunt
#    Then the Book In Shunt Truck page should be displayed
#
#    #Step 36
#    #the Order number to check from manual
#    When the user enters truck bookin details:
#    And confirms vessel to storage location
#    And clicks Save & Next
#    Then the next Book In Shunt Truck page should appear
#
#    Examples:
#      | tc_id      |
#      | LoginTest1 |
#

    #"Book In Book Out" #
#  @Launchtr
#  Scenario Outline: Vessel Planning and Adding a New Vessel Visit
#    Given Launch the Application using "<tc_id>"
#    Given the user has launched the Immingham Pre Prod application
#    And the user has logged in with valid credentials
#    Then the CommTrac application home page should be displayed
#
#    #//Step 38
#    When the user navigates to Trucks On Site
#    And selects the truck
#    And clicks Book Out Shunt
#    Then the Truck Book Out page should appear
#
#    #//Step 39
#    When the user enters Gross Weight
#    Then Nett Weight is calculated automatically
#    And clicks Save
#    Then the shunt should be completed and Truck Grid updated
#
#    Examples:
#      | tc_id      |
#      | LoginTest1 |

  @Launchit1
  Scenario Outline: Vessel Planning and Adding a New Vessel Visit
    Given Launch the Application using "<tc_id>"
    Given the user has launched the Immingham Pre Prod application
    And the user has logged in with valid credentials
    Then the CommTrac application home page should be displayed

    When delete
    Then deleted

    #Verify Storage Area and Complete Vessel Visit

#    # Step 40
#    When the user navigates to Cargo Storage Areas
#    Then all discharged products should be visible in Storage Area
#
#    # Step 41
#    When the user navigates to Vessel Visits
#    #added vessel globally with OSR no.
#    And selects the vessel and clicks Edit
#    And the user updates Progress to Completed
#    And the user enters Arrival Time Departure Time Operation Start Operation End
#    Then the Vessel Visit should be updated and marked completed
#
     Examples:
      | tc_id      |
      | LoginTest1 |


#  @Launchit1
#  Scenario Outline: Vessel Planning and Adding a New Vessel Visit
#    Given Launch the Application using "<tc_id>"
#    Given the user has launched the Immingham Pre Prod application
#    And the user has logged in with valid credentials
#    Then the CommTrac application home page should be displayed
#
##    #Review OSD and Final Confirmation
#    # Step 45
#    When the user navigates to Vessel Visits
#    And filters for Completed vessels
#    And selects Incoming Manifest
#    And Consignments OSD
#    Then the OSD Data Report should be displayed


    # Step 46
#    When the user reviews the data
#    Then all numbers should match expectations
#    Examples:
#      | tc_id      |
#      | LoginTest1 |
