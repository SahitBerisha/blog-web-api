Feature: Update a Category

  Background:
    * url baseUrl
    * def categoriesPath = '/api/v1/categories/'
    * def authFeature = call read('authToken.feature')
    * def createFeature = call read('create.feature')
    * def token = authFeature.accessToken
    * def id = createFeature.categoryId

  Scenario: Update a Category by ID

    Given path categoriesPath + id
    And request
    """
      {
        name: 'Economy'
      }
    """
    And headers Accept = 'application/json'
    And header Authorization = 'Bearer ' + token
    When method PUT
    Then status 200
    And match response ==
    """
      {
        id: '#(id)',
        name: 'Economy',
        posts: []
      }
    """
