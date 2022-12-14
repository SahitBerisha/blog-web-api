Feature: Create Category

  Background:
    * url baseUrl
    * def categoriesBaseUrl = '/api/v1/categories/'
    * def authFeature = callonce read('../login.feature')
    * def bearerToken = authFeature.bearerToken

  Scenario: Create a new Category

    Given path categoriesBaseUrl
    And request
    """
      {
        name: 'Information Technology'
      }
    """
    And header Accept = 'application/json'
    And header Authorization = bearerToken
    When method POST
    Then status 201
    And match response ==
    """
      {
        id: '#uuid',
        name: 'Information Technology',
        posts: []
      }
    """
    * def categoryId = response.id