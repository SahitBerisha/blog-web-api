Feature: Login test

  Background:
    * url baseUrl
    * def loginBaseUrl = '/api/login'

  Scenario: Login and return access token

    Given path loginBaseUrl
    And form field username = admin
    And form field password = password
    When method post
    Then status 200

    * def bearerToken = 'Bearer ' + response.token