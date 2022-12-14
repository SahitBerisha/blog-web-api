Feature: Read Categories

  Background:
    * url baseUrl
    * def categoriesPath = '/api/v1/categories/'
    * def authFeature = call read('login.feature')
    * def createFeature = call read('create.feature')
    * def token = authFeature.accessToken
    * def id = createFeature.categoryId

  Scenario: Get one category by ID

    Given path categoriesPath + id
    And headers Authorization = 'Bearer ' + token
    When method GET
    Then status 200
    And match response ==
    """
      {
        id: '#(id)',
        name: 'Information Technology',
        posts: []
      }
    """

  Scenario: Get All Categories

    Given path categoriesPath
    And header Authorization = 'Bearer ' + token
    When method GET
    Then status 200

  Scenario: Get one Category with invalid ID

    Given path categoriesPath + 'invalid-id'
    And header Authorization = 'Bearer ' + token
    When method GET
    Then status 404
