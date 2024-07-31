Feature: Amazon Product Search with different options

  Scenario Outline: Product search on Amazon home page
    Given I open the Amazon homepage
    When I enter <name> in the search box
    And I click on the search button for product <name>
    Then I should see search results for product <name> 
 
    Examples: 
      | name |
      | pen  |
      | laptop |
      #| school bags for girl kid |
      #| Chocolates               |
