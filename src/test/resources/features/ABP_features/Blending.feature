Feature: Blending (Review)

  @Launch_Blending
  Scenario Outline: Blending
#    Given Use the "<tc_id>"
    Given User has Launch the App using "<tc_id>"
    When User has launched the Immingham application
    And User has logged in with valid credentials
    Then CommTrac application Home page should be displayed

    # Step 40 --Verifying Before
    When the user navigates to Operations Storage Area
    And selects the Storage Area
    Then all discharged products should be displayed in Storage Areas


    When I click on Menu
    And I do some actions
    Then Menu is displayed

    # Step 40 --Verifying After
    When the user navigates to Operations Storage Area
    And selects the Storage Area
    Then all discharged products should be displayed in Storage Areas

    Examples:
      | tc_id |
      | 10A |