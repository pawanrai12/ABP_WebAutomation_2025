Feature: Delivery Order Creation
As a Cargo Booking User
I want to create Delivery Orders
So that I can manage outbound cargo efficiently

  @LaunchDO
  Scenario Outline: Vessel Planning and Adding a New Vessel Visit
#    Given Use the "<tc_id>"
    Given Launch the Application using "<tc_id>"
    When the user has launched the Immingham Pre Prod application
    And the user has logged in with valid credentials
    Then the CommTrac application home page should be displayed

#    #Step 15
#    #Successfully create a new Delivery Order with product and quantity
#    Given I am on the Delivery Orders grid under Cargo>Booking>Delivery Orders
#    When I click on the Add button in Delivery Orders
#    Then a New Delivery Order page should be displayed
#    And I enter the details for the Delivery Order:
#      | Field Name       | Value                |
#      | Order Number     | <OrderNumber>        |
#      | Order Status     | <OrderStatus>        |
#      | Consignor        | <Consignor>          |
#    And I click on the "Save" button
#    Then a save success message should be displayed
#    And the "Consignment options" should be available
#    When I click "Add" under "Consignment" section
#    Then a "Product selection" page should be displayed as a popup
#    And I enter the following mandatory details for Product selection:
#      | Field Name          | Value                        |
#      | Product             | <Product>                    |
#      | Consignment Stock   | <ConsignmentStock>           |
#    And I click on the "Next" button
#    Then the "Order Quantity" page should be displayed
#    And I enter the following mandatory details for Order Quantity:
#      | Field Name         | Value          |
#      | Order Total Weight | <OrderTotalWeight> |
#      | Weight Unit        | <WeightUnit>   |
#    And I click on the "Save" button
#    Then a save success message should be displayed

    Examples:
      | tc_id      |
      | LoginTest1 |