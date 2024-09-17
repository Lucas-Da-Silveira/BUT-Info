Feature: Stratégie de placement de pion par l'IA dans le jeu de morpion

  Scenario: L'IA bloque la victoire de l'adversaire
    Given un plateau de morpion avec les coups suivants :
      | position | joueur |
      | (1, 1)   | X      |
      | (1, 2)   | X      |
      | (2, 1)   | O      |
    When c'est au tour de l'IA de jouer
    Then l'IA doit jouer en position (1, 3)

  Scenario: L'IA gagne en plaçant le troisième pion dans une ligne
    Given un plateau de morpion avec les coups suivants :
      | position | joueur |
      | (2, 1)   | X      |
      | (3, 1)   | O      |
      | (2, 2)   | X      |
      | (1, 3)   | O      |
    When c'est au tour de l'IA de jouer
    Then l'IA doit jouer en position (2, 3)

  Scenario: L'IA choisit le centre si disponible
    Given un plateau de morpion vide
    When c'est au tour de l'IA de jouer
    Then l'IA doit jouer en position (2, 2)

  Scenario: L'IA choisit un coin si le centre est pris
    Given un plateau de morpion avec les coups suivants :
      | position | joueur |
      | (2, 2)   | X      |
    When c'est au tour de l'IA de jouer
    Then l'IA doit jouer en position (1, 1)
