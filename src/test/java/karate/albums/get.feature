Feature: Read albums

  Background:
    * url baseUrl
    * def createFeature = call read('../albums/post.feature')
    * def albumBaseUrl = createFeature.albumBaseUrl
    * def id = createFeature.albumId
    * def bearerToken = createFeature.bearerToken

  Scenario:

    Given path albumBaseUrl
    And header Authorization = bearerToken
    When method GET
    Then status 200
