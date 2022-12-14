Feature: Create an Album

  Background:
    * url baseUrl
    * def albumBaseUrl = '/api/v1/albums'
    * def authFeature = callonce read('../login.feature')
    * def bearerToken = authFeature.bearerToken

  Scenario: Create an Album

    Given path albumBaseUrl
    And request
    """
      {
        title: 'My Album',
        userId: 'd6436e3b-717d-4c8f-8a0c-6e5820f494ae'
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
        user: '#notnull',
        title: 'My Album',
        photos: []
      }
    """
    * def albumId = response.id