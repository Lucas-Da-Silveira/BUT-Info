Feature: AI Oppening Moves

  Scenario: AI plays in the center
    Given un plateau de morpion vide
    When it's the AI's turn to play
    Then the AI must play in the center

  Scenario: AI plays in a corner if the center is occupied
    Given un plateau de morpion vide
    And human played in the center
    When it's the AI's turn to play
    Then the AI must play in a corner

  Scenario: AI plays in a side if the center and corners are occupied
    Given un plateau de morpion vide
    And human played in the center
    And human played in 0
    And human played in 2
    And human played in 6
    And human played in 8
    When it's the AI's turn to play
    Then the AI must play in a side