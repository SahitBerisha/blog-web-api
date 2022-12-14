Feature: Delete a Post

  Background:
    * url baseUrl
    * def createFeature = call read('../posts/post.feature')
    * def bearerToken = createFeature.bearerToken
    * def id = createFeature.postId
    * def postsUrlBase = '/api/v1/posts/' + id

  Scenario: Delete a Post by ID

    Given path postsUrlBase
    And header Authorization = bearerToken
    When method DELETE
    Then status 204
