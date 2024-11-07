Feature: AI Strategies

  Scenario: AI blocks the human from winning
    Given un plateau de morpion vide
    And human played in 0
    And human played in 1
    When it's the AI's turn to play
    Then the AI must play in 2