Feature: Bagging_Unbagging Steps (Review)

  @Launch_Bagging
  Scenario Outline: Bagging_Unbagging Steps
#    Given Use the "<tc_id>"
    Given User has Launch the App using "<tc_id>"
    When User has launched the Immingham application
    And User has logged in with valid credentials
    Then CommTrac application Home page should be displayed

    # Step 40 --Verifying Before
    When the user navigate to Operations Storage Area
    And select the Storage Area
    Then all discharged product should be displayed in Storage Areas

    # Step Bagging
#    When the user navigate to Operations Bagging
#    Then Bagging Process starts

     # Step UnBagging
    When the user navigate to Operations UnBagging
    Then UnBagging Process starts

    # Step 40 --Verifying After
    When the user navigate to Operations Storage Area
    And select the Storage Area
    Then all discharged product should be displayed in Storage Areas

    Examples:
      | tc_id |
      | 11A |