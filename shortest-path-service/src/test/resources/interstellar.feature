Feature: Find shortest path
 
  Scenario: Shortest path between A and Z
    Given that A is the source planet and Z is the destination planet
    When Path is not null
    Then Shortest path is calculated and displayed