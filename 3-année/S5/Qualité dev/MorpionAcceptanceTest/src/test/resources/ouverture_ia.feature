Feature: Stratégie d'ouverture par l'IA dans le jeu de morpion

  Scenario: L'IA commence en jouant au centre
    Given un plateau de morpion vide
    When c'est au tour de l'IA de jouer
    Then l'IA doit jouer en position (2, 2)

  Scenario: L'IA joue un coin si le centre est occupé
    Given un plateau de morpion avec les coups suivants :
      | position | joueur |
      | (2, 2)   | X      |
    When c'est au tour de l'IA de jouer
    Then l'IA doit jouer en position (1, 1) or (1, 3) or (3, 1) or (3, 3)

  Scenario: L'IA commence par un coin si elle joue en premier
    Given un plateau de morpion vide
    When l'IA choisit de commencer dans un coin
    Then l'IA doit jouer en position (1, 1) or (1, 3) or (3, 1) or (3, 3)

  Scenario: L'IA joue le côté si le centre et les coins sont occupés
    Given un plateau de morpion avec les coups suivants :
      | position | joueur |
      | (2, 2)   | X      |
      | (1, 1)   | O      |
      | (1, 3)   | X      |
    When c'est au tour de l'IA de jouer
    Then l'IA doit jouer en position (1, 2) or (2, 1) or (2, 3) or (3, 2)