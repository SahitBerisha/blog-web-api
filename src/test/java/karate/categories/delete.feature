Feature: Delete a Category

  Background:
    * url baseUrl
    * def categoriesPath = '/api/v1/categories/'
    * def authFeature = call read('authToken.feature')
    * def createFeature = call read('create.feature')
    * def token = authFeature.accessToken
    * def id = createFeature.categoryId

  Scenario: Delete a Category by ID

    Given path categoriesPath + id
    And header Authorization = 'Bearer ' + token
    When method DELETE
    Then status 204
