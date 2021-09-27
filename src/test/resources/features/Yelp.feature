Feature: Search Yelp

  Background:

    Given user navigates to https://www.yelp.com/
    And selects find Restaurants

  Scenario Outline: Detail of the first restaurant search result

    Given User search restaurant <type>
    When filters by <neighborhoods>
    And select the first search result
    Then User views restaurant information

    Examples:
      |type   |neighborhoods|
      |Pizza  |Glen Park |