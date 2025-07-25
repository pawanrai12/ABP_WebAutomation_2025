Feature: Incomming Truck

  @Launch_T
  Scenario Outline: Truck>Onsite
#    Given Use the "<tc_id>"
    Given Launch the Application using "<tc_id>"
    When the user has launched the Immingham Pre Prod application
    And the user has logged in with valid credentials
    Then the CommTrac application home page should be displayed


    #//Step 17
    When the User navigates to Trucks On Site
    And selects the Add Incoming truck
    Then the Incoming Truck page should appear

    #Step 18 to 22
    When the user enters truck details7B:
    And clicks on save
    And user select truck bookout
    Then the final report should appear

    #Step 23
    #Verify truck status is Completed after selecting Completed Trucks
    When I complete the truck processing
    And I navigate to the Completed Trucks section
    Then I should see the truck's status as Completed

    #Step 24
    #Verify Discharged Products in Storage Area
    When I navigate to the Cargo>Storage Areas menu
    And a truck has completed the discharge process & I view the designated storage area on the screen
    Then I should see all the discharged products from the truck listed in the designated storage area




    Examples:
      | tc_id      |
      | LoginTest1 |